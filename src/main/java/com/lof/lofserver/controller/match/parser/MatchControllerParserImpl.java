package com.lof.lofserver.controller.match.parser;

import com.lof.lofserver.controller.match.response.MainPageResponse;
import com.lof.lofserver.controller.match.response.sub.CommonItemListResponse;
import com.lof.lofserver.service.community.response.BannerView;
import com.lof.lofserver.service.community.response.TextArrowView;
import com.lof.lofserver.service.match.response.MatchView;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchControllerParserImpl implements MatchControllerParser {

    @Override
    public List<CommonItemListResponse> parseObjectListToMatchByMonthListResponse(List<Object> objectList) {
        List<CommonItemListResponse> commonItemListResponseList = new ArrayList<>();

        LocalDate currentDate = LocalDate.parse(((MatchView)objectList.get(0)).date());
        commonItemListResponseList.add(getDateView(currentDate));

        for(Object object : objectList) {
            if(object == null) continue;
            if(!((MatchView)object).date().equals(currentDate.toString())){
                commonItemListResponseList.add(getDateView(LocalDate.parse(((MatchView)object).date())));
                currentDate = LocalDate.parse(((MatchView)object).date());
            }
            commonItemListResponseList.add(CommonItemListResponse.builder()
                    .viewType(getViewType(object))
                    .viewObject(object).build());
        }

        return commonItemListResponseList;
    }

    @Override
    public MainPageResponse parseObjectListToMainPageResponse(List<Object> objectList) {
        List<String> bannerList = new ArrayList<>();
        List<CommonItemListResponse> commonItemListResponseList = new ArrayList<>();

        for(Object object : objectList) {
            if(object != null) {
                if (object instanceof BannerView) {
                    bannerList = ((BannerView) object).bannerList();
                } else {
                    commonItemListResponseList.add(CommonItemListResponse.builder()
                            .viewType(getViewType(object))
                            .viewObject(object).build());
                }
            }
        }

        return MainPageResponse.builder()
                .bannerList(bannerList)
                .commonItemListResponse(commonItemListResponseList).build();
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

    private CommonItemListResponse getDateView(LocalDate date){
        if(date.isBefore(LocalDate.now()))
            return CommonItemListResponse.builder()
                    .viewType("MATCH_RESULT_DATE_LINE")
                    .viewObject(date.getYear()+"년 "+date.getMonthValue()+"월 "+date.getDayOfMonth()+"일").build();
        else if(date.isAfter(LocalDate.now()))
            return CommonItemListResponse.builder()
                    .viewType("MATCH_SCHEDULE_DATE_LINE")
                    .viewObject(date.getYear()+"년 "+date.getMonthValue()+"월 "+date.getDayOfMonth()+"일").build();
        else
            return CommonItemListResponse.builder()
                    .viewType("MATCH_TODAY_DATE_LINE")
                    .viewObject(date.getYear()+"년 "+date.getMonthValue()+"월 "+date.getDayOfMonth()).build();
    }
}
