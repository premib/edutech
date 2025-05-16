package com.nameless.edutech.services.impl;

import com.nameless.edutech.DTO.RoleDTO;
import com.nameless.edutech.models.Role;
import com.nameless.edutech.repositories.RoleRepository;
import com.nameless.edutech.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    public final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDTO> getRoleById(String id) {
        return roleRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        Role savedRole = roleRepository.save(role);

        return convertToDTO(savedRole);
    }

    @Override
    public RoleDTO updateRole(String id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with ID: " + id));

        role.setName(roleDTO.name());
        role.setDescription(roleDTO.description());

        Role updatedRole = roleRepository.save(role);

        return convertToDTO(updatedRole);
    }

    @Override
    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }

    private RoleDTO convertToDTO(Role role) {
        return new RoleDTO(
                role.getName(),
                role.getDescription()
        );
    }

    private Role convertToEntity(RoleDTO dto) {
        return Role.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
    }
}
