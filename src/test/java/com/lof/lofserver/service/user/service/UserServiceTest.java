package com.lof.lofserver.service.user.service;

import com.lof.lofserver.config.LofConfig;
import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.user.service.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.service.response.UserInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    @Test
    @DisplayName("사용자 정보 반환 - 성공")
    public void getUserInfo() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(LofConfig.class);
        UserService userService = ac.getBean(UserService.class);
        UserSavedInfoDto userSavedInfoDto = UserSavedInfoDto.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .leagueIdList(List.of(1L, 2L, 3L))
                .build();

        //when
        UserInfoDto userInfoDto = userService.saveUser(userSavedInfoDto);

        //then
        assertThat(userInfoDto).isNotNull();
        assertThat(userInfoDto.isNewUser()).isTrue();
    }

}