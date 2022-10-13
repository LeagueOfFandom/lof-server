package com.lof.lofserver.controller.league.parser;

import com.lof.lofserver.controller.league.response.BaseLeagueAndTeamListResponse;
import com.lof.lofserver.controller.league.response.sub.LeagueInfoList;
import com.lof.lofserver.controller.league.response.sub.TeamInfoList;
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
        List<LeagueInfoList> leagueInfoListList = baseLeagueAndTeamList.leagueInfoList().stream().
                map(this::parseLeagueInfoDtoToLeagueInfo)
                .toList();

        return BaseLeagueAndTeamListResponse.builder()
                .leagueInfoListList(leagueInfoListList)
                .leagueNameList(baseLeagueAndTeamList.leagueNameList())
                .build();
    }

    @Override
    public List<TeamInfoList> parseTeamInfoToTeamInfoDto(List<TeamInfo> teamInfoList) {
        return teamInfoList.stream().map(teamInfo -> TeamInfoList.builder()
                        .league(teamInfo.league())
                        .teamId(teamInfo.teamId())
                        .teamCheck(teamInfo.teamCheck())
                        .teamImg(teamInfo.teamImg())
                        .teamName(teamInfo.teamName())
                        .build())
                .toList();
    }

    private LeagueInfoList parseLeagueInfoDtoToLeagueInfo(LeagueInfo leagueInfo) {
        //teamInfoList 복사
        List<TeamInfoList> teamInfoList = parseTeamInfoToTeamInfoDto(leagueInfo.teamInfo());

        return LeagueInfoList.builder()
                .note(leagueInfo.note())
                .teamInfoListList(teamInfoList)
                .build();
    }
}
