package com.lof.lofserver.controller.team;

import com.lof.lofserver.filter.JsonWebToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
class TeamControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JsonWebToken jsonWebToken;
    @Test
    @DisplayName("팀 전부 가져오기(test token) - 성공")
    void getAllTeamListByUserId() throws Exception {
        //given
        String token = "test";
        //given(jsonWebToken.checkJwtToken(any(String.class))).willReturn("valid");


        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/team/allByUser")
                        .contentType("application/json")
                        .header("Authorization", token));

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("팀 전부 가져오기(test token) - 실패")
    void getAllTeamListByUserIdFail() throws Exception {
        //given
        String token = "wrong";
        given(jsonWebToken.checkJwtToken(any(String.class))).willReturn("invalid");

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/team/allByUser")
                        .contentType("application/json")
                        .header("Authorization", token));

        //then
        resultActions.andExpect(status().isUnauthorized());
    }
}