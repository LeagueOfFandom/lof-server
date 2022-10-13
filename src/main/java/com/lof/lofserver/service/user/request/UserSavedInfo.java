package com.lof.lofserver.service.user.request;

import lombok.Builder;

public record UserSavedInfo(
        String fcmToken,
        String email,
        String nickname,
        String profileImg
){
    @Builder
    public UserSavedInfo {
    }
}
