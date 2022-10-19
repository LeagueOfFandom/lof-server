package com.lof.lofserver.controller.match.parser;

import com.lof.lofserver.controller.match.response.MainPageResponse;
import com.lof.lofserver.controller.match.response.MatchByMonthResponse;
import com.lof.lofserver.controller.match.response.sub.CommonItemListResponse;

import java.util.List;

public interface MatchControllerParser {
    MainPageResponse parseObjectListToMainPageResponse(List<Object> objectList);

    List<MatchByMonthResponse> parseObjectListToMatchByMonthListResponse(List<Object> objectList);
}
