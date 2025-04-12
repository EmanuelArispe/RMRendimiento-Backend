package com.RMRendimiento.UserRole;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="user_rol_entity")
public class UserRole {

    @Id
    @Column(name="id_user_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_role")
    private Long id_role;

    @Column(name="id_user")
    private Long id_user;

}
