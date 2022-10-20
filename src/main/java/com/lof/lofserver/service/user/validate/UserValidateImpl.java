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
        UserEntity userEntity = userRepository.findByNickname(nickname);
        if(userEntity != null)
            throw new UserException(UserExceptionType.NICKNAME_ALREADY_EXIST);
    }

    private void validateNicknameLength(String nickname){
        if(nickname.length() < 3 || nickname.length() > 16){
            throw new UserException(UserExceptionType.NICKNAME_LENGTH_ERROR);
        }
    }

    private void validateNicknameType(String nickname){
        for (int i = 0; i < nickname.length(); i++) {
            if ((nickname.charAt(i)>= 'a' && nickname.charAt(i) <= 'z') || (nickname.charAt(i) >= 'A' && nickname.charAt(i) <= 'Z') || (nickname.charAt(i) >= '0' && nickname.charAt(i)<= '9')) { // 영문(소문자), 영문(대문자), 숫자
            } else {
                throw new UserException(UserExceptionType.NICKNAME_TYPE_ERROR);
            }
        }
    }
}
