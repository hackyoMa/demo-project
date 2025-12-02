package com.github.demoproject.license.service;

import com.github.demoproject.license.entity.License;
import com.github.demoproject.license.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * LicenseService
 *
 * @author hackyo
 * @since 2022/4/1
 */
@Service
@RequiredArgsConstructor
public class LicenseService {

    private static final String PERSONAL_AUTHORIZATION = "Personal";

    private final LicenseRepository licenseRepository;

    public License getCurrentLicense() {
        License license = licenseRepository.findFirstByStartDateNotNullOrderByStartDateDesc();
        if (license != null) {
            return license;
        }
        return licenseRepository.findFirstByAuthorizedTo(PERSONAL_AUTHORIZATION);
    }

}
