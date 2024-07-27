package com.sitalk.api.enums;

import lombok.Getter;

@Getter
public enum OauthEnum {

    KAKAO ("kakao", "카카오"),
    GOOGLE ("google", "구글"),
    PLATFORM ("platform", "플렛폼")
    ;

    private final String key;
    private final String name;

    OauthEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

}
