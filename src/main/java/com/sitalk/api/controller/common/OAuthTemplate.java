package com.sitalk.api.controller.common;

import lombok.extern.slf4j.Slf4j;
import java.util.Map;

@Slf4j
public abstract class OAuthTemplate {
    public final Map<String, Object> authenticate(String code ) {
        log.info("Received code: {}", code);

        String token = getResponse(code);
        return getUserInfo(token);
    }

    protected abstract String getResponse(String code);
    protected abstract Map<String, Object> getUserInfo(String token);
}
