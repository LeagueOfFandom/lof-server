package com.lof.lofserver.controller.user;

import com.lof.lofserver.controller.user.request.UserInfoDtoByGoogle;
import com.lof.lofserver.service.certification.CertificationDto;
import com.lof.lofserver.service.certification.CertificationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private CertificationService certificationService;

    @InjectMocks
    private UserController userController;

    @Test
    @DisplayName("유저 생성 테스트")
    public void userCreate(){
        //given
        String googleAccessToken = "googleAccessToken";
        String fcmToken = "fcmToken";
        UserInfoDtoByGoogle userInfoDtoByGoogle = new UserInfoDtoByGoogle(googleAccessToken, fcmToken);
        given(certificationService.getCertification(userInfoDtoByGoogle.getGoogleAccessToken())).
                willReturn(CertificationDto.builder()
                        .name("test")
                        .email("test")
                        .profileImg("test").build());

        //when
        ResponseEntity<?> responseEntity = userController.createUser(userInfoDtoByGoogle);

        //then
        responseEntity.getStatusCodeValue();
    }

}