package com.pover.Library.controller;


import com.pover.Library.dto.AdminResponseDto;
import com.pover.Library.dto.MemberNumberRequestDto;
import com.pover.Library.dto.UserRequestDto;
import com.pover.Library.dto.UserResponseDto;
import com.pover.Library.service.UserService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto userRequestDto) {
            UserResponseDto userResponseDto = userService.createUser(userRequestDto);
            return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    // ange: http://localhost:8080/user/findByMemberNumber?member_number=some_value
//    @GetMapping("/findByMemberNumber")
//    public ResponseEntity<UserResponseDto> getUserByMemberNumber(@RequestParam String member_number) {
//        if (member_number.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Optional<UserResponseDto> userResponseDto  = userService.getUserByMemberNumber(member_number);
//
//        return userResponseDto
//                .map(responseDto -> new ResponseEntity<>(responseDto, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @GetMapping("/users")

    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<UserResponseDto> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable long id){
        UserResponseDto userResponseDto = userService.getUserById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/by-member-number")
    public ResponseEntity<UserResponseDto> getUserByMemberNumber(@RequestBody MemberNumberRequestDto requestDto) {
        UserResponseDto userResponse = userService.getUserByMemberNumber(requestDto.getMember_number());
        return ResponseEntity.ok(userResponse);
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


}
