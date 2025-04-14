package com.RMRendimiento.Role;

import com.RMRendimiento.Role.dto.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("SELECT new com.RMRendimiento.Role.dto.RoleDTO (r.id,r.role) FROM Role r WHERE lower(r.role) = lower(:role)")
    Optional<RoleDTO> findByRole(String role);

}
