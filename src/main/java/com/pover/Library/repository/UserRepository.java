package com.pover.Library.repository;

import com.pover.Library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByMemberNumber(String member_number);

    Optional<User> findByMemberNumber(String member_number);
}
