package com.lof.lofserver.controller.match;

import com.lof.lofserver.controller.match.parser.MatchControllerParser;
import com.lof.lofserver.controller.match.response.CommonItemDto;
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
    @ApiOperation(value = "메인페이지에 필요한 정보를 가져온다.", response = CommonItemDto[].class)
    public ResponseEntity<?> getMainPage(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken) {
        Long userId = (Long) request.getAttribute("id");
        LocalDate localDate = ZonedDateTime.now(ZoneId.of("UTC")).toLocalDate();
        List<Object> commonItemList = new ArrayList<>();
        //get bannerList
        commonItemList.add(communityService.getBannerList());
        //get liveMatch
        commonItemList.addAll(matchService.getLiveMatchList(userId));
        //get matchList
        commonItemList.addAll(matchService.getMatchListByDate(userId, localDate));
        return ResponseEntity.ok(matchControllerParser.parseObjectListToCommonItemDtoList(commonItemList));
    }

    @GetMapping("/getMatchListByDate")
    @ApiOperation(value = "날짜에 해당하는 경기를 가져온다.", response = CommonItemDto[].class)
    public ResponseEntity<?> getMatchListByDate(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken, @RequestParam("date") String date) {
        Long userId = (Long) request.getAttribute("id");
        LocalDate localDate = LocalDate.parse(date);
        //get matchList
        List<Object> commonItemList = new ArrayList<>(matchService.getMatchListByDate(userId, localDate));
        return ResponseEntity.ok(matchControllerParser.parseObjectListToCommonItemDtoList(commonItemList));
    }

}
