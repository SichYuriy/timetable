package com.gmail.at.sichyuriyy.registration.service;

import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RegistrationServiceImplTest {

    @Mock
    private UserService userService;

    private ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

    private RegistrationServiceImpl subject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        subject = new RegistrationServiceImpl(userService);
    }

    @Test
    public void register_whenUsernameIsFree_shouldSaveUser() {
        User user = User.builder()
                .username("John_Snow")
                .password("123")
                .build();

        when(userService.findByUserName(anyString())).thenReturn(Optional.empty());

        subject.register(user);

        verify(userService).findByUserName("John_Snow");
        verify(userService).save(captor.capture());

        assertThat(captor.getValue().getUsername()).isEqualTo("John_Snow");
        assertThat(captor.getValue().getPassword()).isEqualTo("123");
    }

    @Test
    public void register_whenUsernameIsBusy_shouldThrowUserAlreadyExistException() {
        User user = User.builder()
                .username("John_Snow")
                .password("123")
                .build();

        when(userService.findByUserName(anyString()))
                .thenReturn(Optional.of(User.builder().username("John_Snow").build()));

        assertThatExceptionOfType(UserAlreadyExistException.class)
                .isThrownBy(() -> subject.register(user))
                .withMessage("User John_Snow is already exist");

        verify(userService).findByUserName("John_Snow");
        verify(userService, never()).save(any(User.class));
    }
}