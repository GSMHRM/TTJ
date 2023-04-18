package com.gsmhrm.anything_back.domain.users.repository;

import com.gsmhrm.anything_back.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String > {

    Optional<User> findUserByEmail(String email);
    Boolean ExistByEmail(String email);
}
