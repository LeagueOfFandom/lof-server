package com.lof.lofserver.controller.team;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/team")
public class TeamController {

    @GetMapping("/allByUser")
    public ResponseEntity<?> getAllTeamListByUserId(@RequestHeader("Authorization") String token) {
        return null;
    }

}
