package com.lof.lofserver.controller.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoDto {
    private String googleAccessToken;
    private String fcmToken;

    public UserInfoDto(String googleAccessToken, String fcmToken) {
        this.googleAccessToken = googleAccessToken;
        this.fcmToken = fcmToken;
    }
}
