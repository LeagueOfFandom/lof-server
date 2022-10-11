package com.lof.lofserver.controller.user;

import com.lof.lofserver.controller.user.parser.UserControllerParser;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.filter.JsonWebToken;
import com.lof.lofserver.service.user.UserService;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.response.UserResponseInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserControllerParser userControllerParser;

    @MockBean
    UserService userService;

    @MockBean
    JsonWebToken jsonWebToken;

    @MockBean
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;


    @Test
    @DisplayName("유저 닉네임 설정 - 성공")
    void setUserNiceName() throws Exception {
        //given
        String token = "test";
        //given(jsonWebToken.checkJwtToken(any(String.class))).willReturn("valid");
        //given(userControllerParser.parseUserSavedInfoDto(any(String.class))).willReturn(new UserSavedInfoDto("test", "test"));
        //given(userService.setUserNiceName(any(UserSavedInfoDto.class))).willReturn(new UserResponseInfoDto("test", "test"));

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/user/setNickname")
                        .contentType("application/json")
                        .header("Authorization", token)
                        );

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 - 성공")
    void createUserSuccess() throws Exception {
        //given
        String userInfoDto = "{\"email\": \"email\", \"name\": \"name\", \"picture\": \"picture\", \"fcmToken\": \"fcmToken\"}";
        given(userService.createUserByUserSavedInfoDto(any())).willReturn(createUserResponseInfoDtoTest());
        given(userControllerParser.parseUserInfoDtoToUserSavedInfoDto(any())).willReturn(createUserSavedInfoDtoTest());
        given(jsonWebToken.createJsonWebTokenById(any())).willReturn("token");

        //when
        ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/v1/user/create")
                            .contentType("application/json")
                            .content(userInfoDto));

        //then
        resultActions.andExpect(status().isOk());
    }
    @Test
    @DisplayName("회원가입 - 실패")
    void createUserFail() throws Exception {
        //given
        String userInfoDto = "{ \"name\": \"name\", \"picture\": \"picture\", \"fcmToken\": \"fcmToken\"}";
        given(userService.createUserByUserSavedInfoDto(any())).willReturn(createUserResponseInfoDtoTest());
        given(userControllerParser.parseUserInfoDtoToUserSavedInfoDto(any())).willReturn(createUserSavedInfoDtoTest());
        given(jsonWebToken.createJsonWebTokenById(any())).willReturn("token");

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/user/create")
                        .contentType("application/json")
                        .content(userInfoDto));

        //then
        resultActions.andExpect(status().isBadRequest());
    }

    private UserResponseInfoDto createUserResponseInfoDtoTest() {
        return UserResponseInfoDto.builder()
                .id(1L)
                .isNewUser(true)
                .build();
    }

    private UserSavedInfoDto createUserSavedInfoDtoTest() {
        return UserSavedInfoDto.builder()
                .nickname("Test")
                .email("Test")
                .profileImg("Test")
                .fcmToken("Test")
                .build();
    }

}