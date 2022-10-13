package com.lof.lofserver.controller.match.response;

import lombok.Builder;

public record CommonItemListResponse(String viewType, Object viewObject) {
    @Builder
    public CommonItemListResponse {
    }
}

