package com.gsmhrm.anything_back.domain.users.repository;

import com.gsmhrm.anything_back.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByEmail(String email);
    Boolean existsByEmail(String email);
}
