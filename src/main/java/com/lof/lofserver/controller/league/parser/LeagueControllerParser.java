package com.lof.lofserver.controller.league.parser;

import com.lof.lofserver.controller.league.response.BaseLeagueAndTeamListDto;
import com.lof.lofserver.controller.league.response.sub.TeamInfoDto;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;
import com.lof.lofserver.service.league.response.sub.TeamInfo;

import java.util.List;

public interface LeagueControllerParser {
    BaseLeagueAndTeamListDto parseLeagueInfoToBaseLeagueAndTeamList(BaseLeagueAndTeamList leagueInfo);
    List<TeamInfoDto> parseTeamInfoToTeamInfoDto(List<TeamInfo> teamInfoList);
}
