package com.github.demoproject.license.repository;

import com.github.demoproject.license.entity.License;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * LicenseRepository
 *
 * @author hackyo
 * @since 1.0.0
 */
@Repository
public interface LicenseRepository extends JpaRepository<@NonNull License, @NonNull String> {

    /**
     * findFirstByStartDateNotNullOrderByStartDateDesc
     *
     * @return license
     */
    License findFirstByStartDateNotNullOrderByStartDateDesc();

    /**
     * findFirstByAuthorizedTo
     *
     * @param authorizedTo authorized to
     * @return license
     */
    License findFirstByAuthorizedTo(String authorizedTo);

}
