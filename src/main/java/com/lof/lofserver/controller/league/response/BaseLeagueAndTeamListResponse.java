package com.lof.lofserver.controller.league.response;

import com.lof.lofserver.controller.league.response.sub.LeagueInfoList;
import lombok.Builder;

import java.util.List;

public record BaseLeagueAndTeamListResponse(List<LeagueInfoList> leagueInfoListList, List<String> leagueNameList) {
    @Builder
    public BaseLeagueAndTeamListResponse {
    }
}
