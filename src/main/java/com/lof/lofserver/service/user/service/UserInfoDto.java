package com.lof.lofserver.service.user.service;

import lombok.Builder;

public record UserInfoDto(Long id, String name, String picture, Boolean isNewUser) {

    @Builder
    public UserInfoDto {
    }

}
