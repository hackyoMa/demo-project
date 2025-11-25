package com.github.demoproject.user.model;

import lombok.Data;

/**
 * UpdateUserModel
 *
 * @author hackyo
 * @since 2022/4/1
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
