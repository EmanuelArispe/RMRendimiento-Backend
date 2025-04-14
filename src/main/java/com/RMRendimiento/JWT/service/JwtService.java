package com.RMRendimiento.JWT.service;

import com.RMRendimiento.JWT.dto.RoleTokenDTO;
import com.RMRendimiento.JWT.dto.UserTokenDTO;
import com.RMRendimiento.JWT.repository.JwtRepository;
import com.RMRendimiento.Role.Role;
import com.RMRendimiento.Role.RoleRepository;
import com.RMRendimiento.User.User;
import com.RMRendimiento.UserRole.UserRole;
import com.RMRendimiento.UserRole.UserRoleRepository;
import com.RMRendimiento.UserRole.dto.UserRoleDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtService {

    private final JwtRepository jwtRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;


    public JwtService(JwtRepository jwtRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
        this.jwtRepository = jwtRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<UserTokenDTO> findOneUserByName(String name) {

        Optional<User> userOptional = jwtRepository.findByName(name);
        List<RoleTokenDTO> roles = new ArrayList<>();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<UserRoleDTO> userRoleDTO = userRoleRepository.findeByUserId(user.getId());
            if(userRoleDTO.isEmpty()) {
                return Optional.empty();
            }
            for (UserRoleDTO ur : userRoleDTO ){
                roleRepository.findById(ur.getId_role()).ifPresent(role->
                        roles.add(new RoleTokenDTO(role.getId(),role.getRole())));
            }
            return Optional.of (UserTokenDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).roles(roles).build());
        } else {
            // If the user is not found, return throw an exception
            return Optional.empty();
        }

    }
}
