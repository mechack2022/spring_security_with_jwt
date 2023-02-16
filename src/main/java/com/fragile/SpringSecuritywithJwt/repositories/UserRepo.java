package com.fragile.SpringSecuritywithJwt.repositories;


import com.fragile.SpringSecuritywithJwt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
