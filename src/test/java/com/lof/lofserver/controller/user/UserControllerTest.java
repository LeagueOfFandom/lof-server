package com.lof.lofserver.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lof.lofserver.controller.user.request.UserInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("유저 생성 테스트")
    public void userCreate() throws Exception{
        //given
        String googleAccessToken = "googleAccessToken";
        String fcmToken = "fcmToken";
        UserInfoDto userInfoDto = new UserInfoDto(googleAccessToken, fcmToken);

        //when
        ResultActions resultActions = mvc.perform(
                post("/v1/user/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userInfoDto)));

        //then
        resultActions.andExpect(status().isOk());
    }

}