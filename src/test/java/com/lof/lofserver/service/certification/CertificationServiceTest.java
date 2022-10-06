package com.lof.lofserver.service.certification;

import com.lof.lofserver.config.LofConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


class CertificationServiceTest {

    @Test
    @DisplayName("인증 정보 반환 - 성공")
    void getCertification() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(LofConfig.class);
        CertificationService certificationService = ac.getBean(CertificationService.class);

        //when
        CertificationDto certificationDto = certificationService.getCertification("test");

        //then
        assertThat(certificationDto).usingRecursiveComparison().isEqualTo(testCertificationDto());
    }

    @Test
    @DisplayName("인증 정보 반환 - 실패")
    void getCertificationFail() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(LofConfig.class);
        CertificationService certificationService = ac.getBean(CertificationService.class);

        //when
        CertificationDto certificationDto = certificationService.getCertification("fail");

        //then
        assertThat(certificationDto).isNull();
    }

    private CertificationDto testCertificationDto() {
        return CertificationDto.builder()
                .name("test")
                .email("test")
                .profileImg("test")
                .locale("test").build();
    }
}