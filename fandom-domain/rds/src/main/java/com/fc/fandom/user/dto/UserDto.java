package com.fc.fandom.user.dto;

import com.fc.fandom.cd.Grade;
import com.fc.fandom.cd.Role;
import com.fc.fandom.user.domain.User;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    @NotBlank
    @Size(max = 20, min = 5, message = "아이디는 5 ~ 20자 사이로 생성해주세요.")
    private String userId;

    @NotBlank
    @Size(max = 20, min = 5, message = "아이디는 5 ~ 20자 사이로 생성해주세요.")
    private String password;

    @Size(max = 11, min = 11, message = "핸드폰 번호가 올바르지 않습니다.")
    private String phoneNumber;

    private String profileImage;

    @Builder.Default
    private Grade grade = Grade.MEMBER;

    @Builder.Default
    private Role role = Role.USER;

    private boolean authStatus;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .phoneNumber(phoneNumber)
                .profileImage(profileImage)
                .grade(grade)
                .role(Role.USER)
                .authStatus(false)
                .build();
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
        this.profileImage = user.getProfileImage();
        this.grade = user.getGrade();
        this.role = user.getRole();
        this.authStatus = user.isAuthStatus();
    }
}
