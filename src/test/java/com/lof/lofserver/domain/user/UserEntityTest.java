package com.lof.lofserver.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

    @Test
    @DisplayName("userEntity validation 테스트")
    void validationUserEntity() {
        //given
        String email = "email";
        String nickname = "nickname";
        String profileImage = "profileImage";
        List<Long> leagueIdList = List.of(1L, 2L, 3L);

        UserEntity userEntity = UserEntity.builder()
                .fcmToken(null)
                .email(email)
                .nickname(nickname)
                .profileImg(profileImage)
                .leagueList(leagueIdList)
                .build();

        //when
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var constraintViolations = validator.validate(userEntity);

        //then
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("userEntity null setter 테스트")
    void setUserEntityNull() {
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
        userEntity.setFcmToken(null);
        userEntity.setNickname(null);
        userEntity.setProfileImg(null);
        userEntity.setLeagueList(null);
        userEntity.setTeamList(null);
        userEntity.setUserSelectedMatchList(null);

        //then
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        var constraintViolations = validator.validate(userEntity);

        assertThat(constraintViolations.size()).isEqualTo(7);
    }

}