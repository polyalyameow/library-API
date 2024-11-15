package com.pover.Library.service;

import com.pover.Library.JWT.JwtUtil;
import com.pover.Library.dto.*;
import com.pover.Library.model.User;
import com.pover.Library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminProfileService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminProfileService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AdminProfileResponseDto getUserProfileByMemberNumber(String memberNumber) {
        User user = userRepository.findByMemberNumber(memberNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<LoanResponseDto> activeLoans = user.getLoans().stream()
                .filter(loan -> loan.getReturnedDate() == null)
                .map(LoanResponseDto::new)
                .collect(Collectors.toList());

        return new AdminProfileResponseDto(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword(), user.getMemberNumber(), activeLoans);
    }

    @Transactional
    public AdminProfileResponseDto updateUserProfileByMemberNumber(AdminProfileRequestDto adminProfileRequestDto) {
        String memberNumber = adminProfileRequestDto.getMember_number();

        User user = userRepository.findByMemberNumber(memberNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (adminProfileRequestDto.getFirst_name() != null && !adminProfileRequestDto.getFirst_name().isBlank()) {
            user.setFirst_name(adminProfileRequestDto.getFirst_name());
        } else if (adminProfileRequestDto.getFirst_name() != null) {
            throw new IllegalArgumentException("First name cannot be blank");
        }

        if (adminProfileRequestDto.getLast_name() != null && !adminProfileRequestDto.getLast_name().isBlank()) {
            user.setLast_name(adminProfileRequestDto.getLast_name());
        } else if (adminProfileRequestDto.getLast_name() != null) {
            throw new IllegalArgumentException("Last name cannot be blank");
        }

        if (adminProfileRequestDto.getEmail() != null && !adminProfileRequestDto.getEmail().isBlank()) {
            user.setEmail(adminProfileRequestDto.getEmail());
        } else if (adminProfileRequestDto.getEmail() != null) {
            throw new IllegalArgumentException("Email cannot be blank");
        }

        if (adminProfileRequestDto.getPassword() != null && !adminProfileRequestDto.getPassword().isBlank()) {
            if (!adminProfileRequestDto.getPassword().matches("^\\d{4}$")) {
                throw new IllegalArgumentException("Password must be 4 digits");
            }
            String hashedPassword = passwordEncoder.encode(adminProfileRequestDto.getPassword());
            user.setPassword(hashedPassword);
        }


        userRepository.save(user);

        List<LoanResponseDto> activeLoans = user.getLoans().stream()
                .filter(loan -> loan.getReturnedDate() == null)
                .map(LoanResponseDto::new)
                .collect(Collectors.toList());

        return new AdminProfileResponseDto(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword(), user.getMemberNumber(), activeLoans);
    }
}
