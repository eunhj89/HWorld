package com.uno.hworld.service;

import com.uno.hworld.common.UserAuth;
import com.uno.hworld.domain.User;
import com.uno.hworld.domain.UserSessionVo;
import com.uno.hworld.dto.UserDto;
import com.uno.hworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Transactional
    public void joinUser(UserDto userDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .userId(userDto.getUserId())
                .userPw(passwordEncoder.encode(userDto.getUserPw()))
                .userNm(userDto.getUserNm())
                .userAuth(UserAuth.USER)
                .build();
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("User is not found."));
        httpSession.setAttribute("user", new UserSessionVo(user));
        return user;
    }
}
