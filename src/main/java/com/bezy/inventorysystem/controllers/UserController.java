package com.bezy.inventorysystem.controllers;

import com.bezy.inventorysystem.mappers.UserMapper;
import com.bezy.inventorysystem.dtos.RegisterUserRequest;
import com.bezy.inventorysystem.dtos.UpdateUserRequest;
import com.bezy.inventorysystem.dtos.UserDto;
import com.bezy.inventorysystem.entities.User;
import com.bezy.inventorysystem.repositories.UserRepository;
import com.bezy.inventorysystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class  UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, UserMapper userMapper, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserRequest request
    ){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Username already exists");
        }

        if(userRepository.existsByEmail(request.getEmail())){
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setId(request.getId());
        user.setRole(request.getRole());
        userRepository.save(user);
        return ResponseEntity.ok("User has been created successfully!");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> editUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.ok("User not found!");
        }
        userMapper.update(request, user);
        userRepository.save(user);
        return ResponseEntity.ok("User has been updated successfully!");
    }

    @GetMapping("/users")
    public Iterable<UserDto> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(
            @PathVariable Long id
    ){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id
    ){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.ok("User has been deleted successfully!");
    }
}
