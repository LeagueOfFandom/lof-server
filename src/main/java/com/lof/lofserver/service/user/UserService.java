package com.lof.lofserver.service.user;

import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.response.UserInfoDto;

public interface UserService {
    UserInfoDto createUserByUserSavedInfoDto(UserSavedInfoDto userSavedInfoDto);
}
