package com.gmail.at.sichyuriyy.security.service;

import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    private UserRepository userRepository;
    private UserDetailsServiceImpl subject;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
        subject = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void loadUserByUserName_shouldCallRepository() {
        User userFromRepository = User.builder()
                .username("John_Snow")
                .password("123")
                .build();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userFromRepository));

        subject.loadUserByUsername("John_Snow");

        verify(userRepository).findByUsername("John_Snow");
    }

    @Test
    public void loadUserByUserName_whenExist_shouldReturnUserWithCredentials() {
        User userFromRepository = User.builder()
                .username("John_Snow")
                .password("123")
                .build();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userFromRepository));

        UserDetails userDetails = subject.loadUserByUsername("username_example");

        assertThat(userDetails.getUsername()).isEqualTo("John_Snow");
        assertThat(userDetails.getPassword()).isEqualTo("123");
    }

    @Test
    public void loadUserByUserName_whenNotExist_shouldThrowUsernameNotFoundException() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThatExceptionOfType(UsernameNotFoundException.class)
                .isThrownBy(() -> subject.loadUserByUsername("John_Snow"))
                .withMessage("Username John_Snow does not exist");
    }
}