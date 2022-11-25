package com.lof.lofserver.service.user;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.domain.user.sub.AlarmList;
import com.lof.lofserver.service.user.request.UserSavedInfo;
import com.lof.lofserver.service.user.response.EventResponse;
import com.lof.lofserver.service.user.response.TextResponse;
import com.lof.lofserver.service.user.response.UserResponseInfo;
import com.lof.lofserver.service.user.validate.UserValidateImpl;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidateImpl userValidateImpl;

    @Override
    public Boolean isNewUser(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserResponseInfo createTempUser(String fcmToken){
        Long userId = userRepository.save(UserEntity.builder()
                .fcmToken(fcmToken)
                .email("temp")
                .nickname("temp")
                .profileImg("")
                .build()).getUserId();

        return UserResponseInfo.builder()
                .id(userId)
                .isNewUser(true)
                .build();
    }

    @Override
    public String setFcmTokenByUserId(Long userId, String fcmToken){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        userEntity.setFcmToken(fcmToken);
        userRepository.save(userEntity);
        return fcmToken;
    }

    @Override
    public Boolean getAlarmByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        return userEntity.getAlarm();
    }

    @Override
    public Boolean setAlarmByUserId(Long userId, Boolean alarm) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        userEntity.setAlarm(alarm);
        userRepository.save(userEntity);
        return userEntity.getAlarm();
    }

    @Override
    public List<AlarmList> getAlarmListByUserId(Long userId) {

        List<AlarmList> alarmList = new ArrayList<>();
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        if(userEntity.getAlarmList() != null && userEntity.getAlarmList().size() > 0) {
            alarmList.addAll(userEntity.getAlarmList());
        }
        alarmList.add(AlarmList.builder()
                .viewType("ONE_LINE_TEXT_VIEW")
                .viewObject(new TextResponse("오늘"))
                .build());
        alarmList.add(AlarmList.builder()
                .viewType("INFO_EVENT_VIEW")
                .viewObject(EventResponse.builder()
                        .infoTitle("T1 vs DX")
                        .infoContent("T1과 DX의 라이브 경기가 진행 중입니다. 확인하러 가볼까요?")
                        .infoIsCheck(true)
                        .infoTimeCompare("1시간 전")
                        .infoDateTime("22.09.01. 17:30")
                        .build())
                .build());
        alarmList.add(AlarmList.builder()
                .viewType("ONE_LINE_TEXT_VIEW")
                .viewObject(new TextResponse("어제"))
                .build());
        alarmList.add(AlarmList.builder()
                .viewType("INFO_LEAGUE_VIEW")
                .viewObject(EventResponse.builder()
                        .infoTitle("DRX 2 : 1 KDF")
                        .infoContent("DRX가 KDF의 경기에서 2:1로 승리하였습니다!")
                        .infoIsCheck(true)
                        .infoTimeCompare("하루 전")
                        .infoDateTime("22.08.30. 14:30")
                        .build())
                .build());
        alarmList.add(AlarmList.builder()
                .viewType("INFO_HIGHLIGHT_VIEW")
                .viewObject(EventResponse.builder()
                        .infoTitle("DRX vs T1 하이라이트 영상 업로드!")
                        .infoContent("9월 30일 18:00 경기의 하이라이트 영상이 업로드 되었습니다. 지금 확인해보세요!")
                        .infoIsCheck(true)
                        .infoTimeCompare("1시간 전")
                        .infoDateTime("22.09.01. 17:30")
                        .build())
                .build());
        alarmList.add(AlarmList.builder()
                .viewType("ONE_LINE_TEXT_VIEW")
                .viewObject(new TextResponse("이번주"))
                .build());
        alarmList.add(AlarmList.builder()
                .viewType("INFO_COMMENT_VIEW")
                .viewObject(EventResponse.builder()
                        .infoTitle("'누구누구'님이 나의 글에 댓글을 남겼습니다")
                        .infoContent("와 너무 좋아요")
                        .infoIsCheck(false)
                        .infoTimeCompare("1시간 전")
                        .infoDateTime("22.09.01. 17:30")
                        .build())
                .build());
        alarmList.add(AlarmList.builder()
                .viewType("INFO_POST_LIKE_VIEW")
                .viewObject(EventResponse.builder()
                        .infoTitle("'누구누구'님이 나의 게시글에 하트를 눌렀습니다.")
                        .infoContent("나의 게시글 - 와 너무 잘해")
                        .infoIsCheck(false)
                        .infoTimeCompare("일주일 전")
                        .infoDateTime("22.09.01. 17:30")
                        .build())
                .build());
        alarmList.add(AlarmList.builder()
                .viewType("INFO_POST_SUCCESS_VIEW")
                .viewObject(EventResponse.builder()
                        .infoTitle("게시글을 성공적으로 업로드했습니다.")
                        .infoContent("나의 게시글 제목")
                        .infoIsCheck(false)
                        .infoTimeCompare("1시간 전")
                        .infoDateTime("22.09.01. 17:30")
                        .build())
                .build());

        return alarmList;
    }

    @Override
    public String setUserNickName(Long userId, String nickname) {
        userValidateImpl.validateNickname(nickname);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        userEntity.setNickname(nickname);
        return userRepository.save(userEntity).getNickname();
    }

    @Override
    public String getNicknameByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        return userEntity.getNickname();
    }

    /** 유저 생성
     * @param userSavedInfo - 유저 정보
     * @return UserResponseInfoDto
     */
    @Override
    public UserResponseInfo createUserByUserSavedInfoDto(UserSavedInfo userSavedInfo) {
        //find user
        UserEntity userEntity = userRepository.findByEmail(userSavedInfo.email());

        //if user is existed, return userInfoDto
        if(userEntity != null)
            return parseUserResponseInfoDto(userEntity, false);

        UserSavedInfo savedInfo = UserSavedInfo.builder()
                .email(userSavedInfo.email())
                .fcmToken(userSavedInfo.fcmToken() == null ? "" : userSavedInfo.fcmToken())
                .nickname(userSavedInfo.nickname() == null ? "" : userSavedInfo.nickname())
                .profileImg(userSavedInfo.profileImg() == null ? "" : userSavedInfo.profileImg()).build();

        //create user
        userEntity = createUserEntity(savedInfo);

        //save user
        UserEntity savedUserEntity = userRepository.save(userEntity);

        //return user info
        return parseUserResponseInfoDto(savedUserEntity, true);
    }

    /**
     * create UserResponseInfoDto by UserEntity
     * @param userEntity - user entity
     * @param isNewUser - boolean value
     * @return UserResponseInfoDto
     */
    private UserResponseInfo parseUserResponseInfoDto(UserEntity userEntity, Boolean isNewUser) {
        return UserResponseInfo.builder()
                .id(userEntity.getUserId())
                .isNewUser(isNewUser)
                .build();
    }

    /**
     * create UserEntity by UserSavedInfoDto
     * @param userSavedInfo - user entity 에 저장할 정보
     * @return UserEntity
     */
    private UserEntity createUserEntity(UserSavedInfo userSavedInfo) {
        return UserEntity.builder()
                .fcmToken(userSavedInfo.fcmToken())
                .email(userSavedInfo.email())
                .nickname(userSavedInfo.nickname())
                .profileImg(userSavedInfo.profileImg())
                .build();
    }
}
