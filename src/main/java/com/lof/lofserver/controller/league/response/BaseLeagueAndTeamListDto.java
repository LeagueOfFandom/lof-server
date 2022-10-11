package com.lof.lofserver.controller.league.response;

import com.lof.lofserver.controller.league.response.sub.LeagueInfoDto;
import lombok.Builder;

import java.util.List;

public record BaseLeagueAndTeamListDto(List<LeagueInfoDto> leagueInfoDtoList, List<String> leagueNameList) {
    @Builder
    public BaseLeagueAndTeamListDto {
    }
}
