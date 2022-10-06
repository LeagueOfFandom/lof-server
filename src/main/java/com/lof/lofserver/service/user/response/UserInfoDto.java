package com.lof.lofserver.service.user.response;

import lombok.Builder;

public record UserInfoDto(Long id, Boolean isNewUser) {
    @Builder
    public UserInfoDto {
    }

}
