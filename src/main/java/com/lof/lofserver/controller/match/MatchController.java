package com.lof.lofserver.controller.match;

import com.lof.lofserver.controller.match.parser.MatchControllerParser;
import com.lof.lofserver.controller.match.response.MainPageResponse;
import com.lof.lofserver.controller.match.response.MatchDetailResponse;
import com.lof.lofserver.controller.match.response.sub.CommonItemListResponse;
import com.lof.lofserver.service.community.CommunityService;
import com.lof.lofserver.service.match.MatchService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/match")
@RequiredArgsConstructor
public class MatchController {

    private final CommunityService communityService;
    private final MatchService matchService;
    private final MatchControllerParser matchControllerParser;

    @GetMapping("/mainPage")
    @ApiOperation(value = "메인페이지에 필요한 정보를 가져온다.", response = MainPageResponse.class)
    public ResponseEntity<?> getMainPage(HttpServletRequest request,
                                         @RequestHeader("Authorization") String ignoredToken,
                                         @RequestParam(value = "onlyMyTeam") Boolean onlyMyTeam) {
        Long userId = (Long) request.getAttribute("id");
        LocalDate localDate = ZonedDateTime.now(ZoneId.of("UTC")).toLocalDate().minusMonths(1);
        List<Object> commonItemList = new ArrayList<>();
        //get bannerList
        commonItemList.add(communityService.getBannerList());
        //get liveMatch
        commonItemList.addAll(matchService.testLiveMatchViewList(userId));
        //get matchList
        commonItemList.add(communityService.getTextArrowView("My 경기 일정"));

        commonItemList.addAll(matchService.testMatchViewList());
        return ResponseEntity.ok(matchControllerParser.parseObjectListToMainPageResponse(commonItemList));
    }

    @GetMapping("/matchListByMonth")
    @ApiOperation(value = "달에 해당하는 경기를 가져온다.", response = CommonItemListResponse[].class)
    public ResponseEntity<?> getMatchListByDate(HttpServletRequest request,
                                                @RequestHeader("Authorization") String ignoredToken,
                                                @RequestParam("date") String date,
                                                @RequestParam(value = "onlyMyTeam") Boolean onlyMyTeam) {
        Long userId = (Long) request.getAttribute("id");
        LocalDate localDate = LocalDate.parse(date);
        //get matchList
        List<Object> commonItemList = new ArrayList<>(matchService.getMatchListByMonth(userId, localDate,onlyMyTeam));
        return ResponseEntity.ok(matchControllerParser.parseObjectListToMatchByMonthListResponse(commonItemList));
    }

    @GetMapping("/matchDetail")
    @ApiOperation(value = "경기 상세 정보를 가져온다.")
    public ResponseEntity<?> getMatchDetail(HttpServletRequest request,
                                            @RequestHeader("Authorization") String ignoredToken,
                                            @RequestParam("matchId") Long matchId) {

        return ResponseEntity.ok(matchService.getMatchDetail(matchId));
    }

}
