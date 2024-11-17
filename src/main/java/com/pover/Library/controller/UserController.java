package com.pover.Library.controller;


import com.pover.Library.dto.*;
import com.pover.Library.service.UserService;
import com.pover.Library.validation.UpdateValidationGroup;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto userRequestDto) {
            UserResponseDto userResponseDto = userService.createUser(userRequestDto);
            return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<UserResponseDto> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable long id){
        UserResponseDto userResponseDto = userService.getUserById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String memberNumber = credentials.get("member_number");
        String password = credentials.get("password");

        if (memberNumber == null || password == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Missing required fields: member mumber and/or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        Optional<String> token = userService.authenticateUser(memberNumber, password);
        return token
                .map(t -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("token", t);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, String> errorResponse = new HashMap<>();
                    errorResponse.put("error", "Invalid credentials");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
                });
    }

    // CONTROLLERS FOR USER PROFILE CONTROLLED BY USER
    // user's id isn't needed because of token
    // the profile of the currently authenticated user will be returned

    @GetMapping("/profile")
    public ResponseEntity<BasicUserProfileResponseDto> getUserProfile(@RequestHeader("Authorization") String token) {

        String jwtToken = token.substring(7);
        BasicUserProfileResponseDto userProfile = userService.getUserProfile(jwtToken);
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/profile")
    public ResponseEntity<BasicUserProfileResponseDto> updateUserProfile(@RequestHeader("Authorization") String token,
                                                                         @RequestBody BasicUserProfileRequestDto basicUserProfileRequestDto) {

        String jwtToken = token.substring(7);
        BasicUserProfileResponseDto updatedProfile = userService.updateUserProfile(jwtToken, basicUserProfileRequestDto);
        return ResponseEntity.ok(updatedProfile);
    }
}
