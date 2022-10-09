package com.lof.lofserver.service.league.response;

import com.lof.lofserver.service.league.response.sub.LeagueInfo;
import lombok.Builder;

import java.util.List;

public record AllLeagueAndTeamList(List<LeagueInfo> leagueInfo, List<String> leagueList) {
    @Builder
    public AllLeagueAndTeamList {
    }
}
