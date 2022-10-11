package com.lof.lofserver.controller.user;

import com.lof.lofserver.controller.user.parser.UserControllerParser;
import com.lof.lofserver.controller.user.request.UserInfoDto;
import com.lof.lofserver.filter.JsonWebToken;
import com.lof.lofserver.service.user.UserService;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.response.UserResponseInfoDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JsonWebToken jsonWebToken;
    private final UserControllerParser userControllerParser;

    @PostMapping("/create")
    @ApiOperation(value = "유저를 생성한다.", response = UserResponseInfoDto.class)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserInfoDto userInfoDto) {
        //userInfo -> UserSavedInfoDto
        UserSavedInfoDto userSavedInfoDto = userControllerParser.parseUserInfoDtoToUserSavedInfoDto(userInfoDto);
        //user create
        UserResponseInfoDto userResponseInfoDto = userService.createUserByUserSavedInfoDto(userSavedInfoDto);
        //jwt token 생성
        String token = jsonWebToken.createJsonWebTokenById(userResponseInfoDto.id());

        return ResponseEntity.ok(token);
    }

    @GetMapping("/getNickname")
    @ApiOperation(value = "유저의 닉네임을 가져온다.", response = String.class)
    public ResponseEntity<?> getNickname(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getAttribute("id").toString());
        return ResponseEntity.ok(userService.getNicknameByUserId(userId));
    }

    @PostMapping("/setNickname")
    @ApiOperation(value = "닉네임을 설정한다.", response = String.class)
    public ResponseEntity<?> setUserNickName(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken, @RequestBody String nickname) {
        //get userId
        Long userId = Long.parseLong(request.getAttribute("id").toString());
        //set user nickname
        return ResponseEntity.ok(userService.setUserNickName(userId, nickname));
    }
}
