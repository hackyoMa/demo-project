package com.github.demoproject.sys_config.repository;

import com.github.demoproject.sys_config.entity.SysConfig;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * SysConfigRepository
 *
 * @author hackyo
 * @since 1.0.0
 */
@Repository
public interface SysConfigRepository extends JpaRepository<@NonNull SysConfig, @NonNull String> {

    /**
     * findFirstByConfigKey
     *
     * @param configKey config key
     * @return sys config
     */
    Optional<SysConfig> findFirstByConfigKey(String configKey);

}
