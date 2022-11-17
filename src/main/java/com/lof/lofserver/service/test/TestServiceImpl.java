package com.lof.lofserver.service.test;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final UserRepository userRepository;

    @Value("${FCM.key}")
    private String secretKey;

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
        notification.put("title", "testMessage");
        notification.put("body", message);

        JSONObject userJson = new JSONObject();
        userJson.put("to", userEntity.getFcmToken());
        userJson.put("data", notification);
        HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);
        System.out.println(request);
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            return new FcmDto((long) response.getStatusCodeValue(), response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return new FcmDto(500L, e.getMessage() + "header : " + headers.toString() + "request : " + request.toString());
        }
    }
}
