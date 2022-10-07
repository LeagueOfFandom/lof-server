package com.lof.lofserver.service.user;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.service.user.request.UserSavedInfoDto;
import com.lof.lofserver.service.user.response.UserInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 신규 생성 테스트")
    void userCreate(){
        UserSavedInfoDto userSavedInfoDto = UserSavedInfoDto.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .leagueIdList(List.of(1L, 2L, 3L))
                .build();

        given(userRepository.findByEmail(userSavedInfoDto.email())).willReturn(null);
        given(userRepository.save(any())).willReturn(UserEntity.builder()
                .fcmToken(userSavedInfoDto.fcmToken())
                .email(userSavedInfoDto.email())
                .nickname(userSavedInfoDto.nickname())
                .profileImg(userSavedInfoDto.profileImg())
                .leagueList(userSavedInfoDto.leagueIdList())
                .build());

        //when
        UserInfoDto userInfoDto = userService.createUserByUserSavedInfoDto(userSavedInfoDto);

        //then
        assertThat(userInfoDto).isNotNull();
        assertThat(userInfoDto.isNewUser()).isTrue();
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
                .leagueIdList(List.of(1L, 2L, 3L))
                .build();

        given(userRepository.findByEmail(userSavedInfoDto.email())).willReturn(UserEntity.builder().build());

        //when
        UserInfoDto userInfoDto = userService.createUserByUserSavedInfoDto(userSavedInfoDto);

        //then
        assertThat(userInfoDto).isNotNull();
        assertThat(userInfoDto.isNewUser()).isFalse();
    }

}