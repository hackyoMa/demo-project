package com.github.demoproject.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * UserTokenRespModel
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Data
@AllArgsConstructor
public class UserTokenRespModel {

    /**
     * token
     */
    private String token;

}
