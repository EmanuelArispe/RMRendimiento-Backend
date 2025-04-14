package com.RMRendimiento.UserRole;

import com.RMRendimiento.UserRole.dto.UserRoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    @Query("SELECT new com.RMRendimiento.UserRole.dto.UserRoleDTO (ru.id,ru.id_role,ru.id_user) FROM UserRole ru WHERE (ru.id) = (:id)")
    Optional<UserRoleDTO> findByUserRole(Long id);

    @Query("SELECT new com.RMRendimiento.UserRole.dto.UserRoleDTO(ru.id,ru.id_role,ru.id_user) FROM UserRole ru WHERE (ru.id_user)=(:id)")
    List<UserRoleDTO> findeByUserId(Long id);
}
