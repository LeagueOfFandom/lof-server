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
        if(objectList.size() == 0 || objectList == null || objectList.get(0) == null) {
            return commonItemListResponseList;
        }

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

        LocalDate currentDate = LocalDate.now().minusDays(1000);

        for(Object object : objectList) {
            if(object != null) {
                if (object instanceof BannerView) {
                    bannerList = ((BannerView) object).bannerList();
                } else {
                    if(object instanceof MatchView) {
                        if(!((MatchView)object).date().equals(currentDate.toString())){
                            if(!((MatchView)object).status().equals("running")) {
                                commonItemListResponseList.add(getDateView(LocalDate.parse(((MatchView) object).date())));
                                currentDate = LocalDate.parse(((MatchView) object).date());
                            }
                        }
                    }
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
        else if(object instanceof TextArrowView) return "HOME_MATCH_TITLE_VIEW";
        //error
        else return "ERROR_VIEW";
    }

    private CommonItemListResponse getDateView(LocalDate date){
        if(date.isBefore(LocalDate.now()))
            return CommonItemListResponse.builder()
                    .viewType("MATCH_RESULT_DATE_LINE")
                    .viewObject(new TextArrowView(date.getYear() + "??? " + date.getMonthValue() + "??? " + date.getDayOfMonth() + "???"))
                    .build();
        else if(date.isAfter(LocalDate.now()))
            return CommonItemListResponse.builder()
                    .viewType("MATCH_SCHEDULE_DATE_LINE")
                    .viewObject(new TextArrowView(date.getYear() + "??? " + date.getMonthValue() + "??? " + date.getDayOfMonth() + "???"))
                    .build();
        else
            return CommonItemListResponse.builder()
                    .viewType("MATCH_TODAY_DATE_LINE")
                    .viewObject(new TextArrowView(date.getYear() + "??? " + date.getMonthValue() + "??? " + date.getDayOfMonth() + "???"))
                    .build();
    }
}
