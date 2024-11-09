package com.pover.Library.repository;

import com.pover.Library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByMemberNumber(String member_number);
}
