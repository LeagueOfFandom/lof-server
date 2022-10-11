package com.lof.lofserver.controller.match;

import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.filter.JsonWebToken;
import com.lof.lofserver.service.community.CommunityService;
import com.lof.lofserver.service.community.response.BannerView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

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

    @Test
    @DisplayName("메인 페이지 가져오기")
    void getMainPage() throws Exception {
        //given
        String token = "test";
        given(communityService.getBannerList()).willReturn(getBannerViewTest());

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/match/mainPage")
                        .contentType("application/json")
                        .header("Authorization", token));

        //then
        resultActions.andExpect(status().isOk());
    }

    private BannerView getBannerViewTest() {
        return new BannerView(List.of("test"));
    }

}