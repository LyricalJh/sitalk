package com.sitalk.api.controller;

import com.sitalk.api.dto.ApiResponse;
import com.sitalk.api.entity.User;
import com.sitalk.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final UserRepository userRepository;

    @PostMapping("/oauth/kakao")
    public ApiResponse<?> oAuthKakao(@RequestBody String code) {
        log.info("get code from client = {}", code);
        return ApiResponse.of(code);
    }

    @GetMapping("/users")
    public ApiResponse<?> findAllUsers () {
        List<User> users = userRepository.findAll();
        log.info("check users = {}", users);
        return ApiResponse.of(users);
    }

    @PostMapping("/login")
    public ApiResponse<?> login() {
        return ApiResponse.of("welcome");
    }

}
