package com.lof.lofserver.controller.league.parser;

import com.lof.lofserver.controller.league.response.BaseLeagueAndTeamListDto;
import com.lof.lofserver.controller.league.response.sub.LeagueInfoDto;
import com.lof.lofserver.controller.league.response.sub.TeamInfoDto;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;
import com.lof.lofserver.service.league.response.sub.LeagueInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueControllerParserImpl implements LeagueControllerParser {
    @Override
    public BaseLeagueAndTeamListDto parseLeagueInfoToBaseLeagueAndTeamList(BaseLeagueAndTeamList baseLeagueAndTeamList) {
        //leagueInfoList -> leagueInfoDtoList
        List<LeagueInfoDto> leagueInfoDtoList = baseLeagueAndTeamList.leagueInfoList().stream().
                map(this::parseLeagueInfoDtoToLeagueInfo)
                .toList();

        return BaseLeagueAndTeamListDto.builder()
                .leagueInfoDtoList(leagueInfoDtoList)
                .leagueNameList(baseLeagueAndTeamList.leagueNameList())
                .build();
    }

    private LeagueInfoDto parseLeagueInfoDtoToLeagueInfo(LeagueInfo leagueInfo) {
        //teamInfoList 복사
        List<TeamInfoDto> teamInfoDtoList = leagueInfo.teamInfo().stream().map(teamInfo ->
                TeamInfoDto.builder()
                        .league(teamInfo.league())
                        .teamId(teamInfo.teamId())
                        .teamCheck(teamInfo.teamCheck())
                        .teamImg(teamInfo.teamImg())
                        .teamName(teamInfo.teamName())
                        .build())
                .toList();

        return LeagueInfoDto.builder()
                .note(leagueInfo.note())
                .teamInfoDto(teamInfoDtoList)
                .build();
    }
}
