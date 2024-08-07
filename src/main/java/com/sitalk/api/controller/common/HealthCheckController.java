package com.sitalk.api.controller.common;

import com.sitalk.api.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public ApiResponse<?> healthCheck() {
        return ApiResponse.of("Success health check");
    }

}
