package com.lof.lofserver.service.certification.google;

import com.lof.lofserver.service.certification.CertificationDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GoogleCertificationServiceTest {

    @InjectMocks
    private GoogleCertificationService googleCertificationService;

    @Test
    @DisplayName("구글 인증 토큰으로 인증 정보 반환 - 성공")
    void getCertification() {
        //given
        String googleAccessToken = "test";

        //when
        CertificationDto certificationDto = googleCertificationService.getCertification(googleAccessToken);

        //then
        assertEquals(certificationDto, testCertificationDto());
    }

    @Test
    @DisplayName("구글 인증 토큰으로 인증 정보 반환 - 실패")
    void getCertificationFail() {
        //given
        String googleAccessToken = "fail";

        //when
        CertificationDto certificationDto = googleCertificationService.getCertification(googleAccessToken);

        //then
        assertNull(certificationDto);
    }

    private CertificationDto testCertificationDto() {
        return CertificationDto.builder()
                .name("test")
                .email("test")
                .profileImg("test")
                .locale("test").build();
    }
}