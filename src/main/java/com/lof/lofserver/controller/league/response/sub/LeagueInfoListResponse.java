package com.lof.lofserver.controller.league.response.sub;

import lombok.Builder;

import java.util.List;

public record LeagueInfoListResponse(String note, List<TeamInfoListResponse> teamInfoListResponse) {
    @Builder
    public LeagueInfoListResponse {
    }
}
