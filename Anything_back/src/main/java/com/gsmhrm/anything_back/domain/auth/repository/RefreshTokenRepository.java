package com.gsmhrm.anything_back.domain.auth.repository;

import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}