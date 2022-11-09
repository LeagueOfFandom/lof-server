package com.lof.lofserver.service.match.detail;

import lombok.Getter;

@Getter
public class TeamVsTeamPrediction {
    private Long blueTeamWin;
    private Long redTeamWin;

    public TeamVsTeamPrediction(Long blueTeamWin, Long redTeamWin) {
        this.blueTeamWin = blueTeamWin;
        this.redTeamWin = redTeamWin;
    }
}
