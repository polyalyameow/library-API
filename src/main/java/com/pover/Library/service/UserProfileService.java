package com.pover.Library.service;

import com.pover.Library.JWT.JwtUtil;
import com.pover.Library.dto.LoanResponseDto;
import com.pover.Library.dto.UserProfileRequestDto;
import com.pover.Library.dto.UserProfileResponseDto;
import com.pover.Library.model.User;
import com.pover.Library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserProfileService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserProfileResponseDto getUserProfile(String token) {
        String memberNumber = jwtUtil.extractMemberNumber(token);

        User user = userRepository.findByMemberNumber(memberNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<LoanResponseDto> activeLoans = user.getLoans().stream()
                .filter(loan -> loan.getReturnedDate() == null)
                .map(LoanResponseDto::new)
                .collect(Collectors.toList());

        return new UserProfileResponseDto(user.getFirst_name(), user.getLast_name(), user.getEmail(), activeLoans);
    }

    @Transactional
    public UserProfileResponseDto updateUserProfile(String token, UserProfileRequestDto userProfileRequestDto) {
        String memberNumber = jwtUtil.extractMemberNumber(token);

        User user = userRepository.findByMemberNumber(memberNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (userProfileRequestDto.getFirst_name() != null) {
            user.setFirst_name(userProfileRequestDto.getFirst_name());
        }
        if (userProfileRequestDto.getLast_name() != null) {
            user.setLast_name(userProfileRequestDto.getLast_name());
        }
        if (userProfileRequestDto.getEmail() != null) {
            user.setEmail(userProfileRequestDto.getEmail());
        }

        userRepository.save(user);

        List<LoanResponseDto> activeLoans = user.getLoans().stream()
                .filter(loan -> loan.getReturnedDate() == null)
                .map(LoanResponseDto::new)
                .collect(Collectors.toList());

        return new UserProfileResponseDto(user.getFirst_name(), user.getLast_name(), user.getEmail(), activeLoans);
    }

}
