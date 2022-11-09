package com.lof.lofserver.service.match.detail;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamVsTeam {
    private List<TeamVsTeamDetails> setInfoList = new ArrayList<>();

    public TeamVsTeam(List<TeamVsTeamDetails> setInfoList) {
        this.setInfoList = setInfoList;
    }
}
