package com.github.demoproject.user.controller;

import com.github.demoproject.user.entity.Permission;
import com.github.demoproject.user.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PermissionController
 *
 * @author hackyo
 * @since 2022/4/1
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    /**
     * get permission list
     *
     * @param basics is basics
     * @return permission list
     */
    @GetMapping
    @PreAuthorize("hasAuthority('role:read')")
    public List<Permission> get(@RequestParam(required = false) Boolean basics) {
        return permissionService.get(basics);
    }

}
