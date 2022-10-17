package com.lof.lofserver.controller.league.parser;

import com.lof.lofserver.controller.league.response.BaseLeagueAndTeamListResponse;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;
import com.lof.lofserver.service.league.response.sub.LeagueInfo;
import com.lof.lofserver.service.league.response.sub.TeamInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class LeagueControllerParserImplTest {

    @InjectMocks
    LeagueControllerParserImpl leagueControllerParser;

    @Test
    @DisplayName("리그 정보 파싱 테스트")
    void parseLeagueInfo(){
        //given
        BaseLeagueAndTeamList baseLeagueAndTeamList = createBaseLeagueAndTeamListTest();

        //when
        Object leagueInfo = leagueControllerParser.parseLeagueInfoToBaseLeagueAndTeamList(baseLeagueAndTeamList);

        //then
        assertThat(leagueInfo).isInstanceOf(BaseLeagueAndTeamListResponse.class);
    }

    BaseLeagueAndTeamList createBaseLeagueAndTeamListTest(){
        List<String> leagueNameList = new ArrayList<>();
        leagueNameList.add("리그1");

        List<TeamInfo> teamInfoList = new ArrayList<>();
        teamInfoList.add(TeamInfo.builder()
                .teamImg("test")
                .league("test")
                .teamCheck(false)
                .teamId(1L)
                .teamName("test")
                .build());

        List<LeagueInfo> leagueInfoList = new ArrayList<>();
        leagueInfoList.add(LeagueInfo.builder()
                .note("test")
                .teamInfo(teamInfoList)
                .build());

        return BaseLeagueAndTeamList.builder()
                .leagueNameList(leagueNameList)
                .leagueInfoList(leagueInfoList)
                .build();
    }

}