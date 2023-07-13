package com.gsmhrm.anything_back.domain.auth.repository;

import com.gsmhrm.anything_back.domain.auth.entity.KakaoAuth;
import org.springframework.data.repository.CrudRepository;

public interface KakaoAuthRepository extends CrudRepository<KakaoAuth, String> {
}
