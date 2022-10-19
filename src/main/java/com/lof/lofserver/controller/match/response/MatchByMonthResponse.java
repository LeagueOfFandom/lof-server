package com.lof.lofserver.controller.match.response;

import com.lof.lofserver.controller.match.response.sub.CommonItemListResponse;
import lombok.Builder;

import java.util.List;

public record MatchByMonthResponse (
    String viewType,
    String date,
    List<CommonItemListResponse> matchList
) {
    @Builder
    public MatchByMonthResponse {
    }
}
