package com.lof.lofserver.controller.user.parser;

import com.lof.lofserver.controller.user.request.UserInfoDto;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import org.springframework.stereotype.Service;

@Service
public class UserControllerParserImpl implements UserControllerParser {

    @Override
    public UserSavedInfoDto parseUserInfoDtoToUserSavedInfoDto(UserInfoDto userInfoDto) {
        return UserSavedInfoDto.builder()
                .fcmToken(userInfoDto.fcmToken())
                .profileImg(userInfoDto.picture())
                .email(userInfoDto.email())
                .nickname(userInfoDto.name())
                .build();
    }
}
