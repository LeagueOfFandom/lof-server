package com.lof.lofserver.controller.league.response;

import com.lof.lofserver.controller.league.response.sub.LeagueInfoListResponse;
import lombok.Builder;

import java.util.List;

public record BaseLeagueAndTeamListResponse(List<LeagueInfoListResponse> leagueInfoListListResponse, List<String> leagueNameList) {
    @Builder
    public BaseLeagueAndTeamListResponse {
    }
}
