package com.lof.lofserver.controller.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoDtoByGoogle {
    private String googleAccessToken;
    private String fcmToken;

    public UserInfoDtoByGoogle(String googleAccessToken, String fcmToken) {
        this.googleAccessToken = googleAccessToken;
        this.fcmToken = fcmToken;
    }
}
