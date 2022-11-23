package com.lof.lofserver.service.user.response;

import lombok.Builder;

public record EventResponse(String infoTitle, String infoContent, Boolean infoIsCheck, String infoTimeCompare, String infoDateTime) {
    @Builder
    public EventResponse {
    }
}
