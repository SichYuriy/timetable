package com.gmail.at.sichyuriyy.user.dto;

import com.gmail.at.sichyuriyy.user.domain.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTransformerTest {

    private UserTransformer subject = new UserTransformer();

    @Test
    public void toDto_shouldSetAllFields() {
        User entity = User.builder()
                .id(1L)
                .username("John_Snow")
                .password("123")
                .build();

        UserDto dto = subject.toDto(entity);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getUsername()).isEqualTo("John_Snow");
        assertThat(dto.getPassword()).isEqualTo("123");
    }

    @Test
    public void toDto_whenEntityIsNull_shouldReturnNull() {
        assertThat(subject.toDto(null)).isNull();
    }

    @Test
    public void fromDto_shouldSetAllFields() {
        UserDto dto = UserDto.builder()
                .id(1L)
                .username("John_Snow")
                .password("123")
                .build();
        User entity = subject.fromDto(dto);

        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getUsername()).isEqualTo("John_Snow");
        assertThat(entity.getPassword()).isEqualTo("123");
    }

    @Test
    public void fromDto_whenDtoIsNull_shouldReturnNull() {
        assertThat(subject.fromDto(null)).isNull();
    }
}