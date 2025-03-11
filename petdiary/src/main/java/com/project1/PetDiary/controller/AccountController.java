package com.project1.PetDiary.controller;

import com.project1.PetDiary.model.AccountUser;
import com.project1.PetDiary.model.LoginRequest;
import com.project1.PetDiary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody AccountUser user) {
        System.out.println(user.toString());
        return userService.register(user);
    }

    // 로그인 API 추가
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
