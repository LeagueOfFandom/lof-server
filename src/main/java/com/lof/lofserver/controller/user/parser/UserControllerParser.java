package com.lof.lofserver.controller.user.parser;

import com.lof.lofserver.controller.user.request.UserInfoDto;
import com.lof.lofserver.controller.user.response.UserResponseInfoDto;
import com.lof.lofserver.service.user.request.UserSavedInfo;

public interface UserControllerParser {
    UserSavedInfo parseUserInfoDtoToUserSavedInfoDto(UserInfoDto userInfoDto);
    UserResponseInfoDto parseUserResponseInfoToUserResponseInfoDto(String jwtToken, Boolean isNewUser);
}
