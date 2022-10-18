package com.lof.lofserver.service.user.validate;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.exception.UserException;
import com.lof.lofserver.exception.UserExceptionType;
import com.lof.lofserver.service.user.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserValidateImplTest {

    @InjectMocks
    private UserValidateImpl userValidate;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 닉네임 설정하기 - 실패(중복)")
    void validateDuplicateNickname() {
        //given
        String user1Nickname = "user1";
        String user2Nickname = "user1";
        given(userRepository.findByNickname(user1Nickname)).willReturn(Optional.of(UserEntity.builder().nickname(user1Nickname).build()));

        //when
        UserException throwable = assertThrows(UserException.class, () -> userValidate.validateNickname(user2Nickname));

        //then
        assertThat(throwable.getExceptionType()).isEqualTo(UserExceptionType.NICKNAME_ALREADY_EXIST);
    }

    @Test
    @DisplayName("유저 닉네임 변경하기 - 실패(글자수 초과)")
    void validateNicknameLength_over(){
        //given
        String nickname = "123fksdfjskfjskfjj";

        //when
        UserException throwable = assertThrows(UserException.class, () -> userValidate.validateNickname(nickname));

        //then
        assertThat(throwable.getExceptionType()).isEqualTo(UserExceptionType.NICKNAME_LENGTH_ERROR);
    }

    @Test
    @DisplayName("유저 닉네임 변경하기 - 실패(글자수 미만)")
    void validateNicknameLength_sub(){
        //given
        String nickname = "1a";

        //when
        UserException throwable = assertThrows(UserException.class, () -> userValidate.validateNickname(nickname));

        //then
        assertThat(throwable.getExceptionType()).isEqualTo(UserExceptionType.NICKNAME_LENGTH_ERROR);
    }

    @Test
    @DisplayName("유저 닉네임 변경하기 - 실패(영어/숫자 이외 문자 포함)")
    void validateNicknameType(){
        //given
        String nickname = "1a한글";
        String nickname2 = "/Frkds";

        //when
        UserException throwable = assertThrows(UserException.class, () -> userValidate.validateNickname(nickname));
        UserException throwable2 = assertThrows(UserException.class, () -> userValidate.validateNickname(nickname2));

        //then
        assertThat(throwable.getExceptionType()).isEqualTo(UserExceptionType.NICKNAME_TYPE_ERROR);
        assertThat(throwable2.getExceptionType()).isEqualTo(UserExceptionType.NICKNAME_TYPE_ERROR);
    }

}