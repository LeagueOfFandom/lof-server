package com.lof.lofserver.service.certification.google;

import com.lof.lofserver.service.certification.CertificationService;
import com.lof.lofserver.service.certification.CertificationDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class GoogleCertificationService implements CertificationService {

    /**
     * Google access token 을 이용하여 google user info 를 가져온다.
     * @param accessToken - google access token
     * @return GoogleUserInfoDto - google user info
     */
    private GoogleUserInfoDto getUserInfo(String accessToken) {
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + accessToken;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<GoogleUserInfoDto> googleUserInfoDto = restTemplate.exchange(url, HttpMethod.GET, requestEntity, GoogleUserInfoDto.class);
            return googleUserInfoDto.getBody();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * google user info 를 이용하여 certification dto 를 생성한다.
     * @param googleUserInfoDto - google user info
     * @return CertificationDto - certification dto
     */
    private CertificationDto getCertificationDto(GoogleUserInfoDto googleUserInfoDto) {
        return CertificationDto.builder()
                .name(googleUserInfoDto.getName())
                .email(googleUserInfoDto.getEmail())
                .profileImg(googleUserInfoDto.getPicture())
                .locale(googleUserInfoDto.getLocale())
                .build();
    }

    @Override
    public CertificationDto getCertification(String googleAccessToken) {
        //test token 일 때 반환.
        if(googleAccessToken.equals("test")) return CertificationDto.builder().name("test").email("test").profileImg("test").locale("test").build();

        //token 으로 googleUserInfoDto 를 받아온다.
        GoogleUserInfoDto googleUserInfoDto = getUserInfo(googleAccessToken);

        //googleUserInfoDto 가 null 이면 null 반환.
        return Objects.isNull(googleUserInfoDto) ? null : getCertificationDto(googleUserInfoDto);
    }
}
