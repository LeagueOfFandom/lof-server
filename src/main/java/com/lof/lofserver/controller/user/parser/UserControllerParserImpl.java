package com.lof.lofserver.controller.user.parser;

import com.lof.lofserver.controller.user.request.UserInfoDto;
import com.lof.lofserver.controller.user.response.UserResponseInfoDto;
import com.lof.lofserver.service.user.request.UserSavedInfo;
import org.springframework.stereotype.Service;

@Service
public class UserControllerParserImpl implements UserControllerParser {

    @Override
    public UserSavedInfo parseUserInfoDtoToUserSavedInfoDto(UserInfoDto userInfoDto) {
        return UserSavedInfo.builder()
                .fcmToken(userInfoDto.fcmToken())
                .profileImg(userInfoDto.picture())
                .email(userInfoDto.email())
                .nickname(userInfoDto.name())
                .build();
    }

    @Override
    public UserResponseInfoDto parseUserResponseInfoToUserResponseInfoDto(String jwtToken, Boolean isNewUser) {
        return new UserResponseInfoDto(jwtToken, isNewUser);
    }
}
