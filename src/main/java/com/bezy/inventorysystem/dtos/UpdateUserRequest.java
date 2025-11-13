package com.bezy.inventorysystem.dtos;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String username;
    private String password;
    private String email;
}
