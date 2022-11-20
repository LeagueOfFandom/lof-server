package com.lof.lofserver.service.user;

import com.lof.lofserver.domain.user.sub.AlarmList;
import com.lof.lofserver.service.user.request.UserSavedInfo;
import com.lof.lofserver.service.user.response.UserResponseInfo;

import java.util.List;

public interface UserService {

     UserResponseInfo createUserByUserSavedInfoDto(UserSavedInfo userSavedInfo);
     UserResponseInfo createTempUser(String fcmToken);
     String getNicknameByUserId(Long userId);

     String setUserNickName(Long userId, String nickname);

     Boolean getAlarmByUserId(Long userId);
     Boolean setAlarmByUserId(Long userId, Boolean alarm);
     List<AlarmList> getAlarmListByUserId(Long userId);

    String setFcmTokenByUserId(Long userId, String fcmToken);

     Boolean isNewUser(String email);
}
