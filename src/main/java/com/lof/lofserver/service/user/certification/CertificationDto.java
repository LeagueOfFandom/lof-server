package com.lof.lofserver.service.user.certification;

import lombok.Builder;

public record CertificationDto(String name, String email, String picture, String locale) {
    @Builder
    public CertificationDto {
    }
}
