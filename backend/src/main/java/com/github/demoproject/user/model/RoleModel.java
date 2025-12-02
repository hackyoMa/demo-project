package com.github.demoproject.user.model;

import com.github.demoproject.user.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * RoleModel
 *
 * @author hackyo
 * @since 1.0.0
 */
@Data
public class RoleModel {

    /**
     * role
     */
    private Role role;

    /**
     * permissions
     */
    private List<String> permissions;

}
