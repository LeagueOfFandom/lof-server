package com.lof.lofserver.controller.match.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public record MainPageResponse (
        List<String> bannerList,
        @JsonProperty("commonItemList")
        List<CommonItemListResponse> commonItemListResponse) {
    @Builder
    public MainPageResponse {
    }
}