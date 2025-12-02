package com.github.demoproject.user.repository;

import com.github.demoproject.user.entity.Role;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository
 *
 * @author hackyo
 * @since 1.0.0
 */
@Repository
public interface RoleRepository extends JpaRepository<@NonNull Role, @NonNull String> {

    /**
     * existsByNameAndIdNot
     *
     * @param name name
     * @param id   role id
     * @return exists
     */
    boolean existsByNameAndIdNot(String name, String id);

    /**
     * existsByName
     *
     * @param name name
     * @return exists
     */
    boolean existsByName(String name);

}
