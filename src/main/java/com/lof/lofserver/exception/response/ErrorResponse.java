package com.lof.lofserver.exception.response;

import lombok.Builder;

public record ErrorResponse(
        String code,
        String message
){
    @Builder
    public ErrorResponse {
    }
}
