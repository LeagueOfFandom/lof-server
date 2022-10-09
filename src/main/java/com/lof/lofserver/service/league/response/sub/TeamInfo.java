package com.lof.lofserver.service.league.response.sub;

import lombok.Builder;

public record TeamInfo(Long teamId, String teamName, String teamImg, boolean teamCheck, String league) {
    @Builder
    public TeamInfo {
    }
}
