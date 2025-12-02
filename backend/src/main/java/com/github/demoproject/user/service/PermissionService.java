package com.github.demoproject.user.service;

import com.github.demoproject.user.entity.Permission;
import com.github.demoproject.user.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PermissionService
 *
 * @author hackyo
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public List<Permission> get(Boolean basics) {
        if (basics == null) {
            return permissionRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        } else {
            return permissionRepository.findAllByBasicsOrderByNameAsc(basics);
        }
    }

}
