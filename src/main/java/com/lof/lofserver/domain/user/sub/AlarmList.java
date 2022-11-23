package com.lof.lofserver.domain.user.sub;

import lombok.Builder;

import java.time.LocalDateTime;


public record AlarmList(String viewType, Object viewObject) {
    @Builder
    public AlarmList {
    }
}
