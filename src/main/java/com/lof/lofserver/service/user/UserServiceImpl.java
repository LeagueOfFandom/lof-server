package com.lof.lofserver.service.user;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.response.UserResponseInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String getNicknameByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        return userEntity.getNickname();
    }

    /** 유저 생성
     * @param userSavedInfoDto - 유저 정보
     * @return UserResponseInfoDto
     */
    @Override
    public UserResponseInfoDto createUserByUserSavedInfoDto(UserSavedInfoDto userSavedInfoDto) {
        //find user
        UserEntity userEntity = userRepository.findByEmail(userSavedInfoDto.email());

        //if user is existed, return userInfoDto
        if(userEntity != null)
            return parseUserResponseInfoDto(userEntity, false);

        //create user
        userEntity = createUserEntity(userSavedInfoDto);

        //save user
        UserEntity savedUserEntity = userRepository.save(userEntity);

        //return user info
        return parseUserResponseInfoDto(savedUserEntity, true);
    }

    /**
     * create UserResponseInfoDto by UserEntity
     * @param userEntity - user entity
     * @param isNewUser - boolean value
     * @return UserResponseInfoDto
     */
    private UserResponseInfoDto parseUserResponseInfoDto(UserEntity userEntity, Boolean isNewUser) {
        return UserResponseInfoDto.builder()
                .id(userEntity.getUserId())
                .isNewUser(isNewUser)
                .build();
    }

    /**
     * create UserEntity by UserSavedInfoDto
     * @param userSavedInfoDto - user entity 에 저장할 정보
     * @return UserEntity
     */
    private UserEntity createUserEntity(UserSavedInfoDto userSavedInfoDto) {
        return UserEntity.builder()
                .fcmToken(userSavedInfoDto.fcmToken())
                .email(userSavedInfoDto.email())
                .nickname(userSavedInfoDto.nickname())
                .profileImg(userSavedInfoDto.profileImg())
                .build();
    }
}
