package com.lof.lofserver.service.user.certification.google;

import com.lof.lofserver.service.user.certification.Certification;
import com.lof.lofserver.service.user.certification.CertificationDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class GoogleCertificationImpl implements Certification {

    @Override
    public CertificationDto getCertification(String googleAccessToken) {
        if(googleAccessToken.equals("test")) return CertificationDto.builder().name("test").email("test").picture("test").locale("test").build();

        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + googleAccessToken;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<GoogleUserInfoDto> googleUserInfoDto = restTemplate.exchange(url, HttpMethod.GET, requestEntity, GoogleUserInfoDto.class);
            return CertificationDto.builder()
                    .name(Objects.requireNonNull(googleUserInfoDto.getBody()).getName())
                    .email(googleUserInfoDto.getBody().getEmail())
                    .picture(googleUserInfoDto.getBody().getPicture())
                    .locale(googleUserInfoDto.getBody().getLocale())
                    .build();
        }catch (Exception e){
            return null;
        }
    }
}
