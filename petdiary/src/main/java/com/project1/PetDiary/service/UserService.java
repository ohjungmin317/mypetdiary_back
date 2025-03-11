package com.project1.PetDiary.service;

import com.project1.PetDiary.model.AccountUser;
import com.project1.PetDiary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입 메서드
    public String register(AccountUser user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword())); // 현재 상황에서는 힘들다 ㅠㅠ
        userRepository.save(user);
        return "User registered successfully";
    }

    // 로그인 메서드
    public String login(String email, String password) {
        AccountUser user = userRepository.findByEmail(email);
        if (user != null) {
            return "Login successful";
        } else {
            return "Invalid email or password";
        }
    }
}
