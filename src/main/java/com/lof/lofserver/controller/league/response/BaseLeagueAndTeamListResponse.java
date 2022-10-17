package com.lof.lofserver.controller.league.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lof.lofserver.controller.league.response.sub.LeagueInfoListResponse;
import lombok.Builder;

import java.util.List;

public record BaseLeagueAndTeamListResponse(
        @JsonProperty("leagueInfoList")
        List<LeagueInfoListResponse> leagueInfoListResponse,
        @JsonProperty("leagueNameList")
        List<String> leagueNameList) {
    @Builder
    public BaseLeagueAndTeamListResponse {
    }
}
