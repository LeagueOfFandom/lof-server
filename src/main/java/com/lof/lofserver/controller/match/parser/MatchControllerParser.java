package com.lof.lofserver.controller.match.parser;

import com.lof.lofserver.controller.match.response.MainPageResponse;

import java.util.List;

public interface MatchControllerParser {
    MainPageResponse parseObjectListToMainPageResponse(List<Object> objectList);
}
