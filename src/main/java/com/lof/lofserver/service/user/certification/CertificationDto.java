package com.lof.lofserver.service.user.certification;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CertificationDto {
    private String name;
    private String email;
    private String picture;
    private String locale;

    @Builder
    public CertificationDto(String name, String email, String picture, String locale) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.locale = locale;
    }
}
