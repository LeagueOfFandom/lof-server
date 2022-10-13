package com.lof.lofserver.controller.league.response.sub;

import lombok.Builder;

import java.util.List;

public record LeagueInfoList(String note, List<TeamInfoList> teamInfoListList) {
    @Builder
    public LeagueInfoList {
    }
}
