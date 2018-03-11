package com.gmail.at.sichyuriyy.user.repository;

import com.gmail.at.sichyuriyy.app.Application;
import com.gmail.at.sichyuriyy.app.JpaConfiguration;
import com.gmail.at.sichyuriyy.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
@Rollback
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUserName_returnCorrectUser() {
        User user1 = User.builder().username("user1").password("pass1").build();
        User user2 = User.builder().username("user2").password("pass2").build();

        userRepository.save(user1);
        userRepository.save(user2);

        assertThat(userRepository.findByUsername("non_existing_username")).isEmpty();
        assertThat(userRepository.findByUsername("user1")).containsSame(user1);
        assertThat(userRepository.findByUsername("user2")).containsSame(user2);
    }
}
