package com.lof.lofserver.service.user.validate;

import com.lof.lofserver.domain.user.UserEntity;
import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.exception.UserException;
import com.lof.lofserver.exception.UserExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidateImpl implements UserValidate {

    private final UserRepository userRepository;

    @Override
    public void validateNickname(String nickname){
        validateDuplicateNickname(nickname);
        validateNicknameLength(nickname);
        validateNicknameType(nickname);
    }

    private void validateDuplicateNickname(String nickname){
        Optional<UserEntity> optionalUser = userRepository.findByNickname(nickname);
        optionalUser.ifPresent(user -> {
            throw new UserException(UserExceptionType.NICKNAME_ALREADY_EXIST);
        });
    }

    private void validateNicknameLength(String nickname){
        if(nickname.length() < 3 || nickname.length() > 16){
            throw new UserException(UserExceptionType.NICKNAME_LENGTH_ERROR);
        }
    }

    private void validateNicknameType(String nickname){
        for (int i = 0; i < nickname.length(); i++) {
            if ((nickname.charAt(i)>= 0x61 && nickname.charAt(i) <= 0x7A) || (nickname.charAt(i) >=0x41 && nickname.charAt(i) <= 0x5A) || (nickname.charAt(i) >= 0x30 && nickname.charAt(i)<= 0x39)) { // 영문(소문자), 영문(대문자), 숫자
            } else {
                throw new UserException(UserExceptionType.NICKNAME_TYPE_ERROR);
            }
        }
    }
}
