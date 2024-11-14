package com.pover.Library.controller;

import com.pover.Library.JWT.JwtUtil;
import com.pover.Library.dto.*;

import com.pover.Library.model.Admin;
import com.pover.Library.service.AdminService;
import com.pover.Library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AdminController(AdminService adminService, JwtUtil jwtUtil, UserService userService){
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }



    @Operation(summary = "Create a new Admin", description = "Creates a new admin user with a unique username")
    @PostMapping("/create")
    public ResponseEntity<AdminResponseDto> create(@RequestBody AdminRequestDto adminRequestDto){
        AdminResponseDto adminResponseDto = adminService.createAdmin(adminRequestDto);

    //Service omvandlar admin till en responseDto
        return new ResponseEntity<>( adminResponseDto, HttpStatus.CREATED);
}

    @GetMapping("/get")
    public ResponseEntity<List<AdminResponseDto>> getAll(){
        List<AdminResponseDto> admins = adminService.getAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AdminResponseDto> getById(@PathVariable long id){
        AdminResponseDto adminResponseDto = adminService.getAdminById(id);
        return new ResponseEntity<>(adminResponseDto, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseAdminLoginDto> login(@RequestBody AdminRequestDto adminRequestDto){
        Admin admin = adminService.authenticate(adminRequestDto.getUsername(), adminRequestDto.getPassword());

        if(admin == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtil.generateToken(admin.getAdmin_id(),admin.getRole(), admin.getUsername(), null);

        ResponseAdminLoginDto responseAdminLoginDto = new ResponseAdminLoginDto(token);
        return new ResponseEntity<>(responseAdminLoginDto, HttpStatus.OK);
    }

   // @DeleteMapping("/delete/{id}")
    //public ResponseEntity<AdminResponseDto> delete(@PathVariable long id, @AuthenticationPrincipal Admin currentAdmin){
      //  AdminResponseDto adminResponseDto = adminService.deleteAdminById(id, currentAdmin);
        //return new ResponseEntity<>(adminResponseDto, HttpStatus.OK);
    //}

    @PostMapping("/by-member-number")
    public ResponseEntity<UserResponseDto> getUserByMemberNumber(@RequestBody MemberNumberRequestDto requestDto) {
        UserResponseDto userResponse = userService.getUserByMemberNumber(requestDto.getMember_number());
        return ResponseEntity.ok(userResponse);
    }
}
