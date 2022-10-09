package com.lof.lofserver.controller.league;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/league")
public class LeagueController {

    @GetMapping("/getAllByUser")
    public ResponseEntity<?> getAllLeagueAndTeamListByUserId(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("id").toString());
        return ResponseEntity.ok("ok");
    }

}
