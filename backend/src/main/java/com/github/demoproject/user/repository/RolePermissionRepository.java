package com.github.demoproject.user.repository;

import com.github.demoproject.user.entity.RolePermission;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

/**
 * RolePermissionRepository
 *
 * @author hackyo
 * @since 1.0.0
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<@NonNull RolePermission, @NonNull String> {

    /**
     * deleteAllByRoleId
     *
     * @param roleId role id
     */
    @Modifying
    void deleteAllByRoleId(String roleId);

}
