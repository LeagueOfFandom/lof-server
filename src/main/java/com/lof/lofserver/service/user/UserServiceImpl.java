package com.lof.lofserver.service.user;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.exception.UserException;
import com.lof.lofserver.exception.UserExceptionType;
import com.lof.lofserver.service.user.request.UserSavedInfo;
import com.lof.lofserver.service.user.response.UserResponseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String setUserNickName(Long userId, String nickname) {
        validateDuplicateNickname(nickname);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        userEntity.setNickname(nickname);
        return userRepository.save(userEntity).getNickname();
    }

    @Override
    public String getNicknameByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        return userEntity.getNickname();
    }

    private void validateDuplicateNickname(String nickname){
        Optional<UserEntity> optionalUser = userRepository.findByNickname(nickname);
        optionalUser.ifPresent(user -> {
            throw new UserException(UserExceptionType.NICKNAME_ALREADY_EXIST);
        });
    }

    /** 유저 생성
     * @param userSavedInfo - 유저 정보
     * @return UserResponseInfoDto
     */
    @Override
    public UserResponseInfo createUserByUserSavedInfoDto(UserSavedInfo userSavedInfo) {
        //find user
        UserEntity userEntity = userRepository.findByEmail(userSavedInfo.email());

        //if user is existed, return userInfoDto
        if(userEntity != null)
            return parseUserResponseInfoDto(userEntity, false);

        //create user
        userEntity = createUserEntity(userSavedInfo);

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
    private UserResponseInfo parseUserResponseInfoDto(UserEntity userEntity, Boolean isNewUser) {
        return UserResponseInfo.builder()
                .id(userEntity.getUserId())
                .isNewUser(isNewUser)
                .build();
    }

    /**
     * create UserEntity by UserSavedInfoDto
     * @param userSavedInfo - user entity 에 저장할 정보
     * @return UserEntity
     */
    private UserEntity createUserEntity(UserSavedInfo userSavedInfo) {
        return UserEntity.builder()
                .fcmToken(userSavedInfo.fcmToken())
                .email(userSavedInfo.email())
                .nickname(userSavedInfo.nickname())
                .profileImg(userSavedInfo.profileImg())
                .build();
    }
}
