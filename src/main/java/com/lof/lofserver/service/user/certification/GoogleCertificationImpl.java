package com.lof.lofserver.service.user.certification;

public class GoogleCertificationImpl implements Certification {

    @Override
    public Boolean isCertification(String googleAccessToken) {
        return true;
    }
}
