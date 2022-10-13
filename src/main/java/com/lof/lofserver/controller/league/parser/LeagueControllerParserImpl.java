package com.lof.lofserver.controller.league.parser;

import com.lof.lofserver.controller.league.response.BaseLeagueAndTeamListResponse;
import com.lof.lofserver.controller.league.response.sub.LeagueInfoListResponse;
import com.lof.lofserver.controller.league.response.sub.TeamInfoListResponse;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;
import com.lof.lofserver.service.league.response.sub.LeagueInfo;
import com.lof.lofserver.service.league.response.sub.TeamInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueControllerParserImpl implements LeagueControllerParser {
    @Override
    public BaseLeagueAndTeamListResponse parseLeagueInfoToBaseLeagueAndTeamList(BaseLeagueAndTeamList baseLeagueAndTeamList) {
        //leagueInfoList -> leagueInfoDtoList
        List<LeagueInfoListResponse> leagueInfoListListResponse = baseLeagueAndTeamList.leagueInfoList().stream().
                map(this::parseLeagueInfoDtoToLeagueInfo)
                .toList();

        return BaseLeagueAndTeamListResponse.builder()
                .leagueInfoListListResponse(leagueInfoListListResponse)
                .leagueNameList(baseLeagueAndTeamList.leagueNameList())
                .build();
    }

    @Override
    public List<TeamInfoListResponse> parseTeamInfoToTeamInfoDto(List<TeamInfo> teamInfoList) {
        return teamInfoList.stream().map(teamInfo -> TeamInfoListResponse.builder()
                        .league(teamInfo.league())
                        .teamId(teamInfo.teamId())
                        .teamCheck(teamInfo.teamCheck())
                        .teamImg(teamInfo.teamImg())
                        .teamName(teamInfo.teamName())
                        .build())
                .toList();
    }

    private LeagueInfoListResponse parseLeagueInfoDtoToLeagueInfo(LeagueInfo leagueInfo) {
        //teamInfoList 복사
        List<TeamInfoListResponse> teamInfoListResponse = parseTeamInfoToTeamInfoDto(leagueInfo.teamInfo());

        return LeagueInfoListResponse.builder()
                .note(leagueInfo.note())
                .teamInfoListResponse(teamInfoListResponse)
                .build();
    }
}
