package com.gsmhrm.anything_back.domain.email.repository;

import com.gsmhrm.anything_back.domain.email.entity.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, String> {

}
