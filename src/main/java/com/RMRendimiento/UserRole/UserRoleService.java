package com.RMRendimiento.UserRole;

import com.RMRendimiento.UserRole.dto.UserRoleDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<UserRoleDTO> getAllUserRoles() {
        List<UserRole> listUserRoles =  userRoleRepository.findAll();
        List<UserRoleDTO> result = new ArrayList<>();

        listUserRoles.forEach(userRole -> {
            result.add(UserRoleDTO.builder()
                    .id(userRole.getId())
                    .id_role(userRole.getId_role())
                    .id_user(userRole.getId_user()).build());
        });
        return result;
    }

    public UserRoleDTO getUserRoleById(Long id) {
        UserRole userRole = userRoleRepository.findById(id).get();

        return UserRoleDTO.builder()
                .id(userRole.getId())
                .id_role(userRole.getId_role())
                .id_user(userRole.getId_user())
                .build();
    }

    public UserRole createUserRole(UserRoleDTO newUserRole)throws BadRequestException {
        Optional<UserRoleDTO> userRoleRepit = userRoleRepository.findByUserRole(newUserRole.getId());
        if (userRoleRepit.isPresent()) {
            throw new BadRequestException("El rol de usuario ya esta registrado");
        }
        UserRole userRole = UserRole.builder()
                .id_role(newUserRole.getId_role())
                .id_user(newUserRole.getId_user())
                .build();

        return userRoleRepository.save(userRole);
    }

    public UserRoleDTO deleteUserRole(Long id) {
        Optional<UserRole> userRole = userRoleRepository.findById(id);
        if(userRole.isPresent()) {
            UserRoleDTO userRoleDelete = UserRoleDTO.builder()
                    .id(userRole.get().getId())
                    .id_role(userRole.get().getId_role())
                    .id_user(userRole.get().getId_user())
                    .build();
            userRoleRepository.deleteById(id);
            return userRoleDelete;
        }
        return null;
    }
}
