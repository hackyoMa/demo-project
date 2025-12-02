package com.github.demoproject.config;

import com.github.demoproject.common.SecurityProperties;
import com.github.demoproject.user.entity.UserInfo;
import com.github.demoproject.user.service.UserService;
import com.github.demoproject.util.I18n;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    public SecurityConfiguration(SecurityProperties securityProperties,
                                 WebServerFactory webServerFactory,
                                 UserService userService) {
        this.securityProperties = securityProperties;
        this.webServerFactory = webServerFactory;
        this.userService = userService;
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
                );
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
                SignedJWT signedJWT = SignedJWT.parse(token);
                if (!signedJWT.verify(securityProperties.getSecret().getVerifier())) {
                    throw new InvalidBearerTokenException(I18n.get("badCredentials"));
                }
                Map<String, Object> header = signedJWT.getHeader().toJSONObject();
                Map<String, Object> claims = signedJWT.getJWTClaimsSet().toJSONObject();
                return new Jwt(token, signedJWT.getJWTClaimsSet().getIssueTime().toInstant(),
                        null, header, claims);
            } catch (ParseException | JOSEException e) {
                throw new InvalidBearerTokenException(I18n.get("badCredentials"));
            }
        };
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            String userId = jwt.getSubject();
            userService.verifyToken(userId, jwt.getId());
            UserInfo user = userService.getById(userId);
            userService.verifyUserStatus(user);
            return user.getPermissionIds().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        });
        return jwtAuthenticationConverter;
    }

}
