package com.lof.lofserver.service.league;

import com.lof.lofserver.controller.league.response.sub.TeamInfoDto;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;

import java.util.List;

public interface LeagueService {
    BaseLeagueAndTeamList getAllLeagueAndTeamListByUserId(Long userId);
    List<TeamInfoDto> getTeamListByUserId(Long userId);
}
