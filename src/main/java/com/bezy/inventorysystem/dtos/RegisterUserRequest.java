package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Role;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserRequest {
    private Long id;

    @NotNull(message = "Username is required!")
    private String username;

    @NotNull(message = "password is required!")
    private String password;

    @NotNull(message = "Confirm password is required!")
    private String confirmPassword;

    @AssertTrue(message = "Password do not match!")
    public boolean isPasswordMatching(){
        return password != null && password.equals(confirmPassword);
    }

    @Email(message = "Please provide a valid email")
    @NotNull(message = "Email is required!")
    private String email;

    private Role role;
}
