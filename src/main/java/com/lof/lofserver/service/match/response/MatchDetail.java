package com.lof.lofserver.service.match.response;

import com.lof.lofserver.service.match.response.sub.GameDetail;
import lombok.Builder;

import java.util.List;

public record MatchDetail(
        String status,
        List<GameDetail> gameDetail
) {
    @Builder
    public MatchDetail {
    }
}
