package com.RMRendimiento.JWT.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserTokenDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<RoleTokenDTO> roles;

}
