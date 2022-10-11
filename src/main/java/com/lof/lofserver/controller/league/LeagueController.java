package com.lof.lofserver.controller.league;

import com.lof.lofserver.controller.league.parser.LeagueControllerParser;
import com.lof.lofserver.service.league.LeagueService;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/league")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;
    private final LeagueControllerParser leagueControllerParser;

    @GetMapping("/getAllByUser")
    public ResponseEntity<?> getAllLeagueAndTeamListByUserId(HttpServletRequest request) {
        //get userId
        Long userId = Long.parseLong(request.getAttribute("id").toString());
        //get leagueInfoList
        BaseLeagueAndTeamList baseLeagueAndTeamList = leagueService.getAllLeagueAndTeamListByUserId(userId);
        //parse leagueInfoList to leagueInfoDtoList
        return ResponseEntity.ok(leagueControllerParser.parseLeagueInfoToBaseLeagueAndTeamList(baseLeagueAndTeamList));
    }

}
