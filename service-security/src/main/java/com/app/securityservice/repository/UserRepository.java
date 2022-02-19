package com.app.securityservice.repository;

import java.util.Optional;

import com.app.securityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsUserByEmail(String email);

    boolean existsUserByEmailAndIdNot(String email, String id);

    boolean existsUserByUsername(String name);

    boolean existsUserByUsernameAndIdNot(String name, String id);

    Optional<User> findByEmail(String email);

}
