package com.lof.lofserver.service.user.service;

public interface UserService {
    UserInfoDto saveUser(String name, String email, String picture, String locale);
}
