package com.lof.lofserver.controller.user.parser;

import com.lof.lofserver.controller.user.request.UserInfo;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;

public interface UserControllerParser {
    UserSavedInfoDto parseUserInfoDtoToUserSavedInfoDto(UserInfo userInfo);
}
