package com.RMRendimiento.User.controller;

import com.RMRendimiento.User.dto.UserCreateDTO;
import com.RMRendimiento.User.dto.UserDTO;
import com.RMRendimiento.User.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller("UserController")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public @ResponseBody ResponseEntity<?> getAllUsers() throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
        } catch (Exception e) {
            throw new BadRequestException("Error al listar los usuarios");
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
        } catch (Exception e) {
            throw new BadRequestException("Error al listar el usuario");
        }
    }

    @PostMapping
    public @ResponseBody ResponseEntity<?> createUser(@RequestBody UserCreateDTO newUser) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(newUser));
        } catch (Exception e) {
            throw new BadRequestException("Error al crear el usuario");
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) throws BadRequestException {
        try {
            Optional<UserDTO> user = Optional.ofNullable(userService.deleteUser(id));
            if (user.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(user.get());

            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (Exception e) {
            throw new BadRequestException("Error al eliminar el usuario");
        }
    }
}
