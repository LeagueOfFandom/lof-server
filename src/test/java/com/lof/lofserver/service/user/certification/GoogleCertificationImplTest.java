package com.lof.lofserver.service.user.certification;

import com.lof.lofserver.config.LofConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
class GoogleCertificationImplTest {


    @Test
    @DisplayName("사용자 외부 인증 - 실패")
    public void certificationFail() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(LofConfig.class);
        Certification certification = ac.getBean(Certification.class);

        //when
        CertificationDto certificationDto = certification.getCertification("googleAccessToken");

        //then
        assertThat(certificationDto).isEqualTo(null);
    }

    @Test
    @DisplayName("사용자 외부 인증 성공(test)")
    public void certificationSuccess() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(LofConfig.class);
        Certification certification = ac.getBean(Certification.class);

        //when
        CertificationDto certificationDto = certification.getCertification("test");

        //then
        CertificationDto testCertificationDto = CertificationDto.builder().name("test").email("test").picture("test").locale("test").build();
        assertThat(certificationDto).usingRecursiveComparison().isEqualTo(testCertificationDto);
    }

}