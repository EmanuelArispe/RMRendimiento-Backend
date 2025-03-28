package com.RMRendimiento.User.repository;

import com.RMRendimiento.User.dto.UserDTO;
import com.RMRendimiento.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT new com.RMRendimiento.User.dto.UserDTO (u.id, u.name, u.email,u.password) FROM User u WHERE lower(u.email) = lower(:email)")
    Optional<UserDTO> findByEmail(String email);

}
