package com.lof.lofserver.controller.league;

import com.lof.lofserver.controller.league.parser.LeagueControllerParser;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.filter.JsonWebToken;
import com.lof.lofserver.service.league.LeagueService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LeagueController.class)
class LeagueControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JsonWebToken jsonWebToken;

    @MockBean
    UserRepository userRepository;

    @MockBean
    LeagueControllerParser leagueControllerParser;

    @MockBean
    LeagueService leagueService;
    @Test
    @DisplayName("팀 전부 가져오기(test token) - 성공")
    void getAllTeamListByUserId() throws Exception {
        //given
        String token = "test";
        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/league/allByUser")
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
                MockMvcRequestBuilders.get("/v1/league/allByUser")
                        .contentType("application/json")
                        .header("Authorization", token));

        //then
        resultActions.andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("유저의 선택한 팀 가져오기 - 성공")
    void getSelectedTeamByUserId() throws Exception {
        //given
        String token = "test";
        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/league/selectedTeamByUser")
                        .contentType("application/json")
                        .header("Authorization", token));

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("유저의 팀 설정하기 - 성공")
    void setSelectedTeamByUserId() throws Exception {
        //given
        String token = "test";
        List<Long> teamIdList = List.of(1L, 2L);
        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/league/selectedTeamByUser")
                        .contentType("application/json")
                        .header("Authorization", token)
                        .content(teamIdList.toString()));

        //then
        resultActions.andExpect(status().isOk());
    }
}