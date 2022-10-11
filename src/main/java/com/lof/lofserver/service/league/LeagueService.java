package com.lof.lofserver.service.league;

import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;

public interface LeagueService {
    BaseLeagueAndTeamList getAllLeagueAndTeamListByUserId(Long userId);
}
