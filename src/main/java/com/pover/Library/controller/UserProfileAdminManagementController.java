package com.pover.Library.controller;

import com.pover.Library.dto.ExtendedUserProfileRequestDto;
import com.pover.Library.dto.ExtendedUserProfileResponseDto;
import com.pover.Library.service.UserService;
import com.pover.Library.validation.UpdateValidationGroup;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// ADMIN'S AND LIBRARIAN'S ACCESS TO USER'S PROFILE

@RestController
@RequestMapping("/api/admin/profile")
public class UserProfileAdminManagementController {

    private final UserService userService;

    public UserProfileAdminManagementController(UserService userService) {
        this.userService = userService;
    }

    //@PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    @GetMapping("/{memberNumber}")
    public ResponseEntity<ExtendedUserProfileResponseDto> getUserProfileByMemberNumber(@PathVariable String memberNumber) {
        ExtendedUserProfileResponseDto userProfile = userService.getUserProfileByMemberNumber(memberNumber);
        if (userProfile == null) {
            throw new RuntimeException("UserProfile not found for memberNumber: " + memberNumber);
        }
        return ResponseEntity.ok(userProfile);
    }

    //@PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    @PutMapping("/{memberNumber}")
    public ResponseEntity<ExtendedUserProfileResponseDto> updateUserProfileByMemberNumber(@PathVariable String memberNumber, @RequestBody @Validated(UpdateValidationGroup.class) ExtendedUserProfileRequestDto extendedUserProfileRequestDto) {
        extendedUserProfileRequestDto.setMember_number(memberNumber);
        ExtendedUserProfileResponseDto updatedProfile = userService.updateUserProfileByMemberNumber(extendedUserProfileRequestDto);
        return ResponseEntity.ok(updatedProfile);
    }
}
