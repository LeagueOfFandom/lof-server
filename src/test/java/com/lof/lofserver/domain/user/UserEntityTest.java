package com.lof.lofserver.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class UserEntityTest {

    @Test
    @DisplayName("userEntity 생성 테스트")
    void createUserEntity() {
        //given
        String fcmToken = "token";
        String email = "email";
        String nickname = "nickname";
        String profileImage = "profileImage";
        List<Long> leagueIdList = List.of(1L, 2L, 3L);

        //when
        UserEntity userEntity = UserEntity.builder()
                .fcmToken(fcmToken)
                .email(email)
                .nickname(nickname)
                .profileImg(profileImage)
                .leagueList(leagueIdList)
                .build();

        //then
        assertThat(userEntity.getFcmToken()).isEqualTo(fcmToken);
        assertThat(userEntity.getEmail()).isEqualTo(email);
        assertThat(userEntity.getNickname()).isEqualTo(nickname);
        assertThat(userEntity.getProfileImg()).isEqualTo(profileImage);
        assertThat(userEntity.getLeagueList()).isEqualTo(leagueIdList);
    }

    @Test
    @DisplayName("userEntity setter 테스트")
    void setUserEntity() {
        //given
        String fcmToken = "token";
        String email = "email";
        String nickname = "nickname";
        String profileImage = "profileImage";
        List<Long> leagueIdList = List.of(1L, 2L, 3L);

        UserEntity userEntity = UserEntity.builder()
                .fcmToken(fcmToken)
                .email(email)
                .nickname(nickname)
                .profileImg(profileImage)
                .leagueList(leagueIdList)
                .build();

        //when
        String newFcmToken = "newToken";
        String newEmail = "newEmail";
        String newNickname = "newNickname";
        String newProfileImage = "newProfileImage";
        List<Long> newLeagueIdList = List.of(4L, 5L, 6L);

        userEntity.setFcmToken(newFcmToken);
        userEntity.setEmail(newEmail);
        userEntity.setNickname(newNickname);
        userEntity.setProfileImg(newProfileImage);
        userEntity.setLeagueList(newLeagueIdList);

        //then
        assertThat(userEntity.getFcmToken()).isEqualTo(newFcmToken);
        assertThat(userEntity.getEmail()).isEqualTo(newEmail);
        assertThat(userEntity.getNickname()).isEqualTo(newNickname);
        assertThat(userEntity.getProfileImg()).isEqualTo(newProfileImage);
        assertThat(userEntity.getLeagueList()).isEqualTo(newLeagueIdList);
    }
}