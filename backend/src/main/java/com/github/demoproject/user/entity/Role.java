package com.github.demoproject.user.entity;

import com.github.demoproject.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;

/**
 * Role
 *
 * @author hackyo
 * @since 1.0.0
 */
@Data
@Entity(name = "role")
public class Role extends BaseEntity {

    /**
     * name
     */
    private String name;

    /**
     * name
     */
    private String description;

    /**
     * is system role
     */
    private Boolean systemRole;

}
