package com.github.demoproject.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

/**
 * RolePermission
 *
 * @author hackyo
 * @since 1.0.0
 */
@Data
@Entity(name = "role_permission")
@IdClass(RolePermission.class)
public class RolePermission {

    /**
     * role id
     */
    @Id
    private String roleId;

    /**
     * permission id
     */
    @Id
    private String permissionId;

}
