package com.lof.lofserver.domain.match.sub;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Live {
    private LocalDateTime opens_at;
    private Boolean supported;
    private String url;
}

