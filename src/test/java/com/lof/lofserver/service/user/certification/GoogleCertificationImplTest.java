package com.lof.lofserver.service.user.certification;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoogleCertificationImplTest {

    @Test
    public void isCertification() {
        Certification certification = new GoogleCertificationImpl();
        assertTrue(certification.isCertification("googleAccessToken"));
    }
}