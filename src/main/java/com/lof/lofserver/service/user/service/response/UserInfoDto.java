package com.lof.lofserver.service.user.service.response;

import lombok.Builder;

public record UserInfoDto(Long id, Boolean isNewUser) {
    @Builder
    public UserInfoDto {
    }

}
