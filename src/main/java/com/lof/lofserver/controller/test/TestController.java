package com.lof.lofserver.controller.test;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.service.test.FcmDto;
import com.lof.lofserver.service.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
