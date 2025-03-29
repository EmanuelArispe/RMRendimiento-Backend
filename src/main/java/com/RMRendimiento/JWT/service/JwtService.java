package com.RMRendimiento.JWT.service;

import com.RMRendimiento.JWT.dto.RoleTokenDTO;
import com.RMRendimiento.JWT.dto.UserTokenDTO;
import com.RMRendimiento.JWT.repository.JwtRepository;
import com.RMRendimiento.User.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtService {

    private final JwtRepository jwtRepository;

    public JwtService(JwtRepository jwtRepository) {
        this.jwtRepository = jwtRepository;
    }

    public Optional<UserTokenDTO> findOneUserByName(String name) {

        Optional<User> userOptional = jwtRepository.findByName(name);

        List<RoleTokenDTO> roles = new ArrayList<>();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getRoles().forEach(role -> roles.add(new RoleTokenDTO(role.getId(), role.getRole())));
            return Optional.of (UserTokenDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).roles(roles).build());
        } else {
            // If the user is not found, return throw an exception
            return Optional.empty();
        }

    }
}
