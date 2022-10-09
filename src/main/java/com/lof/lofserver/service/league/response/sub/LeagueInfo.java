package com.lof.lofserver.service.league.response.sub;

import lombok.Builder;

import java.util.List;

public record LeagueInfo(String note, List<TeamInfo> teamInfo) {
    @Builder
    public LeagueInfo {
    }
}
