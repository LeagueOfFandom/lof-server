package com.lof.lofserver.controller.user;

import com.lof.lofserver.controller.user.parser.UserControllerParser;
import com.lof.lofserver.controller.user.request.UserInfoRequest;
import com.lof.lofserver.controller.user.response.FcmTokenResponse;
import com.lof.lofserver.controller.user.response.NewUserResponse;
import com.lof.lofserver.controller.user.response.UserNickname;
import com.lof.lofserver.domain.user.sub.AlarmList;
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
import java.util.List;

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
    @ApiOperation(value = "유저의 닉네임을 가져온다.", response = UserNickname.class)
    public ResponseEntity<?> getNickname(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken) {
        Long userId = (Long) request.getAttribute("id");
        return ResponseEntity.ok(new UserNickname(userService.getNicknameByUserId(userId)));
    }

    @PostMapping("/nickname")
    @ApiOperation(value = "닉네임을 설정한다.", response = UserNickname.class)
    public ResponseEntity<?> setUserNickName(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken, @RequestBody String nickname) {
        //get userId
        Long userId = (Long) request.getAttribute("id");
        nickname = nickname.replace("\"", "");
        //set user nickname

        return ResponseEntity.ok(new UserNickname( userService.setUserNickName(userId, nickname)));
    }

    @GetMapping("/alarm")
    @ApiOperation(value = "전체알람 설정을 가져온다.", response = String.class)
    public ResponseEntity<?> getAlarm(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken) {
        Long userId = (Long) request.getAttribute("id");
        return ResponseEntity.ok(userService.getAlarmByUserId(userId));
    }

    @PostMapping("/alarm")
    @ApiOperation(value = "전체알람 설정을 변경한다.", response = String.class)
    public ResponseEntity<?> setAlarm(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken, @RequestBody Boolean alarm) {
        Long userId = (Long) request.getAttribute("id");
        return ResponseEntity.ok(userService.setAlarmByUserId(userId, alarm));
    }

    @PostMapping("/fcm")
    @ApiOperation(value = "fcm 토큰을 저장한다.", response = FcmTokenResponse.class)
    public ResponseEntity<?> setFcmToken(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken, @RequestBody String fcmToken) {
        Long userId = (Long) request.getAttribute("id");
        FcmTokenResponse fcmTokenResponse = new FcmTokenResponse(userService.setFcmTokenByUserId(userId, fcmToken));
        return ResponseEntity.ok(fcmTokenResponse);
    }

    @PostMapping("/temp")
    @ApiOperation(value = "임시 유저를 생성한다.", response = UserResponseInfo.class)
    public ResponseEntity<?> createTempUser(@RequestBody String fcmToken) {
        //user create
        UserResponseInfo userResponseInfo = userService.createTempUser(fcmToken);
        //jwt token 생성
        String token = jsonWebToken.createJsonWebTokenById(userResponseInfo.id());
        //parse to response
        return ResponseEntity.ok(userControllerParser.parseUserResponseInfoToUserResponseInfoDto(token, userResponseInfo.isNewUser()));
    }

    @GetMapping("/alarm/list")
    @ApiOperation(value = "알람 리스트를 가져온다.", response = AlarmList[].class)
    public ResponseEntity<?> getAlarmList(HttpServletRequest request, @RequestHeader("Authorization") String ignoredToken) {
        Long userId = (Long) request.getAttribute("id");
        List<AlarmList> alarmList = userService.getAlarmListByUserId(userId);
        return ResponseEntity.ok(alarmList);
    }

    @GetMapping("/new")
    @ApiOperation(value = "새로운 유저인지 확인한다.", response = NewUserResponse.class)
    public ResponseEntity<?> isNewUser(@RequestParam("email") String email) {
        return ResponseEntity.ok(new NewUserResponse(userService.isNewUser(email)));
    }
}
