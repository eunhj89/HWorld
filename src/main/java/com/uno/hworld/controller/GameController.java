package com.uno.hworld.controller;

import com.uno.hworld.dto.UserDto;
import com.uno.hworld.exception.BusinessException;
import com.uno.hworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class GameController {

    private final UserService userService;

    @GetMapping("/index")
    public String root(Model model) throws BusinessException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("data", userService.findUser(userId).getUserNm());
        return "index";
    }
}
