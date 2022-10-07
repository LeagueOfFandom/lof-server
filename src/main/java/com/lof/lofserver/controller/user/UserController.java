package com.lof.lofserver.controller.user;

import com.lof.lofserver.controller.user.parser.UserControllerParser;
import com.lof.lofserver.controller.user.request.UserInfo;
import com.lof.lofserver.service.user.UserService;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.response.UserResponseInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserControllerParser userControllerParser;
    @PostMapping("/google/userCreate")
    public ResponseEntity<?> createUser(@RequestBody UserInfo userInfo) {
        //userInfo -> UserSavedInfoDto
        UserSavedInfoDto userSavedInfoDto = userControllerParser.parseUserInfoDtoToUserSavedInfoDto(userInfo);
        //user create
        UserResponseInfoDto userResponseInfoDto = userService.createUserByUserSavedInfoDto(userSavedInfoDto);
        return ResponseEntity.ok(userService.createUserByUserSavedInfoDto(userSavedInfoDto));
    }
}
