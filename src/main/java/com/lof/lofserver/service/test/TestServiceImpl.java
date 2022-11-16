package com.lof.lofserver.service.test;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final UserRepository userRepository;

    @Value("${FCM.key}")
    private final String secretKey;

    @Override
    public List<UserEntity> getUserNickname() {
        return userRepository.findAll();
    }

    @Override
    public FcmDto sendFcm(String message, Long id) {
        String url = "https://fcm.googleapis.com/fcm/send";
        UserEntity userEntity = userRepository.findById(id).orElse(null);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "key=" + secretKey);

        JSONObject notification = new JSONObject();
        notification.put("title", message);

        JSONObject userJson = new JSONObject();
        userJson.put("to", userEntity.getFcmToken());
        userJson.put("notification", notification);
        HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);
        ResponseEntity<FcmDto> response = new RestTemplate().exchange(url, HttpMethod.POST, request, FcmDto.class);
        response.getBody().setFcmToken(userEntity.getFcmToken());
        return response.getBody();
    }
}
