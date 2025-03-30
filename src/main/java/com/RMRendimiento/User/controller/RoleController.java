package com.RMRendimiento.User.controller;

import com.RMRendimiento.User.dto.RoleDTO;
import com.RMRendimiento.User.service.RoleService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')") // para dar permisos a los distintos roles
    public @ResponseBody ResponseEntity<?> getAllRoles() throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles());
        } catch (Exception e) {
            throw new BadRequestException("Error al listar los roles");
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public @ResponseBody ResponseEntity<?> getRoleById(@PathVariable(value = "id") Long id) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(roleService.getRoleById(id));
        } catch (Exception e) {
            throw new BadRequestException("Error al listar el usuario");
        }
    }

    @PostMapping
    public @ResponseBody ResponseEntity<?> createRole(@RequestBody RoleDTO newRole) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(newRole));
        } catch (Exception e) {
            throw new BadRequestException("Error al crear el rol");
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> deleteRole(@PathVariable(value = "id") Long id) throws BadRequestException {
        try {
            Optional<RoleDTO> roleDelete = Optional.ofNullable(roleService.deleteRole(id));
            if(roleDelete.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(roleDelete.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        } catch (Exception e) {
            throw new BadRequestException("Error al eliminar el rol");
        }
    }
}
