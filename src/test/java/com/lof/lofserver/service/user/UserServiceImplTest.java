package com.lof.lofserver.service.user;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.response.UserResponseInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        UserSavedInfoDto userSavedInfoDto = UserSavedInfoDto.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .build();

        given(userRepository.findByEmail(userSavedInfoDto.email())).willReturn(null);
        given(userRepository.save(any())).willReturn(UserEntity.builder()
                .fcmToken(userSavedInfoDto.fcmToken())
                .email(userSavedInfoDto.email())
                .nickname(userSavedInfoDto.nickname())
                .profileImg(userSavedInfoDto.profileImg())
                .build());

        //when
        UserResponseInfo userResponseInfo = userService.createUserByUserSavedInfoDto(userSavedInfoDto);

        //then
        assertThat(userResponseInfo).isNotNull();
        assertThat(userResponseInfo.isNewUser()).isTrue();
    }

    @Test
    @DisplayName("기존 유저 생성 테스트")
    void userCreated(){
        //given
        UserSavedInfoDto userSavedInfoDto = UserSavedInfoDto.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .build();

        given(userRepository.findByEmail(userSavedInfoDto.email())).willReturn(UserEntity.builder().build());

        //when
        UserResponseInfo userResponseInfo = userService.createUserByUserSavedInfoDto(userSavedInfoDto);

        //then
        assertThat(userResponseInfo).isNotNull();
        assertThat(userResponseInfo.isNewUser()).isFalse();
    }

    @Test
    @DisplayName("유저 닉네임 설정하기 - 실패(중복)")
    void duplicateNickname(){
        //given
        String user1Nickname = "user1";
        String user2Nickname = "user1";
        UserEntity userEntity1 = UserEntity.builder()
                .nickname(user1Nickname)
                .build();

        List<UserEntity> userList = new ArrayList<>();
        userList.add(userEntity1);

        given(userRepository.findByNickname(user1Nickname)).willReturn(userList);

        //when

        //then
        assertThatThrownBy(() -> userService.setUserNickName(1L, user2Nickname))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 존재하는 닉네임입니다.");

    }



}