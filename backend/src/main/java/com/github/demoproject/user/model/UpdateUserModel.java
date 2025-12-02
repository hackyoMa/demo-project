package com.github.demoproject.user.model;

import lombok.Data;

/**
 * UpdateUserModel
 *
 * @author hackyo
 * @since 1.0.0
 */
@Data
public class UpdateUserModel {

    /**
     *
     */
    private String id;

    /**
     * username
     */
    private String username;

    /**
     * name
     */
    private String name;

    /**
     * email
     */
    private String email;

    /**
     * area code
     */
    private String areaCode;

    /**
     * phone
     */
    private String phone;

}
