package com.uno.hworld.controller;

import com.uno.hworld.domain.User;
import com.uno.hworld.dto.UserDto;
import com.uno.hworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @PostMapping("/signUp")
    public String signUp(UserDto userDto) {
        userService.joinUser(userDto);
        return "redirect:/login";
    }

    @GetMapping("/userAccess")
    public String userAccess(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("info", user.getUserNm() + "님" );
        return "userAccess";
    }
}
