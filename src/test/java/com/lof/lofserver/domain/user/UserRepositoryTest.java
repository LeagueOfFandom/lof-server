package com.lof.lofserver.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("사용자 null 값 저장 - 실패")
    void saveUserFail() {
        //given
        UserEntity userEntity = new UserEntity();

        //when
        Exception exception = assertThrows(Exception.class, () -> userRepository.save(userEntity));

        //then
        assertTrue(exception instanceof ConstraintViolationException);
    }
    @Test
    @DisplayName("사용자 저장 - 성공")
    void saveUserSuccess() {
        //given
        UserEntity userEntity = UserEntity.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .leagueList(List.of(1L, 2L, 3L))
                .build();

        //when
        UserEntity savedUserEntity = userRepository.save(userEntity);

        //then
        assertThat(savedUserEntity.getFcmToken()).isEqualTo("token");
        assertThat(savedUserEntity.getEmail()).isEqualTo("email");
        assertThat(savedUserEntity.getNickname()).isEqualTo("nickname");
        assertThat(savedUserEntity.getProfileImg()).isEqualTo("profileImage");
        assertThat(savedUserEntity.getLeagueList()).isEqualTo(List.of(1L, 2L, 3L));
    }

    @Test
    @DisplayName("사용자 조회 - 성공")
    void findUserSuccess() {
        //given
        UserEntity userEntity = UserEntity.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .leagueList(List.of(1L, 2L, 3L))
                .build();

        //when
        UserEntity savedUserEntity = userRepository.save(userEntity);
        UserEntity findUserEntity = userRepository.findById(savedUserEntity.getUserId()).orElse(null);

        //then
        assertThat(findUserEntity).isNotNull();
        assertThat(findUserEntity.getUserId()).isEqualTo(savedUserEntity.getUserId());
        assertThat(findUserEntity.getFcmToken()).isEqualTo("token");
        assertThat(findUserEntity.getEmail()).isEqualTo("email");
        assertThat(findUserEntity.getNickname()).isEqualTo("nickname");
        assertThat(findUserEntity.getProfileImg()).isEqualTo("profileImage");
        assertThat(findUserEntity.getLeagueList()).isEqualTo(List.of(1L, 2L, 3L));
    }

    @Test
    @DisplayName("사용자 조회 - 실패")
    void findUserFail() {
        //given
        UserEntity userEntity = UserEntity.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .leagueList(List.of(1L, 2L, 3L))
                .build();

        //when
        userRepository.save(userEntity);
        UserEntity findUserEntity = userRepository.findById(userEntity.getUserId() + 1).orElse(null);

        //then
        assertThat(findUserEntity).isEqualTo(null);
    }

    @Test
    @DisplayName("사용자 삭제 - 성공")
    void deleteUserSuccess() {
        //given
        UserEntity userEntity = UserEntity.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .leagueList(List.of(1L, 2L, 3L))
                .build();

        //when
        UserEntity savedUserEntity = userRepository.save(userEntity);
        userRepository.deleteById(savedUserEntity.getUserId());
        UserEntity findUserEntity = userRepository.findById(savedUserEntity.getUserId()).orElse(null);

        //then
        assertThat(findUserEntity).isEqualTo(null);
    }

    @Test
    @DisplayName("사용자 수정 - 성공")
    void updateUserSuccess() {
        //given
        UserEntity userEntity = UserEntity.builder()
                .fcmToken("token")
                .email("email")
                .nickname("nickname")
                .profileImg("profileImage")
                .leagueList(List.of(1L, 2L, 3L))
                .build();

        //when
        UserEntity savedUserEntity = userRepository.save(userEntity);
        savedUserEntity.setFcmToken("updateToken");
        savedUserEntity.setNickname("updateNickname");
        savedUserEntity.setProfileImg("updateProfileImage");
        savedUserEntity.setLeagueList(List.of(4L, 5L, 6L));
        UserEntity updatedUserEntity = userRepository.save(savedUserEntity);
        UserEntity findUserEntity = userRepository.findById(updatedUserEntity.getUserId()).orElse(null);

        //then
        assertThat(findUserEntity).isNotNull();
        assertThat(findUserEntity.getFcmToken()).isEqualTo("updateToken");
        assertThat(findUserEntity.getNickname()).isEqualTo("updateNickname");
        assertThat(findUserEntity.getProfileImg()).isEqualTo("updateProfileImage");
        assertThat(findUserEntity.getLeagueList()).isEqualTo(List.of(4L, 5L, 6L));

    }
}