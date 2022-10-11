package com.lof.lofserver.controller.match;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/match")
public class MatchController {

    @GetMapping("/mainPage")
    public ResponseEntity<?> getMainPage(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken) {
        return ResponseEntity.ok("ok");
    }

}
