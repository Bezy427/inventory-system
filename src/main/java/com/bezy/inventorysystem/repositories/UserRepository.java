package com.bezy.inventorysystem.repositories;

import com.bezy.inventorysystem.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@NotBlank(message = "Email is required!") @Email String email);

    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);

    String email(String email);
}