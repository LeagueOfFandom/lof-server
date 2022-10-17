package com.lof.lofserver.controller.league.response.sub;

import lombok.Builder;

public record TeamInfoListResponse(Long teamId, String teamName, String teamImg, boolean teamCheck, String league) {
    @Builder
    public TeamInfoListResponse {
    }
}
