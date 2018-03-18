package com.gmail.at.sichyuriyy.user.dto;

import com.gmail.at.sichyuriyy.common.dto.DtoTransformer;
import com.gmail.at.sichyuriyy.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserTransformer implements DtoTransformer<User, UserDto> {

    @Override
    public UserDto toDto(User user) {
        return Optional.ofNullable(user)
                .map(u -> UserDto.builder()
                        .id(u.getId())
                        .username(u.getUsername())
                        .password(u.getPassword())
                        .build()
                ).orElse(null);
    }

    @Override
    public User fromDto(UserDto userDto) {
        return Optional.ofNullable(userDto)
                .map(dto -> User.builder()
                        .id(dto.getId())
                        .username(dto.getUsername())
                        .password(dto.getPassword())
                        .build()
                ).orElse(null);
    }
}
