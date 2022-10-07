package com.lof.lofserver.controller.user;

import com.lof.lofserver.controller.user.parser.UserControllerParser;
import com.lof.lofserver.controller.user.request.UserInfoDto;
import com.lof.lofserver.filter.JsonWebToken;
import com.lof.lofserver.service.user.UserService;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.response.UserResponseInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JsonWebToken jsonWebToken;
    private final UserControllerParser userControllerParser;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserInfoDto userInfoDto) {
        //userInfo -> UserSavedInfoDto
        UserSavedInfoDto userSavedInfoDto = userControllerParser.parseUserInfoDtoToUserSavedInfoDto(userInfoDto);
        //user create
        UserResponseInfoDto userResponseInfoDto = userService.createUserByUserSavedInfoDto(userSavedInfoDto);
        //jwt token 생성
        String token = jsonWebToken.createJsonWebTokenById(userResponseInfoDto.id());

        return ResponseEntity.ok(token);
    }
}
