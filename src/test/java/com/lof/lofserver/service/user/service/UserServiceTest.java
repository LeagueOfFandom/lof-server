package com.lof.lofserver.service.user.service;

import com.lof.lofserver.config.LofConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


class UserServiceTest {

    @Test
    @DisplayName("사용자 저장")
    void saveUser() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(LofConfig.class);
        UserService userService = ac.getBean(UserService.class);

        String name = "test";
        String email = "test";
        String picture = "test";
        String locale = "test";

        //when
        UserInfoDto userInfoDto = userService.saveUser(name, email, picture, locale);

        //then
        assertThat(userInfoDto).isEqualTo(null);
    }
}