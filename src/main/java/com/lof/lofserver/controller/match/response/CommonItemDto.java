package com.lof.lofserver.controller.match.response;

import lombok.Builder;

public record CommonItemDto(String viewType, Object viewObject) {
    @Builder
    public CommonItemDto {
    }
}

