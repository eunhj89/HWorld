package com.uno.hworld.repository;

import com.uno.hworld.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserId(String userId);

    User save(User user);
}
