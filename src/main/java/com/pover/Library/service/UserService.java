package com.pover.Library.service;

import com.pover.Library.dto.UserRequestDto;
import com.pover.Library.dto.UserResponseDto;
import com.pover.Library.model.User;
import com.pover.Library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserResponseDto> getUserByMemberNumber(String memberNumber) {
        User user = userRepository.findByMemberNumber(memberNumber).orElse(null);
        return userRepository.findByMemberNumber(memberNumber)
                .map(this::convertToDto);
    }


    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByMemberNumber(userRequestDto.getMember_number())) {
            throw new IllegalArgumentException("Entered personal number is already in the system");
        }

        User user = new User();
        user.setFirst_name(userRequestDto.getFirst_name());
        user.setLast_name(userRequestDto.getLast_name());
        user.setEmail(userRequestDto.getEmail());
        user.setMemberNumber(userRequestDto.getMember_number());
        userRepository.save(user);

        return convertToDto(user);
    }


    // konverterar User till UserResponseDto
    private UserResponseDto convertToDto(User user) {
        return new UserResponseDto(
                user.getUser_id(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getMemberNumber()
        );
    }
}
