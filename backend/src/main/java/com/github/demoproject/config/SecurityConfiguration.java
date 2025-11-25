package com.github.demoproject.config;

import com.github.demoproject.common.SecurityProperties;
import com.github.demoproject.user.entity.UserInfo;
import com.github.demoproject.user.service.UserService;
import com.github.demoproject.util.I18n;
import com.github.demoproject.util.RequestUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * SecurityConfiguration
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final SecurityProperties securityProperties;
    private final WebServerFactory webServerFactory;
    private final UserService userService;
    private final JwtParser jwtParser;

    @Autowired
    public SecurityConfiguration(SecurityProperties securityProperties,
                                 WebServerFactory webServerFactory,
                                 UserService userService) {
        this.securityProperties = securityProperties;
        this.webServerFactory = webServerFactory;
        this.userService = userService;
        this.jwtParser = Jwts.parser().verifyWith(securityProperties.getSecret().getPublicKey()).build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        webServerFactory.setSslRedirect(http);
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions((HeadersConfigurer.FrameOptionsConfig::sameOrigin)))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())
                        .authenticationEntryPoint((_, _, authException) -> {
                            throw authException;
                        })
                        .accessDeniedHandler((_, _, accessDeniedException) -> {
                            throw accessDeniedException;
                        })
                ).addFilterAfter(new AdditionalCheckFilter(), BearerTokenAuthenticationFilter.class);
        configureWhitelistAccess(http);
        return http.build();
    }

    private void configureWhitelistAccess(HttpSecurity http) {
        PathPatternRequestMatcher.Builder requestMatcher = PathPatternRequestMatcher.withDefaults().basePath(WebConfig.CONTEXT_PATH);
        http.authorizeHttpRequests(authorizeHttpRequests -> {
            Arrays.stream(securityProperties.getAllWhitelist())
                    .map(requestMatcher::matcher)
                    .forEach(matcher -> authorizeHttpRequests.requestMatchers(matcher).permitAll());
            securityProperties.getWhitelist().entrySet().stream()
                    .flatMap(entry -> Arrays.stream(entry.getValue())
                            .map(path -> requestMatcher.matcher(entry.getKey(), path))
                    ).forEach(matcher -> authorizeHttpRequests.requestMatchers(matcher).permitAll());
            authorizeHttpRequests.requestMatchers(requestMatcher.matcher("/**"))
                    .authenticated().anyRequest().permitAll();
        });
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return token -> {
            try {
                Jws<Claims> jws = jwtParser.parseSignedClaims(token);
                Map<String, Object> payload = jws.getPayload();
                return new Jwt(token, Instant.ofEpochMilli((Long) payload.get(JwtClaimNames.IAT)),
                        null, jws.getHeader(), payload);
            } catch (Exception e) {
                throw new InvalidBearerTokenException(I18n.get("badCredentials"));
            }
        };
    }

    private final class AdditionalCheckFilter extends OncePerRequestFilter {
        private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
        private static final UrlPathHelper PATH_HELPER = new UrlPathHelper();

        @Override
        protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && authentication instanceof JwtAuthenticationToken) {
                try {
                    Jwt jwt = (Jwt) authentication.getPrincipal();
                    if (jwt != null) {
                        String userId = jwt.getSubject();

                        String userAgent = RequestUtil.getUserAgent(request);
                        String clientIp = RequestUtil.getClientIp(request);
                        userService.verifyToken(userId, jwt.getId(), userAgent, clientIp);

                        UserInfo user = userService.getById(userId);
                        userService.verifyUserStatus(user);

                        List<SimpleGrantedAuthority> grantedAuthorityList = user.getPermissionIds().stream().map(SimpleGrantedAuthority::new).toList();
                        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(jwt, grantedAuthorityList));
                    }
                } catch (AuthenticationException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
            filterChain.doFilter(request, response);
        }

        @Override
        protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
            String path = PATH_HELPER.getLookupPathForRequest(request);
            return !PATH_MATCHER.match(WebConfig.CONTEXT_PATH + "/**", path);
        }
    }

}
