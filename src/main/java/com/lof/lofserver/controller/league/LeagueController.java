package com.lof.lofserver.controller.league;

import com.lof.lofserver.controller.league.parser.LeagueControllerParser;
import com.lof.lofserver.controller.league.response.BaseLeagueAndTeamListDto;
import com.lof.lofserver.controller.league.response.sub.TeamInfoDto;
import com.lof.lofserver.service.league.LeagueService;
import com.lof.lofserver.service.league.response.BaseLeagueAndTeamList;
import com.lof.lofserver.service.league.response.sub.TeamInfo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/v1/league")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;
    private final LeagueControllerParser leagueControllerParser;

    @GetMapping("/allByUser")
    @ApiOperation(value = "유저의 리그와 팀 리스트를 가져온다.", response = BaseLeagueAndTeamListDto.class)
    public ResponseEntity<?> getAllLeagueAndTeamListByUserId(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken) {
        //get userId
        Long userId = Long.parseLong(request.getAttribute("id").toString());
        //get leagueInfoList
        BaseLeagueAndTeamList baseLeagueAndTeamList = leagueService.getAllLeagueAndTeamListByUserId(userId);
        //parse leagueInfoList to leagueInfoDtoList
        return ResponseEntity.ok(leagueControllerParser.parseLeagueInfoToBaseLeagueAndTeamList(baseLeagueAndTeamList));
    }

    @GetMapping("/selectedTeamByUser")
    @ApiOperation(value = "유저의 팀 리스트를 가져온다.", response = TeamInfoDto[].class)
    public ResponseEntity<?> getTeamListByUserId(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken) {
        //get userId
        Long userId = Long.parseLong(request.getAttribute("id").toString());
        //get leagueInfoList
        List<TeamInfo> teamInfoList = leagueService.getTeamListByUserId(userId);
        //parse leagueInfoList to leagueInfoDtoList
        return ResponseEntity.ok(leagueControllerParser.parseTeamInfoToTeamInfoDto(teamInfoList));
   }

}
