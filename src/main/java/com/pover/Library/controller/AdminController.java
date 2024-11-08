package com.pover.Library.controller;

import com.pover.Library.dto.AdminRequestDto;
import com.pover.Library.dto.AdminResponseDto;
import com.pover.Library.model.Admin;
import com.pover.Library.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

@PostMapping("/create")
    public ResponseEntity<AdminResponseDto> create(@RequestBody AdminRequestDto adminRequestDto){
        AdminResponseDto adminResponseDto = adminService.createAdmin(adminRequestDto);
    //Service omvandlar admin till en responseDto
        return new ResponseEntity<>( adminResponseDto, HttpStatus.CREATED);
}

}
