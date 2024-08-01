package com.sitalk.api.controller;

import com.sitalk.api.controller.common.OAuthTemplate;
import com.sitalk.api.dto.ApiResponse;
import com.sitalk.api.dto.LoginDto;
import com.sitalk.api.entity.User;
import com.sitalk.api.enums.ErrorCodeEnum;
import com.sitalk.api.exception.ApiException;
import com.sitalk.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final UserRepository userRepository;

    private final Map<String, OAuthTemplate> oAuthTemplates;

    @PostMapping("/oauth/{provider}")
    public ApiResponse<?> oAuthKakao(@PathVariable String provider, @RequestBody String code) {
        log.info("check loginDto = {}", code);

        OAuthTemplate oAuthTemplate = oAuthTemplates.get(provider);
        if (oAuthTemplate == null) {
             throw new ApiException(ErrorCodeEnum.INTERNAL_SERVER_ERROR, "해당 로그인 템플릿이 존재하지 않습니다.");
        }

        return ApiResponse.of(oAuthTemplate.authenticate(code));
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
