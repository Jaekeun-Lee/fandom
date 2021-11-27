package com.fc.fandom.user.service;

import com.fc.fandom.exception.DuplicateUserIdException;
import com.fc.fandom.user.domain.User;
import com.fc.fandom.user.dto.UserDto;
import com.fc.fandom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * @param userDto
     * @return
     */
    public User save(UserDto userDto) {
        duplicateUserIdCheck(userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDto.toEntity());
    }


    /**
     * 아이디 중복 체크
     * @param userDto
     * @return true - 아이디 사용 가능
     * @throws DuplicateUserIdException 아이디 사용 불가능
     */
    public boolean duplicateUserIdCheck(UserDto userDto) {
        if (userRepository.findByUserId(userDto.getUserId()).isEmpty()) {
            return true;
        } else {
            throw new DuplicateUserIdException("이미 사용중인 아이디 입니다.");
        }
    }
}
