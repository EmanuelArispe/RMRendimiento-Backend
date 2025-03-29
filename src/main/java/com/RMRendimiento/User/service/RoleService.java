package com.RMRendimiento.User.service;

import com.RMRendimiento.User.dto.RoleDTO;
import com.RMRendimiento.User.entity.Role;
import com.RMRendimiento.User.repository.RoleRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> getAllRoles() {
        List<Role> listRoles =  roleRepository.findAll();
        List<RoleDTO> result = new ArrayList<>();

        listRoles.forEach(role -> {
            result.add(RoleDTO.builder()
                    .id(role.getId())
                    .name(role.getRole())
                    .build());
        });
        return result;
    }

    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id).get();  // Ver si esta presente el rol

        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getRole())
                .build();
    }

    public Role createRole(RoleDTO newRole)throws BadRequestException {
        Optional<RoleDTO> roleRepit = roleRepository.findByRole(newRole.getName());
        if (roleRepit.isPresent()) {
            throw new BadRequestException("El rol ya esta registrado");
        }
        Role role = Role.builder()
                .role(newRole.getName())
                .build();

        return roleRepository.save(role);
    }

    public RoleDTO deleteRole(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()) {
            RoleDTO roleDelete = RoleDTO.builder()
                    .id(role.get().getId())
                    .name(role.get().getRole())
                    .build();
            roleRepository.deleteById(id);
            return roleDelete;
        }
        return null;
    }
}
