package com.uno.hworld.repository;

import com.uno.hworld.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public String save(User user) {
        em.persist(user);
        return user.getUserId();
    }

    public User find(String userId) {
        return Optional.ofNullable(em.find(User.class, userId)).orElse(null);
    }
}
