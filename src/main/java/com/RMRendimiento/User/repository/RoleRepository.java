package com.RMRendimiento.User.repository;

import com.RMRendimiento.User.dto.RoleDTO;
import com.RMRendimiento.User.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("SELECT new com.RMRendimiento.User.dto.RoleDTO (r.role) FROM Role r WHERE lower(r.role) = lower(:role)")
    Optional<RoleDTO> findByRole(String role);

}
