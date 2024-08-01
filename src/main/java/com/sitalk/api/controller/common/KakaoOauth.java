package com.sitalk.api.controller.common;


import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("kakao")
public class KakaoOauth extends OAuthTemplate{

    @Override
    protected String getResponse(String code) {

        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "ec0f895aa2ee4fe6aee7029370925e0c");
        params.add("redirect_uri", "http://localhost:3001/users/kakao/login");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // Parse the response body to extract the access token
            // Example: {"access_token":"...", "token_type":"bearer", "expires_in":43199, "refresh_token":"...", ...}
            JSONObject jsonResponse = new JSONObject(response.getBody());
            return jsonResponse.getString("access_token");
        } else {
            throw new RuntimeException("Failed to get token from Kakao: " + response.getBody());
        }

    }

    @Override
    protected Map<String, Object> getUserInfo(String token) {
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonResponse = new JSONObject(response.getBody());
            Map<String, Object> userMap = new HashMap<>();
            log.info("check userMap = {}", jsonResponse);
            userMap.put("id", jsonResponse.getLong("id"));
            userMap.put("nickname",jsonResponse.getJSONObject("properties").getString("nickname"));
            userMap.put("email", jsonResponse.getJSONObject("kakao_account").optString("email", null));
            return userMap;
        } else {
            throw new RuntimeException("Failed to get user info from Kakao: " + response.getBody());
        }
    }
}
