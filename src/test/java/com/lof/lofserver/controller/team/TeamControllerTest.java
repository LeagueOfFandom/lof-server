package com.lof.lofserver.controller.team;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
class TeamControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("팀 전부 가져오기(test token) - 성공")
    void getAllTeamListByUserId() throws Exception {
        //given
       String token = "test";

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/team/allByUser")
                        .contentType("application/json")
                        .header("Authorization", token));

        //then
        resultActions.andExpect(status().isOk());
    }
}