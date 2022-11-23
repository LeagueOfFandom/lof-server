package com.lof.lofserver.controller.test;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.service.test.FcmDto;
import com.lof.lofserver.service.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/")
    public Boolean healthCheck() {
        return true;
    }

    @GetMapping("/userList")
    public List<UserEntity> getUserList() {
        return testService.getUserNickname();
    }

    @PostMapping("/sendFcm")
    public List<FcmDto> sendFcm(@RequestParam String message, @RequestParam Long id, @RequestParam String title) {
        return testService.sendFcm(title, message, id);
    }

}
