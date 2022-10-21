package com.uno.hworld.repository;

import com.uno.hworld.domain.User;
import com.uno.hworld.common.UserAuth;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(true)
    public void testUser() {
        User user = User.builder()
                        .userId("test").userNm("test").userPw("qwer1234").userAuth(UserAuth.USER).build();
        User savedUser = userRepository.save(user);
        Optional<User> findUser = userRepository.findByUserId(savedUser.getUserId());

        Assertions.assertThat(findUser.get().getUserId()).isEqualTo(user.getUserId());
        Assertions.assertThat(findUser.get().getUserPw()).isEqualTo(user.getUserPw());
    }
}
