package com.lof.lofserver.controller.match.parser;

import com.lof.lofserver.controller.match.response.MatchByMonthResponse;
import com.lof.lofserver.controller.match.response.sub.CommonItemListResponse;
import com.lof.lofserver.controller.match.response.MainPageResponse;
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
    public List<MatchByMonthResponse> parseObjectListToMatchByMonthListResponse(List<Object> objectList) {
        List<CommonItemListResponse> commonItemListResponseList = new ArrayList<>();

        for(Object object : objectList) {
            if(object == null) continue;
            commonItemListResponseList.add(CommonItemListResponse.builder()
                    .viewType(getViewType(object))
                    .viewObject(object).build());
        }

        //날짜 별로 묶기 진행.
        LocalDate currentDate = LocalDate.now();
        List<MatchByMonthResponse> matchByMonthResponseList = new ArrayList<>();
        for(int i = 1; i <= currentDate.lengthOfMonth(); i++){
            if(i < currentDate.getDayOfMonth())
                matchByMonthResponseList.add(MatchByMonthResponse.builder()
                        .viewType("MATCH_RESULT_DATE_LINE")
                        .date(currentDate.getYear() + "년 " + currentDate.getMonthValue() + "월 " + i + "일")
                        .matchList(new ArrayList<>()).build());
            else if(i == currentDate.getDayOfMonth())
                matchByMonthResponseList.add(MatchByMonthResponse.builder()
                        .viewType("MATCH_TODAY_DATE_LINE")
                        .date(currentDate.getYear() + "년 " + currentDate.getMonthValue() + "월 " + i + "일")
                        .matchList(new ArrayList<>()).build());
            else
                matchByMonthResponseList.add(MatchByMonthResponse.builder()
                        .viewType("MATCH_SCHEDULE_DATE_LINE")
                        .date(currentDate.getYear() + "년 " + currentDate.getMonthValue() + "월 " + i + "일")
                        .matchList(new ArrayList<>()).build());
        }

        for(CommonItemListResponse commonItemListResponse : commonItemListResponseList) {
            if(commonItemListResponse.viewObject() instanceof MatchView matchView) {
                LocalDate matchDate = LocalDate.parse(matchView.date());
                matchByMonthResponseList.get(matchDate.getDayOfMonth() - 1).matchList().add(commonItemListResponse);
            }
        }

        return matchByMonthResponseList;
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
}
