package com.github.demoproject.user.entity;

import com.github.demoproject.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;

/**
 * Permission
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Data
@Entity(name = "permission")
public class Permission extends BaseEntity {

    /**
     * parent id
     */
    private String parentId;

    /**
     * name
     */
    private String name;

    /**
     * description
     */
    private String description;

    /**
     * is basics
     */
    private Boolean basics;

}
