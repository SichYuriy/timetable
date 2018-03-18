package com.gmail.at.sichyuriyy.user.service;

import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserService subject;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        subject = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
    }

    @Test
    public void findByUsername_shouldCallRepository() {
        subject.findByUserName("John_Snow");

        verify(userRepository).findByUsername("John_Snow");
    }

    @Test
    public void findByUsername_whenExists_shouldReturnResultFromRepository() {
        User userFromRepository = User.builder().id(1L).build();
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userFromRepository));

        Optional<User> actual = subject.findByUserName("John_Snow");

        assertThat(actual).containsSame(userFromRepository);
    }

    @Test
    public void findByUsername_whenNotExists_shouldReturnEmptyOptional() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        Optional<User> actual = subject.findByUserName("John_Snow");

        assertThat(actual).isEmpty();
    }

    @Test
    public void save_shouldEncryptPasswordAndCallCallRepository() {
        User user = User.builder().username("John_Snow").password("123").build();
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encoded_password");

        subject.save(user);

        ArgumentCaptor<User> repoUserCaptor = ArgumentCaptor.forClass(User.class);

        verify(bCryptPasswordEncoder).encode("123");
        verify(userRepository).save(repoUserCaptor.capture());

        User savedUser = repoUserCaptor.getValue();

        assertThat(savedUser.getUsername()).isEqualTo("John_Snow");
        assertThat(savedUser.getPassword()).isEqualTo("encoded_password");
    }

}