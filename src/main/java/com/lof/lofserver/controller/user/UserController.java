package com.lof.lofserver.controller.user;

import com.lof.lofserver.controller.user.request.UserInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserInfoDto userInfoDto){
        return null;
    }
}
