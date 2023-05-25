package com.gsmhrm.anything_back.domain.auth.repository;

import com.gsmhrm.anything_back.domain.auth.entity.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, String> {
}
