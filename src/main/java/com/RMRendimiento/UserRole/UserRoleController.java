package com.RMRendimiento.UserRole;

import com.RMRendimiento.UserRole.dto.UserRoleDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("api/user_rol")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public @ResponseBody ResponseEntity<?> getAllUserRoles() throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userRoleService.getAllUserRoles());
        } catch (Exception e) {
            throw new BadRequestException("Error al listar los roles de usuarios");
        }
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<?> getUserRoleById(@PathVariable(value = "id") Long id) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userRoleService.getUserRoleById(id));
        } catch (Exception e) {
            throw new BadRequestException("Error al listar el rol de usuario");
        }
    }

    @PostMapping
    public @ResponseBody ResponseEntity<?> createUserRole(@RequestBody UserRoleDTO newUserRole) throws BadRequestException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userRoleService.createUserRole(newUserRole));
        } catch (Exception e) {
            throw new BadRequestException("Error al crear el rol");
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> deleteUserRole(@PathVariable(value = "id") Long id) throws BadRequestException {
        try {
            Optional<UserRoleDTO> userRoleDelete = Optional.ofNullable(userRoleService.deleteUserRole(id));
            if(userRoleDelete.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(userRoleDelete.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol de usuario no encontrado");
        } catch (Exception e) {
            throw new BadRequestException("Error al eliminar el rol de usuario");
        }
    }

}
