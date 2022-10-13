package com.lof.lofserver.controller.user.parser;

import com.lof.lofserver.controller.user.request.UserInfoRequest;
import com.lof.lofserver.controller.user.response.UserInfoResponse;
import com.lof.lofserver.service.user.request.UserSavedInfo;
import org.springframework.stereotype.Service;

@Service
public class UserControllerParserImpl implements UserControllerParser {

    @Override
    public UserSavedInfo parseUserInfoDtoToUserSavedInfoDto(UserInfoRequest userInfoRequest) {
        return UserSavedInfo.builder()
                .fcmToken(userInfoRequest.fcmToken())
                .profileImg(userInfoRequest.picture())
                .email(userInfoRequest.email())
                .nickname(userInfoRequest.name())
                .build();
    }

    @Override
    public UserInfoResponse parseUserResponseInfoToUserResponseInfoDto(String jwtToken, Boolean isNewUser) {
        return new UserInfoResponse(jwtToken, isNewUser);
    }
}
