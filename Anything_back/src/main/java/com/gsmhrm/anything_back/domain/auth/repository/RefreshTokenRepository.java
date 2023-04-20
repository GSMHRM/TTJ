package com.gsmhrm.anything_back.domain.auth.repository;

import com.gsmhrm.anything_back.domain.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
