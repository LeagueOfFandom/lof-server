package com.lof.lofserver.controller.test;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.service.test.FcmDto;
import com.lof.lofserver.service.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/userList")
    public List<UserEntity> getUserList() {
        return testService.getUserNickname();
    }

    @PostMapping("/sendFcm")
    public FcmDto sendFcm(@RequestBody String message, @RequestBody Long id) {
        return testService.sendFcm(message, id);
    }

}
