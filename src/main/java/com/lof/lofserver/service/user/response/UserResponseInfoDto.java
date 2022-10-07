package com.lof.lofserver.service.user.response;

import lombok.Builder;

public record UserResponseInfoDto(Long id, Boolean isNewUser) {
    @Builder
    public UserResponseInfoDto {
    }

}
