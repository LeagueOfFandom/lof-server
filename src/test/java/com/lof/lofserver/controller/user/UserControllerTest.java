package com.lof.lofserver.controller.user;

import com.lof.lofserver.controller.user.parser.UserControllerParser;
import com.lof.lofserver.service.user.UserService;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    UserControllerParser userControllerParser;

    @Mock
    UserService userService;


    private UserSavedInfoDto getUserSavedInfoDtoTest() {
        return UserSavedInfoDto.builder()
                .nickname("test")
                .fcmToken("test")
                .email("test")
                .profileImg("test")
                .build();
    }

}