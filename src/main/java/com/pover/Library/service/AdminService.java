package com.pover.Library.service;

import com.pover.Library.dto.AdminRequestDto;
import com.pover.Library.dto.AdminResponseDto;
import com.pover.Library.model.Admin;
import com.pover.Library.repository.AdminRepository;
import org.springframework.stereotype.Service;


@Service
public class AdminService {
    private final AdminRepository adminRepository;
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminResponseDto createAdmin(AdminRequestDto adminRequestDto) {
        if(adminRepository.existsByUsername(adminRequestDto.getUsername())){
            throw new IllegalArgumentException("Email already exists");
        }
        Admin admin = new Admin();
        admin.setUsername(adminRequestDto.getUsername());
        admin.setPassword(adminRequestDto.getPassword());
        admin.setRole(adminRequestDto.getRole());
        adminRepository.save(admin);

        return new AdminResponseDto(admin.getAdmin_id(), admin.getUsername(), admin.getRole());
    }

}
