package com.lof.lofserver.controller.league.response.sub;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public record LeagueInfoListResponse(
        String note,
        @JsonProperty("teamInfoList")
        List<TeamInfoListResponse> teamInfoListResponse) {
    @Builder
    public LeagueInfoListResponse {
    }
}
