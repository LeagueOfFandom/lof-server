package com.lof.lofserver.service.user.service;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.user.service.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.service.response.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public UserInfoDto saveUser(UserSavedInfoDto userSavedInfoDto) {
        //find user
        UserEntity userEntity = userRepository.findByEmail(userSavedInfoDto.email());

        //if user is existed, return userInfoDto
        if(userEntity != null)
            return buildUserInfoDto(userEntity, false);

        //create user
        userEntity = buildUserEntity(userSavedInfoDto);

        //save user
        UserEntity savedUserEntity = userRepository.save(userEntity);

        //return user info
        return buildUserInfoDto(savedUserEntity, true);
    }

    private UserInfoDto buildUserInfoDto(UserEntity userEntity, Boolean isNewUser) {
        return UserInfoDto.builder()
                .id(userEntity.getUserId())
                .isNewUser(isNewUser)
                .build();
    }

    private UserEntity buildUserEntity(UserSavedInfoDto userSavedInfoDto) {
        return UserEntity.builder()
                .fcmToken(userSavedInfoDto.fcmToken())
                .email(userSavedInfoDto.email())
                .nickname(userSavedInfoDto.nickname())
                .profileImg(userSavedInfoDto.profileImg())
                .leagueList(userSavedInfoDto.leagueIdList())
                .build();
    }
}
