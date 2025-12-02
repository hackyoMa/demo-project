package com.github.demoproject.user.service;

import com.github.demoproject.common.HttpException;
import com.github.demoproject.user.entity.Permission;
import com.github.demoproject.user.entity.Role;
import com.github.demoproject.user.entity.RolePermission;
import com.github.demoproject.user.repository.PermissionRepository;
import com.github.demoproject.user.repository.RolePermissionRepository;
import com.github.demoproject.user.repository.RoleRepository;
import com.github.demoproject.user.repository.UserRoleRepository;
import com.github.demoproject.util.I18n;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * RoleService
 *
 * @author hackyo
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final UserRoleRepository userRoleRepository;
    private final PermissionRepository permissionRepository;

    public List<Role> get() {
        return roleRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Transactional(rollbackFor = HttpException.class)
    public Role add(Role role, List<String> permissionIds) {
        if (roleRepository.existsByName(role.getName())) {
            throw new HttpException(I18n.get("roleNameExits"));
        }
        role.setId(null);
        role.setSystemRole(false);
        role = roleRepository.save(role);
        saveRolePermission(role.getId(), permissionIds);
        return role;
    }

    @Transactional(rollbackFor = HttpException.class)
    public Role update(Role role, List<String> permissionIds) {
        if (roleRepository.existsByNameAndIdNot(role.getName(), role.getId())) {
            throw new HttpException(I18n.get("roleNameExits"));
        }
        Role oldRole = roleRepository.findById(role.getId()).orElseThrow();
        oldRole.setName(role.getName());
        if (!StringUtils.hasLength(role.getDescription())) {
            oldRole.setDescription(null);
        } else {
            oldRole.setDescription(role.getDescription());
        }
        role = roleRepository.save(oldRole);
        if (!role.getSystemRole()) {
            saveRolePermission(role.getId(), permissionIds);
        }
        return role;
    }

    private void saveRolePermission(String roleId, List<String> permissionIds) {
        rolePermissionRepository.deleteAllByRoleId(roleId);
        List<Permission> permissions = permissionRepository.findAllByIdInAndBasics(permissionIds, false);
        List<RolePermission> rolePermissions = new ArrayList<>(permissions.size());
        for (Permission permission : permissions) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permission.getId());
            rolePermissions.add(rolePermission);
        }
        rolePermissionRepository.saveAll(rolePermissions);
    }

    @Transactional(rollbackFor = HttpException.class)
    public void delete(String roleId) {
        if (!StringUtils.hasLength(roleId)) {
            return;
        }
        Role role = roleRepository.findById(roleId).orElseThrow();
        if (role.getSystemRole()) {
            throw new HttpException(I18n.get("systemRoleCannotDeleted"));
        }
        rolePermissionRepository.deleteAllByRoleId(roleId);
        userRoleRepository.deleteAllByRoleId(roleId);
        roleRepository.deleteById(roleId);
    }

    public List<String> getRolePermission(String roleId) {
        List<Permission> permissions = permissionRepository.findAllByRoleId(roleId);
        return permissions.stream().map(Permission::getId).toList();
    }

}
