package com.lof.lofserver.controller.league.response.sub;

import lombok.Builder;

import java.util.List;

public record LeagueInfoDto(String note, List<TeamInfoDto> teamInfoDto) {
    @Builder
    public LeagueInfoDto {
    }
}
