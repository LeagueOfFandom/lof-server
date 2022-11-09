package com.lof.lofserver.service.match.detail;

import lombok.Getter;

import java.util.List;

@Getter
public class TeamVsTeamRosterInfo {
    private List<Roster> blueTeam;
    private List<Roster> redTeam;

    public TeamVsTeamRosterInfo(List<Roster> blueTeam, List<Roster> redTeam) {
        this.blueTeam = blueTeam;
        this.redTeam = redTeam;
    }
}
