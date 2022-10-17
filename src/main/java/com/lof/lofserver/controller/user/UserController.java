package com.lof.lofserver.controller.user;

import com.lof.lofserver.controller.user.parser.UserControllerParser;
import com.lof.lofserver.controller.user.request.UserInfoRequest;
import com.lof.lofserver.filter.JsonWebToken;
import com.lof.lofserver.service.user.UserService;
import com.lof.lofserver.service.user.request.UserSavedInfo;
import com.lof.lofserver.service.user.response.UserResponseInfo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JsonWebToken jsonWebToken;
    private final UserControllerParser userControllerParser;

    @PostMapping("")
    @ApiOperation(value = "유저를 생성한다.", response = UserResponseInfo.class)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserInfoRequest userInfoRequest) {
        //userInfo -> UserSavedInfoDto
        UserSavedInfo userSavedInfo = userControllerParser.parseUserInfoDtoToUserSavedInfoDto(userInfoRequest);
        //user create
        UserResponseInfo userResponseInfo = userService.createUserByUserSavedInfoDto(userSavedInfo);
        //jwt token 생성
        String token = jsonWebToken.createJsonWebTokenById(userResponseInfo.id());
        //parse to response
        return ResponseEntity.ok(userControllerParser.parseUserResponseInfoToUserResponseInfoDto(token, userResponseInfo.isNewUser()));
    }

    @GetMapping("/nickname")
    @ApiOperation(value = "유저의 닉네임을 가져온다.", response = String.class)
    public ResponseEntity<?> getNickname(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken) {
        Long userId = (Long) request.getAttribute("id");
        return ResponseEntity.ok(userService.getNicknameByUserId(userId));
    }

    @PostMapping("/nickname")
    @ApiOperation(value = "닉네임을 설정한다.", response = String.class)
    public ResponseEntity<?> setUserNickName(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken, @RequestBody String nickname) {
        //get userId
        Long userId = (Long) request.getAttribute("id");
        //set user nickname
        return ResponseEntity.ok(userService.setUserNickName(userId, nickname));
    }

}
