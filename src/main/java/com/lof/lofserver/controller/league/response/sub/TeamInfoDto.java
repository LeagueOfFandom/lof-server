package com.lof.lofserver.controller.league.response.sub;

import lombok.Builder;

public record TeamInfoDto(Long teamId, String teamName, String teamImg, boolean teamCheck, String league) {
    @Builder
    public TeamInfoDto {
    }
}
