package com.lof.lofserver.service.league;

import com.lof.lofserver.service.league.response.AllLeagueAndTeamList;

import java.util.List;

public interface LeagueService {
    AllLeagueAndTeamList getAllLeagueAndTeamList(Long userId);
}
