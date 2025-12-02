package com.github.demoproject.user.repository;

import com.github.demoproject.user.entity.UserRole;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRoleRepository
 *
 * @author 13712
 * @since 1.0.0
 */
@Repository
public interface UserRoleRepository extends JpaRepository<@NonNull UserRole, @NonNull String> {

    /**
     * deleteAllByRoleId
     *
     * @param roleId role id
     */
    @Modifying
    void deleteAllByRoleId(String roleId);

    /**
     * deleteAllByUserId
     *
     * @param userId user id
     */
    @Modifying
    void deleteAllByUserId(String userId);

    /**
     * findAllByUserIdIn
     *
     * @param userIds user ids
     * @return user role list
     */
    List<UserRole> findAllByUserIdIn(List<String> userIds);

}
