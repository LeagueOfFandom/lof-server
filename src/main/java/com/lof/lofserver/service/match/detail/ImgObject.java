package com.lof.lofserver.service.match.detail;

import lombok.Getter;

import java.util.List;

@Getter
public class ImgObject {
    private String text;
    private List<String> blueImgList;
    private List<String> redImgList;

    public ImgObject(String text, List<String> blueImgList, List<String> redImgList) {
        this.text = text;
        this.blueImgList = blueImgList;
        this.redImgList = redImgList;
    }
}
