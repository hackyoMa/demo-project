package com.github.demoproject.user.model;

import lombok.Data;

/**
 * UpdateUserPasswordModel
 *
 * @author hackyo
 * @since 2022/4/1
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
