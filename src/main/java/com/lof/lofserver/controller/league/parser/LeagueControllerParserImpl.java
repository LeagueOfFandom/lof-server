package com.lof.lofserver.controller.league.parser;

import com.lof.lofserver.controller.league.response.BaseLeagueAndTeamListDto;
import com.lof.lofserver.controller.league.response.sub.LeagueInfoDto;
import com.lof.lofserver.controller.league.response.sub.TeamInfoDto;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;
import com.lof.lofserver.service.league.response.sub.LeagueInfo;
import com.lof.lofserver.service.league.response.sub.TeamInfo;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    @Override
    public List<TeamInfoDto> parseTeamInfoToTeamInfoDto(List<TeamInfo> teamInfoList) {
        return teamInfoList.stream().map(teamInfo -> TeamInfoDto.builder()
                        .league(teamInfo.league())
                        .teamId(teamInfo.teamId())
                        .teamCheck(teamInfo.teamCheck())
                        .teamImg(teamInfo.teamImg())
                        .teamName(teamInfo.teamName())
                        .build())
                .toList();
    }

    private LeagueInfoDto parseLeagueInfoDtoToLeagueInfo(LeagueInfo leagueInfo) {
        //teamInfoList 복사
        List<TeamInfoDto> teamInfoList = parseTeamInfoToTeamInfoDto(leagueInfo.teamInfo());

        return LeagueInfoDto.builder()
                .note(leagueInfo.note())
                .teamInfoDtoList(teamInfoList)
                .build();
    }
}
