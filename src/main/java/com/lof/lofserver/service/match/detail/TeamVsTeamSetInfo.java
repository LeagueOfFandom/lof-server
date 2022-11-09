package com.lof.lofserver.service.match.detail;

import lombok.Getter;

@Getter
public class TeamVsTeamSetInfo {
    private String viewType;
    private StringObject viewStringObject;
    private ImgObject viewImgObject;

    public TeamVsTeamSetInfo(String viewType, StringObject viewStringObject, ImgObject viewImgObject) {
        this.viewType = viewType;
        this.viewStringObject = viewStringObject;
        this.viewImgObject = viewImgObject;
    }
}
