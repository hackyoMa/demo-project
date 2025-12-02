package com.github.demoproject.user.repository;

import com.github.demoproject.user.entity.OrgUser;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrgUserRepository
 *
 * @author hackyo
 * @since 1.0.0
 */
@Repository
public interface OrgUserRepository extends JpaRepository<@NonNull OrgUser, @NonNull String> {

    /**
     * existsByOrgId
     *
     * @param orgId org id
     * @return exists
     */
    boolean existsByOrgId(String orgId);

    /**
     * findAllByOrgIdIn
     *
     * @param orgId org ids
     * @return org user list
     */
    List<OrgUser> findAllByOrgIdIn(List<String> orgId);

    /**
     * deleteAllByUserId
     *
     * @param userId user id
     */
    @Modifying
    void deleteAllByUserId(String userId);

}
