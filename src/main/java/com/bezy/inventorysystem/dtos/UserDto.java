package com.bezy.inventorysystem.dtos;

import com.bezy.inventorysystem.entities.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
}
