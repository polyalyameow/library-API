package com.pover.Library.service;

import com.pover.Library.JWT.JwtUtil;
import com.pover.Library.dto.UserRequestDto;
import com.pover.Library.dto.UserResponseDto;
import com.pover.Library.model.User;
import com.pover.Library.model.enums.Role;
import com.pover.Library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<String> authenticateUser(String memberNumber, String password) {
        Optional<User> existingUser = userRepository.findByMemberNumber(memberNumber);

        if (existingUser.isPresent() && passwordEncoder.matches(password, existingUser.get().getPassword())) {
            String token = jwtUtil.generateToken(existingUser.get().getUser_id(), existingUser.get().getRole(), null, existingUser.get().getMemberNumber());
            return Optional.of(token);
        }
        return Optional.empty();
    }

//    public Optional<UserResponseDto> getUserByMemberNumber(String memberNumber) {
//        User user = userRepository.findByMemberNumber(memberNumber).orElse(null);
//        return userRepository.findByMemberNumber(memberNumber)
//                .map(this::convertToDto);
//    }

    public UserResponseDto getUserById(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDto(user);
    }

    public UserResponseDto getUserByMemberNumber(String memberNumber) {
        User user = userRepository.findByMemberNumber(memberNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponseDto(user);
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

        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        System.out.println("Encoded password: " + encodedPassword);
        user.setPassword(encodedPassword);

        user.setRole(Role.USER);

        userRepository.save(user);

        return convertToDto(user);
    }


    // konverterar User till UserResponseDto
    private UserResponseDto convertToDto(User user) {
        return new UserResponseDto(user);
    }


    public List<UserResponseDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user-> new UserResponseDto(user))
                .collect(Collectors.toList());

    }
}
