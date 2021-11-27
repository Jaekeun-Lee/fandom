package com.fc.fandom.user.domain;

import com.fc.fandom.cd.Grade;
import com.fc.fandom.cd.Role;
import com.fc.fandom.common.BaseTimeEntity;
import com.fc.fandom.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@ToString
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column
    private String phoneNumber;

    @Column
    private String profileImage;

    @Column
    private Grade grade;

    @Column
    private Role role;

    @Column
    private boolean authStatus;

    @Builder
    public User(String userId, String password, String phoneNumber, String profileImage, Grade grade, Role role, boolean authStatus) {
        this.userId = userId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.grade = grade;
        this.role = role;
        this.authStatus = authStatus;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public String getRoleTitle() {
        return this.role.getTitle();
    }

    public void updateUserInfo(UserDto userDto) {
        this.password = userDto.getPassword();
        this.phoneNumber = userDto.getPhoneNumber();
        this.profileImage = userDto.getProfileImage();
        this.grade = userDto.getGrade();
        this.role = userDto.getRole();
        this.authStatus = userDto.isAuthStatus();
    }
}