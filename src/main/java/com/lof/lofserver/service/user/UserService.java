package com.lof.lofserver.service.user;

import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.response.UserResponseInfo;

public interface UserService {
     UserResponseInfo createUserByUserSavedInfoDto(UserSavedInfoDto userSavedInfoDto);
     String getNicknameByUserId(Long userId);

     String setUserNickName(Long userId, String nickname);
}
