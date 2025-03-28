package com.RMRendimiento.User.dto;

import com.RMRendimiento.User.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Role> roles;

    public UserDTO(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = new ArrayList<>();
    }
}
