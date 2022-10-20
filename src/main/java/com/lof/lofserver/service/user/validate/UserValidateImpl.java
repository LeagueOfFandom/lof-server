package com.lof.lofserver.service.user.validate;

import com.lof.lofserver.domain.user.UserRepository;
import com.lof.lofserver.exception.UserException;
import com.lof.lofserver.exception.UserExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

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
        if(userRepository.existsByNickname(nickname))
            throw new UserException(UserExceptionType.NICKNAME_ALREADY_EXIST);
    }

    private void validateNicknameLength(String nickname){
        if(nickname.length() < 3 || nickname.length() > 16){
            throw new UserException(UserExceptionType.NICKNAME_LENGTH_ERROR);
        }
    }

    private void validateNicknameType(String nickname){
        if(!Pattern.matches("^[a-zA-Z]*$", nickname)){
            throw new UserException(UserExceptionType.NICKNAME_TYPE_ERROR);
        }
    }
}
