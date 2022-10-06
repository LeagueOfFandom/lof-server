package com.lof.lofserver.service.user.certification.google;

import com.lof.lofserver.service.user.certification.Certification;
import com.lof.lofserver.service.user.certification.CertificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class GoogleCertificationImpl implements Certification {

    @Override
    public CertificationDto getCertification(String googleAccessToken) {
        return null;
    }
}
