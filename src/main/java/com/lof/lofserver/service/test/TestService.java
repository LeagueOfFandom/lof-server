package com.lof.lofserver.service.test;

import com.lof.lofserver.domain.user.UserEntity;

import java.util.List;

public interface TestService {
    List<UserEntity> getUserNickname();

    List<FcmDto> sendFcm(String title, String message, Long id);
}
