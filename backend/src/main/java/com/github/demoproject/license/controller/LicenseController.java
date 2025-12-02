package com.github.demoproject.license.controller;

import com.github.demoproject.license.entity.License;
import com.github.demoproject.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LicenseController
 *
 * @author hackyo
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/license", version = "1.0.0+")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    /**
     * get current license
     *
     * @return license
     */
    @GetMapping("/current")
    @PreAuthorize("hasAuthority('license:read')")
    public License getCurrentLicense() {
        return licenseService.getCurrentLicense();
    }

}
