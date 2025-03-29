package com.RMRendimiento.User.service;

import com.RMRendimiento.User.dto.UserCreateDTO;
import com.RMRendimiento.User.dto.UserDTO;
import com.RMRendimiento.User.entity.Role;
import com.RMRendimiento.User.entity.User;
import com.RMRendimiento.User.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("UserService")
public class UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> result = new ArrayList<>();


        users.forEach(user -> {
            result.add(UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRoles())
                    .build());
        });
        return result;
    }

    public UserDTO getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get(); // Ver si esta presente el usuario

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }

    public User createUser(UserCreateDTO newUser) throws BadRequestException {
        Optional<UserDTO> userRepit = userRepository.findByEmail(newUser.getEmail());

        if (userRepit.isPresent()) {
            throw new BadRequestException("El email ya esta registrado");
        }

        User user = User.builder()
                .name(newUser.getName())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword())).build();

        return userRepository.save(user);
    }

    public UserDTO deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return UserDTO.builder()
                    .id(userOptional.get().getId())
                    .name(userOptional.get().getName())
                    .email(userOptional.get().getEmail())
                    .password(userOptional.get().getPassword())
                    .roles(userOptional.get().getRoles())
                    .build();
        }
        return null;
    }
}