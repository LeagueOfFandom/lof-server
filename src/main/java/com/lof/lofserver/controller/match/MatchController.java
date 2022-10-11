package com.lof.lofserver.controller.match;

import com.lof.lofserver.controller.match.response.CommonItemDto;
import com.lof.lofserver.service.community.CommunityService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/match")
@RequiredArgsConstructor
public class MatchController {

    private final CommunityService communityService;

    @GetMapping("/mainPage")
    @ApiOperation(value = "메인페이지에 필요한 정보를 가져온다.", response = CommonItemDto[].class)
    public ResponseEntity<?> getMainPage(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken) {
        List<Object> commonItemList = new ArrayList<>();
        //get bannerList
        commonItemList.add(communityService.getBannerList());
        //get liveMatch
        return ResponseEntity.ok("ok");
    }

}
