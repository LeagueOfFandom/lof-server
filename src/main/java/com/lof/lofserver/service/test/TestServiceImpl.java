package com.lof.lofserver.service.test;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.domain.user.sub.AlarmList;
import com.lof.lofserver.service.user.response.EventResponse;
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

import java.util.ArrayList;
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
    public List<FcmDto> sendFcm(String title, String message, Long id) {
        List<FcmDto> fcmDtoList = new ArrayList<>();
        if(id == 0){
            List<UserEntity> userEntityList = userRepository.findAll();
            for(UserEntity userEntity : userEntityList){
                fcmDtoList.add(sendFcmToUser(title, message, userEntity));
            }
        }else{
            UserEntity userEntity = userRepository.findById(id).orElseThrow();
            fcmDtoList.add(sendFcmToUser(title, message, userEntity));
        }
        return  fcmDtoList;
    }

    private FcmDto sendFcmToUser(String title, String message, UserEntity userEntity){

        String url = "https://fcm.googleapis.com/fcm/send";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "key=" + secretKey);

        JSONObject notification = new JSONObject();
        notification.put("title", title);
        notification.put("body", message);

        JSONObject data = new JSONObject();
        data.put("homeScore", 1);
        data.put("awayScore", 2);

        JSONObject userJson = new JSONObject();
        userJson.put("to", userEntity.getFcmToken());
        userJson.put("notification", notification);
        userJson.put("data", data);
        userJson.put("priority", "high");


        HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);
        System.out.println(request);
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            userEntity.addAlarmList(AlarmList.builder()
                            .viewType("INFO_EVENT_VIEW")
                            .viewObject(EventResponse.builder()
                                    .infoTitle("BFO vs IST 의 4세트 경기가 종료되었습니다.")
                                    .infoContent("BFO 2 : 2 IST 로 BFO 가 4세트를 이겼습니다.")
                                    .infoIsCheck(false)
                                    .infoTimeCompare("방금 전")
                                    .infoDateTime("22.09.01. 17:30")
                                    .build())
                    .build());
            userRepository.save(userEntity);
            return new FcmDto((long) response.getStatusCodeValue(), response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return new FcmDto(500L, e.getMessage() + "header : " + headers.toString() + "request : " + request.toString());
        }
    }
}
