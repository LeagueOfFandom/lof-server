package com.lof.lofserver.controller.team;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/team")
public class TeamController {

    @GetMapping("/allByUser")
    public ResponseEntity<?> getAllTeamListByUserId(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("id").toString());
        return ResponseEntity.ok("ok");
    }

}
