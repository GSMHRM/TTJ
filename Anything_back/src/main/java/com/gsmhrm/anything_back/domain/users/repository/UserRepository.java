package com.gsmhrm.anything_back.domain.users.repository;

import com.gsmhrm.anything_back.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String > {


}
