package com.lof.lofserver.controller.match.parser;

import com.lof.lofserver.controller.match.response.CommonItemDto;
import com.lof.lofserver.service.community.response.BannerView;
import com.lof.lofserver.service.community.response.TextArrowView;
import com.lof.lofserver.service.match.response.MatchView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchControllerParserImpl implements MatchControllerParser {

    @Override
    public List<CommonItemDto> parseObjectListToCommonItemDtoList(List<Object> objectList) {
        return objectList.stream()
                .map(object -> CommonItemDto.builder()
                        .viewType(getViewType(object))
                        .viewObject(object)
                        .build())
                .toList();
    }

    private String getViewType(Object object) {
        //banner
        if(object instanceof BannerView) return "BANNER_VIEW";
        //match
        else if (object instanceof MatchView) {
            //live match
            if(((MatchView) object).status().equals("running")) return "LIVE_VIEW";
            //before match
            else if(((MatchView) object).status().equals("not_started")) return "MATCH_SCHEDULE_VIEW";
            //after match
            else return "MATCH_RESULT_VIEW";
        }
        //text arrow
        else if(object instanceof TextArrowView) return "TEXT_ARROW_VIEW";
        //error
        else return "ERROR_VIEW";
    }
}
