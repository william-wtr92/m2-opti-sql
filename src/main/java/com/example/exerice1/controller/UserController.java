package com.example.exerice1.controller;

import com.example.exerice1.dto.UserDto;
import com.example.exerice1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(
            @Valid @RequestBody UserDto userDto
    ) {
        userService.createUser(userDto);

        return ResponseEntity.ok("User created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable Long id
    ) {
        UserDto userDto = userService.getUserById(id);

        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDto userDto
    ) {
        userService.updateUser(id, userDto);

        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);

        return ResponseEntity.ok("User deleted");
    }

}

