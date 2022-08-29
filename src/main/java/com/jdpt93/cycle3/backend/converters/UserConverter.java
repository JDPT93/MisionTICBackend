package com.jdpt93.cycle3.backend.converters;

import com.jdpt93.cycle3.backend.data.UserData;
import com.jdpt93.cycle3.backend.entities.User;

public class UserConverter extends Converter<User, UserData> {

    @Override
    public User toEntity(UserData object) {
        return User.builder()
                .id(object.getId())
                .FullName(object.getFullName())
                .email(object.getEmail())
                .nickname(object.getNickname())
                .password(object.getPassword())
                .build();
    }

    @Override
    public UserData toData(User object) {
        return UserData.builder()
                .id(object.getId())
                .FullName(object.getFullName())
                .email(object.getEmail())
                .nickname(object.getNickname())
                .password(null)
                .build();
    }

}
