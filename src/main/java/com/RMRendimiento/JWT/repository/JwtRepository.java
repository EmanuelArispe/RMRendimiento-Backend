package com.RMRendimiento.JWT.repository;
import com.RMRendimiento.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JwtRepository extends JpaRepository<User,Long> {

    @Query("""
        FROM User u JOIN FETCH u.roles
        WHERE lower(u.name) = ?1
    """)
    Optional<User> findByName(String name);
}
