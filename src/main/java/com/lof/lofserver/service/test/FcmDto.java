package com.lof.lofserver.service.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FcmDto {
    private Long success;
    private String fcmToken;

    public FcmDto(Long success, String fcmToken) {
        this.success = success;
        this.fcmToken = fcmToken;
    }
}
