package com.lof.lofserver.service.user.response;

import lombok.Builder;

public record UserResponseInfo(Long id, Boolean isNewUser) {
    @Builder
    public UserResponseInfo {
    }

}
