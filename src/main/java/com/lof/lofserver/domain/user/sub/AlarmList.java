package com.lof.lofserver.domain.user.sub;

import lombok.Builder;

import java.time.LocalDateTime;


public record AlarmList(String title, String content, LocalDateTime createdAt,String viewType) {
    @Builder
    public AlarmList {
    }
}
