package com.lof.lofserver.service.user.request;

import lombok.Builder;

import java.util.List;

public record UserSavedInfoDto (
        String fcmToken,
        String email,
        String nickname,
        String profileImg,
        List<Long> leagueIdList
){
    @Builder
    public UserSavedInfoDto{
    }
}
