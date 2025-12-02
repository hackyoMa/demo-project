package com.github.demoproject.user.model;

import lombok.Data;

/**
 * UpdateUserPasswordModel
 *
 * @author hackyo
 * @since 1.0.0
 */
@Data
public class UpdateUserPasswordModel {

    /**
     * original password
     */
    private String originalPassword;

    /**
     * new password
     */
    private String newPassword;

}
