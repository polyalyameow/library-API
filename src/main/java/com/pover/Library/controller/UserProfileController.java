package com.pover.Library.controller;

import com.pover.Library.dto.UserProfileRequestDto;
import com.pover.Library.dto.UserProfileResponseDto;
import com.pover.Library.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<UserProfileResponseDto> getUserProfile(@RequestHeader("Authorization") String token) {

        String jwtToken = token.substring(7);
        UserProfileResponseDto userProfile = userProfileService.getUserProfile(jwtToken);
        return ResponseEntity.ok(userProfile);
    }


    @PutMapping
    public ResponseEntity<UserProfileResponseDto> updateUserProfile(@RequestHeader("Authorization") String token,
                                                                    @RequestBody UserProfileRequestDto userProfileRequestDto) {

        String jwtToken = token.substring(7);
        UserProfileResponseDto updatedProfile = userProfileService.updateUserProfile(jwtToken, userProfileRequestDto);
        return ResponseEntity.ok(updatedProfile);
    }
}
