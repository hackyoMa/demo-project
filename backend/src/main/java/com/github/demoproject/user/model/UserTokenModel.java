package com.github.demoproject.user.model;

import lombok.Data;

import java.util.Date;

/**
 * UserTokenModel
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Data
public class UserTokenModel {

    /**
     * user agent
     */
    private String userAgent;

    /**
     * client ip
     */
    private String clientIp;

    /**
     * issued at
     */
    private Date issuedAt;

}
