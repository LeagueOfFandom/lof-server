package com.lof.lofserver.service.user.certification;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;



class GoogleCertificationImplTest {


    @Test
    public void isCertification() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(CertificationConfig.class);
        Certification certification = ac.getBean(Certification.class);
        assertTrue(certification.isCertification("googleAccessToken"));
    }
}