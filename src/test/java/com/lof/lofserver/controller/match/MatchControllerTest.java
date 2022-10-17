package com.lof.lofserver.controller.match;

import com.lof.lofserver.controller.match.parser.MatchControllerParser;
import com.lof.lofserver.controller.match.response.MainPageResponse;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.filter.JsonWebToken;
import com.lof.lofserver.service.community.CommunityService;
import com.lof.lofserver.service.community.response.BannerView;
import com.lof.lofserver.service.match.MatchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MatchController.class)
class MatchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JsonWebToken jsonWebToken;

    @MockBean
    UserRepository userRepository;

    @MockBean
    CommunityService communityService;

    @MockBean
    MatchService matchService;

    @MockBean
    MatchControllerParser matchControllerParser;

    @Test
    @DisplayName("달별 매치 정보 가져오기")
    void getMatchByDate() throws Exception {
        //given
        String token = "test";

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/match/matchListByMonth")
                        .contentType("application/json")
                        .header("Authorization", token)
                        .param("date", "2022-01-01")
                        .param("onlyMyTeam", "false"));

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("메인 페이지 가져오기")
    void getMainPage() throws Exception {
        //given
        String token = "test";
        given(communityService.getBannerList()).willReturn(getBannerViewTest());
        given(matchService.getLiveMatchList(any(Long.class))).willReturn(List.of());

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/match/mainPage")
                        .contentType("application/json")
                        .header("Authorization", token)
                        .param("onlyMyTeam", "false"));

        //then
        resultActions.andExpect(status().isOk());
    }

//    @Test
//    @DisplayName("매치 상세 정보 가져오기")
//    void getMatchDetail() throws Exception {
//        //given
//        String token = "test";
//
//        //when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.get("/v1/match/getMatchDetail")
//                        .contentType("application/json")
//                        .header("Authorization", token)
//                        .param("matchId", "1"));
//
//        //then
//        resultActions.andExpect(status().isOk());
//    }

    private BannerView getBannerViewTest() {
        return new BannerView(List.of("test"));
    }

}