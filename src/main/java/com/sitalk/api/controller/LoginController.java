package com.sitalk.api.controller;

import com.sitalk.api.dto.ApiResponse;
import com.sitalk.api.entity.User;
import com.sitalk.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public ApiResponse<?> findAllUsers () {
        List<User> users = userRepository.findAll();
        log.info("check users = {}", users);
        return ApiResponse.of(users);
    }
}
