package com.github.demoproject.license.repository;

import com.github.demoproject.license.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * LicenseRepository
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Repository
public interface LicenseRepository extends JpaRepository<License, String> {

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
