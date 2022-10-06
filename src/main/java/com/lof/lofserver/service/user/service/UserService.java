package com.lof.lofserver.service.user.service;

import com.lof.lofserver.service.user.service.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.service.response.UserInfoDto;

public interface UserService {
    UserInfoDto saveUser(UserSavedInfoDto userSavedInfoDto);
}
