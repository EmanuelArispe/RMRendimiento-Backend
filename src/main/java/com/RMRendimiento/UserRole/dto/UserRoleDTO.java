package com.RMRendimiento.UserRole.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRoleDTO {

    private Long id;
    private Long id_role;
    private Long id_user;
}
