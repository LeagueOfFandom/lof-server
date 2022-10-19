package com.lof.lofserver.service.user;

import com.lof.lofserver.service.user.request.UserSavedInfo;
import com.lof.lofserver.service.user.response.UserResponseInfo;

public interface UserService {
     UserResponseInfo createUserByUserSavedInfoDto(UserSavedInfo userSavedInfo);
     String getNicknameByUserId(Long userId);

     String setUserNickName(Long userId, String nickname);

     Boolean getAlarmByUserId(Long userId);
     Boolean setAlarmByUserId(Long userId, Boolean alarm);
}
