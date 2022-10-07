package com.lof.lofserver.controller.team;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/team")
public class TeamController {

    @GetMapping("/allByUser")
    public ResponseEntity<?> getAllTeamListByUserId(@RequestParam String token) {
        return null;
    }

}
