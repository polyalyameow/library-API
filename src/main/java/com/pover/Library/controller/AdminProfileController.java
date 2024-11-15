package com.pover.Library.controller;

import com.pover.Library.dto.AdminProfileRequestDto;
import com.pover.Library.dto.AdminProfileResponseDto;
import com.pover.Library.dto.MemberNumberRequestDto;
import com.pover.Library.service.AdminProfileService;
import com.pover.Library.validation.UpdateValidationGroup;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.pover.Library.validation.UpdateValidationGroup;

@RestController
@RequestMapping("/admin/user-info")
@Validated
public class AdminProfileController {

    private final AdminProfileService adminProfileService;

    public AdminProfileController(AdminProfileService adminProfileService) {
        this.adminProfileService = adminProfileService;
    }

    @PostMapping
    public ResponseEntity<AdminProfileResponseDto> getUserProfileByMemberNumber(@RequestBody MemberNumberRequestDto requestDto) {
        AdminProfileResponseDto userProfile = adminProfileService.getUserProfileByMemberNumber(requestDto.getMember_number());
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping
    public ResponseEntity<AdminProfileResponseDto> updateUserProfileByMemberNumber(@RequestBody @Validated(UpdateValidationGroup.class) AdminProfileRequestDto adminProfileRequestDto) {
        AdminProfileResponseDto updatedProfile = adminProfileService.updateUserProfileByMemberNumber(adminProfileRequestDto);
        return ResponseEntity.ok(updatedProfile);
    }
}
