package com.lof.lofserver.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public record ErrorResponseDto(
        String code,
        String message
){
    @Builder
    public ErrorResponseDto{
    }
}
