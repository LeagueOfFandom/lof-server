package com.lof.lofserver.controller.match.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lof.lofserver.controller.match.response.sub.GameDetailResponse;
import lombok.Builder;

import java.util.List;

public record MatchDetailResponse (
        String status,
        @JsonProperty("gameDetailList") //set 별 정보
        List<GameDetailResponse> gameDetailResponseList
){
    @Builder
    public MatchDetailResponse {
    }
}