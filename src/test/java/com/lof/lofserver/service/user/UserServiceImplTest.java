package com.lof.lofserver.service.user;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.exception.UserException;
import com.lof.lofserver.exception.UserExceptionType;
import com.lof.lofserver.service.user.request.UserSavedInfo;
import com.lof.lofserver.service.user.response.UserResponseInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 닉네임 설정하기 - 성공")
    void setUserNickName() {
        //given
        String beforeNickname = "test";
        String afterNickname = "test2";
        UserEntity userEntity = UserEntity.builder()
                .nickname(afterNickname)
                .build();
        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(UserEntity.builder().nickname(beforeNickname).build()));
        given(userRepository.save(any(UserEntity.class))).willReturn(userEntity);

        //when
        String afterUserNickname = userService.setUserNickName(1L, afterNickname);

        //then
        assertThat(afterUserNickname).isEqualTo(afterNickname);
    }

    @Test
    @DisplayName("유저 닉네임 가져오기 - 성공")
    void getNicknameByUserId() {
        //given
        Long userId = 1L;
        String nickname = "test";
        given(userRepository.findById(any(Long.class))).willReturn(java.util.Optional.of(UserEntity.builder().nickname(nickname).build()));

        //when
        String result = userService.getNicknameByUserId(userId);

        //then
        assertThat(result).isEqualTo(nickname);
    }

    @Test
    @DisplayName("유저 신규 생성 테스트")
    void userCreate(){
        UserSavedInfo userSavedInfo = UserSavedInfo.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .build();

        given(userRepository.findByEmail(userSavedInfo.email())).willReturn(null);
        given(userRepository.save(any())).willReturn(UserEntity.builder()
                .fcmToken(userSavedInfo.fcmToken())
                .email(userSavedInfo.email())
                .nickname(userSavedInfo.nickname())
                .profileImg(userSavedInfo.profileImg())
                .build());

        //when
        UserResponseInfo userResponseInfo = userService.createUserByUserSavedInfoDto(userSavedInfo);

        //then
        assertThat(userResponseInfo).isNotNull();
        assertThat(userResponseInfo.isNewUser()).isTrue();
    }

    @Test
    @DisplayName("기존 유저 생성 테스트")
    void userCreated(){
        //given
        UserSavedInfo userSavedInfo = UserSavedInfo.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .build();

        given(userRepository.findByEmail(userSavedInfo.email())).willReturn(UserEntity.builder().build());

        //when
        UserResponseInfo userResponseInfo = userService.createUserByUserSavedInfoDto(userSavedInfo);

        //then
        assertThat(userResponseInfo).isNotNull();
        assertThat(userResponseInfo.isNewUser()).isFalse();
    }

    @Test
    @DisplayName("유저 닉네임 설정하기 - 실패(중복)")
    void validateDuplicateUserNickname() {
        //given
        String user1Nickname = "user1";
        String user2Nickname = "user1";

        //when
        given(userRepository.findByNickname(user1Nickname)).willReturn(Optional.of(UserEntity.builder().nickname(user1Nickname).build()));

        //then
        UserException throwable = assertThrows(UserException.class, () -> userService.setUserNickName(1L, user2Nickname));
        assertThat(throwable.getExceptionType()).isEqualTo(UserExceptionType.NICKNAME_ALREADY_EXIST);
    }


}