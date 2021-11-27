package com.fc.fandom.user.service;

import com.fc.fandom.exception.DuplicateUserIdException;
import com.fc.fandom.user.domain.User;
import com.fc.fandom.user.dto.UserDto;
import com.fc.fandom.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private final String SET_UP_USER_ID = "setupUserId01";

    @BeforeEach
    public void setup() {
        userRepository.save(createDto().toEntity());
    }

    @AfterEach
    public void cleanup() {
        userRepository.deleteAllInBatch();
    }


    @Test
    @DisplayName("회원가입 성공")
    void save() {
        // given
        UserDto expected = new UserDto();
        expected.setUserId("saveTest01");
        expected.setPassword("saveTest0123!@");

        // when
        User actual = userService.save(expected);

        // then
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        assertEquals(expected.getGrade(), actual.getGrade());
        assertEquals(expected.getRole(), actual.getRole());
        assertTrue(actual.getCreatedDate().isBefore(LocalDateTime.now()));
    }

    @Test
    @DisplayName("사용중인 아이디 에러")
    void duplicationExceptionTest() {
        assertThrows(DuplicateUserIdException.class, () -> userService.save(createDto()));
    }

    @Test
    @DisplayName("사용자 정보 수정")
    void update() {
        // given
        UserDto expected = new UserDto();
        expected.setPassword("abcdefg123!@");
        expected.setPhoneNumber("09876543210");
        expected.setProfileImage("update profile image");

        // when
        User user = userRepository.findByUserId(SET_UP_USER_ID).get();
        user.updateUserInfo(expected);
        User actual = userRepository.save(user);

        // then
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        assertEquals(expected.getProfileImage(), actual.getProfileImage());
        assertTrue(actual.getCreatedDate().isBefore(actual.getModifiedDate()));

    }

    private UserDto createDto() {
        return UserDto.builder()
                .userId(SET_UP_USER_ID)
                .password("Qwer1234!")
                .phoneNumber("01234567890")
                .build();
    }

}