package com.github.demoproject.config;

import com.github.demoproject.util.CurrentUser;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.Optional;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

/**
 * DataJpaConfig
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Configuration
@EnableJpaAuditing
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class DataJpaConfig {

    private static final String SYSTEM_USER = "system";

    @Bean
    public AuditorAware<@NonNull String> auditorProvider() {
        return () -> Optional.ofNullable(CurrentUser.getId()).or(() -> Optional.of(SYSTEM_USER));
    }

}
