package com.lof.lofserver.service.user.certification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class GoogleCertificationImpl implements Certification {

    @Override
    public Boolean isCertification(String googleAccessToken) {
        return true;
    }
}
