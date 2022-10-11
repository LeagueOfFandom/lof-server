package com.lof.lofserver.service.user;

import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.response.UserResponseInfoDto;

public interface UserService {
     UserResponseInfoDto createUserByUserSavedInfoDto(UserSavedInfoDto userSavedInfoDto);
     String getNicknameByUserId(Long userId);
}
