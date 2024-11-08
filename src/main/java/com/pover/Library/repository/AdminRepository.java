package com.pover.Library.repository;

import com.pover.Library.model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;



public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByUsername( String username);



}
