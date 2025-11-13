package com.bezy.inventorysystem;

import com.bezy.inventorysystem.dtos.RegisterUserRequest;
import com.bezy.inventorysystem.dtos.UpdateUserRequest;
import com.bezy.inventorysystem.dtos.UserDto;
import com.bezy.inventorysystem.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
