package com.pover.Library.controller;


import com.pover.Library.dto.UserRequestDto;
import com.pover.Library.dto.UserResponseDto;
import com.pover.Library.service.UserService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @GetMapping("/findByMemberNumber")
    public ResponseEntity<UserResponseDto> getUserByMemberNumber(@RequestParam String member_number) {
        if (member_number.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<UserResponseDto> userResponseDto  = userService.getUserByMemberNumber(member_number);

        return userResponseDto
                .map(responseDto -> new ResponseEntity<>(responseDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<UserResponseDto> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
