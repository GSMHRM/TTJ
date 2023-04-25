package com.gsmhrm.anything_back.domain.auth.repository;

import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
