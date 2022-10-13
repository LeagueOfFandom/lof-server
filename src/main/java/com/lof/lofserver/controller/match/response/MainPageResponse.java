package com.lof.lofserver.controller.match.response;

import lombok.Builder;

import java.util.List;

public record MainPageResponse (List<String> bannerList, List<CommonItemListResponse> commonItemListResponse) {
    @Builder
    public MainPageResponse {
    }
}