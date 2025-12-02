package com.github.demoproject.user.model;

import lombok.Data;

import java.util.Date;

/**
 * UserTokenModel
 *
 * @author hackyo
 * @since 1.0.0
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
     * issue time
     */
    private Date issueTime;

}
