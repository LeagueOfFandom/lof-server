package com.lof.lofserver.domain.match.sub;

import lombok.Getter;

@Getter
public class Stream {
    private String embed_url;
    private String language;
    private Boolean main;
    private Boolean official;
    private String raw_url;
}