package com.lof.lofserver.controller.user.parser;

import com.lof.lofserver.controller.user.request.UserInfoRequest;
import com.lof.lofserver.controller.user.response.UserInfoResponse;
import com.lof.lofserver.service.user.request.UserSavedInfo;

public interface UserControllerParser {
    UserSavedInfo parseUserInfoDtoToUserSavedInfoDto(UserInfoRequest userInfoRequest);
    UserInfoResponse parseUserResponseInfoToUserResponseInfoDto(String jwtToken, Boolean isNewUser);
}
