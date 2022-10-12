package com.lof.lofserver.controller.match.parser;

import com.lof.lofserver.controller.match.response.CommonItemDto;

import java.util.List;

public interface MatchControllerParser {
    List<CommonItemDto> parseObjectListToCommonItemDtoList(List<Object> objectList);
}
