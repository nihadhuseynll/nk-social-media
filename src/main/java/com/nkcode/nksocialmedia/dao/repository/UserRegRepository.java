package com.nkcode.nksocialmedia.dao.repository;

import com.nkcode.nksocialmedia.dao.entity.UserReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRegRepository extends JpaRepository<UserReg, UUID> {

    UserReg findByUsernameAndPassword(String username, String password);
}
