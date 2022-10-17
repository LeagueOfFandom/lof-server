package com.lof.lofserver.service.league;

import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;
import com.lof.lofserver.service.league.response.sub.TeamInfo;

import java.util.List;

public interface LeagueService {
    BaseLeagueAndTeamList getAllLeagueAndTeamListByUserId(Long userId);
    List<TeamInfo> getTeamListByUserId(Long userId);

    List<Long> setTeamListByUserId(Long userId, List<Long> teamIdList);
}
