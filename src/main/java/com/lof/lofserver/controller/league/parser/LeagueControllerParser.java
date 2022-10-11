package com.lof.lofserver.controller.league.parser;

import com.lof.lofserver.controller.league.response.BaseLeagueAndTeamListDto;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;

public interface LeagueControllerParser {
    BaseLeagueAndTeamListDto parseLeagueInfoToBaseLeagueAndTeamList(BaseLeagueAndTeamList leagueInfo);
}
