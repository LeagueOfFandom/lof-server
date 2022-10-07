package com.lof.lofserver.controller.user.parser;

import com.lof.lofserver.controller.user.request.UserInfo;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserControllerParserImpl implements UserControllerParser {

    @Override
    public UserSavedInfoDto parseUserInfoDtoToUserSavedInfoDto(UserInfo userInfo) {
        return UserSavedInfoDto.builder()
                .fcmToken(userInfo.fcmToken())
                .profileImg(userInfo.picture())
                .email(userInfo.email())
                .nickname(userInfo.name())
                .build();
    }
}
