package com.gsmhrm.anything_back.domain.auth.repository;

import com.gsmhrm.anything_back.domain.auth.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, String> {
}
