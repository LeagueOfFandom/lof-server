package com.lof.lofserver.controller.user;

import com.lof.lofserver.controller.user.request.UserInfoDtoByGoogle;
import com.lof.lofserver.service.certification.CertificationDto;
import com.lof.lofserver.service.certification.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final CertificationService certificationService;

    @PostMapping("/google/userCreate")
    public ResponseEntity<?> createUser(@RequestBody UserInfoDtoByGoogle userInfoDtoByGoogle){
        //google access token 으로 google user 정보를 받아온다.
        CertificationDto certificationDto = certificationService.getCertification(userInfoDtoByGoogle.getGoogleAccessToken());
        //user 정보가 없다면 token 이 유효하지 않은 것이다.
        if(certificationDto == null)
            return new ResponseEntity<>("googleAccessToken is invalid", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(certificationDto, HttpStatus.OK);
    }
}
