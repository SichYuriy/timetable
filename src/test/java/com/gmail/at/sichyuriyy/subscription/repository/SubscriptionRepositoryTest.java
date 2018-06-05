package com.gmail.at.sichyuriyy.subscription.repository;

import com.gmail.at.sichyuriyy.app.Application;
import com.gmail.at.sichyuriyy.subscription.domain.Subscription;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.repository.TimetableRepository;
import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;

import static com.gmail.at.sichyuriyy.subscription.SubscriptionTestData.getTestSubscription;
import static com.gmail.at.sichyuriyy.timetable.TimetableTestData.getTestTimetable;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
@Rollback
public class SubscriptionRepositoryTest {

    @Autowired
    private SubscriptionRepository subject;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TimetableRepository timetableRepository;

    @Test
    public void findAllBySubscriber_shouldMatchBySubscriberAndNotDeletedTimetable() {
        User timetableOwner = User.builder().username("owner").password("0").build();
        User user1 = User.builder().username("user1").password("1").build();
        User user2 = User.builder().username("user2").password("2").build();
        Timetable timetable1 = getTestTimetable(timetableOwner);
        Timetable timetable2 = getTestTimetable(timetableOwner);
        timetable2.setDeleted(true);
        Subscription subscription1 = getTestSubscription(user1, timetable1);
        Subscription subscription2 = getTestSubscription(user2, timetable1);
        Subscription subscription3 = getTestSubscription(user1, timetable2);

        userRepository.saveAll(Arrays.asList(timetableOwner, user1, user2));
        timetableRepository.saveAll(Arrays.asList(timetable1, timetable2));
        subject.saveAll(Arrays.asList(subscription1, subscription2, subscription3));

        Page<Subscription> actual = subject.findAllBySubscriber(user1, PageRequest.of(0, 5));

        assertThat(actual).containsExactly(subscription1);
    }
}