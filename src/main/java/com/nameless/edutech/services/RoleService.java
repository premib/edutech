package com.nameless.edutech.services;

import com.nameless.edutech.DTO.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTO> getAllRoles();
    Optional<RoleDTO> getRoleById(String id);
    RoleDTO saveRole(RoleDTO roleDTO);
    RoleDTO updateRole(String id, RoleDTO roleDTO);
    void deleteRole(String id);
}
