package com.lof.lofserver.service.league.response;

import com.lof.lofserver.service.league.response.sub.LeagueInfo;
import lombok.Builder;

import java.util.List;

public record AllLeagueAndTeamList(List<LeagueInfo> leagueInfoList, List<String> leagueNameList) {
    @Builder
    public AllLeagueAndTeamList {
    }
}
