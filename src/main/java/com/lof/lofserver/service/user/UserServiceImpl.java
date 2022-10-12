package com.lof.lofserver.service.user;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
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
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        });
    }

    /** 유저 생성
     * @param userSavedInfoDto - 유저 정보
     * @return UserResponseInfoDto
     */
    @Override
    public UserResponseInfo createUserByUserSavedInfoDto(UserSavedInfoDto userSavedInfoDto) {
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
    private UserResponseInfo parseUserResponseInfoDto(UserEntity userEntity, Boolean isNewUser) {
        return UserResponseInfo.builder()
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
