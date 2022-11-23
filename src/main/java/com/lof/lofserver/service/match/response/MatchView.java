package com.lof.lofserver.service.match.response;

import lombok.Builder;

public record MatchView(
        Long matchId,
        String homeName,
        String homeImg,
        String awayName,
        String awayImg,
        String date,
        String time,
        String league,
        Boolean isAlarm,
        Long homeScore,
        Long awayScore,
        String videoLink,
        String status
) {
    @Builder
    public MatchView {
    }
}

