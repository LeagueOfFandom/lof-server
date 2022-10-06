package com.lof.lofserver.service.user.certification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;



class GoogleCertificationImplTest {


    @Test
    @DisplayName("구글 인증 test - 실패")
    public void isCertification() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(CertificationConfig.class);
        Certification certification = ac.getBean(Certification.class);

        //when
        CertificationDto certificationDto = certification.getCertification("googleAccessToken");

        //then
        assertThat(certificationDto).isEqualTo(null);
    }
}