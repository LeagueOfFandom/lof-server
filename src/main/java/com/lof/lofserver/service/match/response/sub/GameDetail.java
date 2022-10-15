package com.lof.lofserver.service.match.response.sub;

import lombok.Builder;

import java.util.List;

public record GameDetail(
        String date,
        String time,
        String blueTeamAcronym,
        String redTeamAcronym,
        String blueTeamImageUrl,
        String redTeamImageUrl,
        Long blueTeamId,
        Long redTeamId,
        Long blueTeamScore,
        Long redTeamScore,
        Boolean blueWin,
        Boolean redWin,
        List<Object> detailInfoList
) {
    @Builder
    public GameDetail {
    }
}
