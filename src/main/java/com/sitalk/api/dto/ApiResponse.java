package com.sitalk.api.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class ApiResponse<T> {
    private String res_code = "200";
    private String res_msg = "정상처리 되었습니다.";
    private T res_data;

    public ApiResponse() {
        this.res_data = (T) new HashMap<String, Object>();
    }

    public ApiResponse(T data) {
        this.res_data = data;
    }

    public ApiResponse(String res_code, String res_msg) {
        this.res_code = res_code;
        this.res_msg = res_msg;
    }

    public static <T> ApiResponse<T> of(T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> empty() {
        return new ApiResponse<>(null);
    }
}
