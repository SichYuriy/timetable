package com.gmail.at.sichyuriyy.user.repository;

import com.gmail.at.sichyuriyy.app.Application;
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
    private UserRepository subject;

    @Test
    public void findByUserName_returnCorrectUser() {
        User user1 = User.builder().username("user1").password("pass1").build();
        User user2 = User.builder().username("user2").password("pass2").build();

        subject.save(user1);
        subject.save(user2);

        assertThat(subject.findByUsername("non_existing_username")).isEmpty();
        assertThat(subject.findByUsername("user1")).containsSame(user1);
        assertThat(subject.findByUsername("user2")).containsSame(user2);
    }
}
