package com.lof.lofserver.controller.league.parser;

import com.lof.lofserver.controller.league.response.BaseLeagueAndTeamListResponse;
import com.lof.lofserver.controller.league.response.sub.TeamInfoList;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;
import com.lof.lofserver.service.league.response.sub.TeamInfo;

import java.util.List;

public interface LeagueControllerParser {
    BaseLeagueAndTeamListResponse parseLeagueInfoToBaseLeagueAndTeamList(BaseLeagueAndTeamList leagueInfo);
    List<TeamInfoList> parseTeamInfoToTeamInfoDto(List<TeamInfo> teamInfoList);
}
