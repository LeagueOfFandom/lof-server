package com.lof.lofserver.service.certification;

import lombok.Builder;

public record CertificationDto(String name, String email, String profileImg, String locale) {
    @Builder
    public CertificationDto {
    }
}
