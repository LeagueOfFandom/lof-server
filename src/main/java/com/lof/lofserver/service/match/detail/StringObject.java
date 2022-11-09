package com.lof.lofserver.service.match.detail;

import lombok.Getter;

@Getter
public class StringObject {
    private String text;
    private String blueString;
    private String redString;

    public StringObject(String text, String blueString, String redString) {
        this.text = text;
        this.blueString = blueString;
        this.redString = redString;
    }
}
