package com.github.demoproject.sys_config.entity;

import com.github.demoproject.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;

/**
 * SysConfig
 *
 * @author hackyo
 * @since 1.0.0
 */
@Data
@Entity(name = "sys_config")
public class SysConfig extends BaseEntity {

    /**
     * config key
     */
    private String configKey;

    /**
     * config value
     */
    private String configValue;

    /**
     * description
     */
    private String description;

}
