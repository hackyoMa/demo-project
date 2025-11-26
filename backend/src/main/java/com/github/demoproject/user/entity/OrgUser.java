package com.github.demoproject.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

/**
 * OrgUser
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Data
@Entity(name = "org_user")
@IdClass(OrgUser.class)
public class OrgUser {

    /**
     * org id
     */
    @Id
    private String orgId;

    /**
     * user id
     */
    @Id
    private String userId;

}
