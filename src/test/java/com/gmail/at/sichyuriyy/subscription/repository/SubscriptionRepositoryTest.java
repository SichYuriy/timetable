package com.gmail.at.sichyuriyy.subscription.repository;

import com.gmail.at.sichyuriyy.app.Application;
import com.gmail.at.sichyuriyy.subscription.domain.Subscription;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.repository.TimetableRepository;
import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.repository.UserRepository;
import org.junit.Before;
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
import java.util.Optional;

import static com.gmail.at.sichyuriyy.subscription.SubscriptionTestData.getNotApprovedSubscription;
import static com.gmail.at.sichyuriyy.timetable.TimetableTestData.getTimetable;
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

    private User user1;
    private User user2;
    private Timetable timetable1;
    private Timetable timetable2;
    private Timetable deletedTimetable;

    @Before
    public void setUp() {
        User timetableOwner = User.builder().username("owner").password("0").build();
        user1 = User.builder().username("user1").password("1").build();
        user2 = User.builder().username("user2").password("2").build();
        timetable1 = getTimetable(timetableOwner);
        timetable2 = getTimetable(timetableOwner);
        deletedTimetable = getTimetable(timetableOwner);
        deletedTimetable.setDeleted(true);

        userRepository.saveAll(Arrays.asList(timetableOwner, user1, user2));
        timetableRepository.saveAll(Arrays.asList(timetable1, timetable2, deletedTimetable));
    }

    @Test
    public void findAllBySubscriber_shouldMatchBySubscriberAndNotDeletedTimetable() {
        Subscription subscription1 = getNotApprovedSubscription(user1, timetable1);
        Subscription subscription2 = getNotApprovedSubscription(user2, timetable1);
        Subscription subscription3 = getNotApprovedSubscription(user1, deletedTimetable);
        subject.saveAll(Arrays.asList(subscription1, subscription2, subscription3));

        Page<Subscription> actual = subject.findAllBySubscriber(user1, PageRequest.of(0, 5));

        assertThat(actual).containsExactly(subscription1);
    }

    @Test
    public void findByTimetableAndSubscriber_shouldReturnMatched() {
        Subscription subscription = getNotApprovedSubscription(user1, timetable1);
        subject.save(subscription);

        Optional<Subscription> actual = subject.findByTimetableAndSubscriber(timetable1, user1);

        assertThat(actual).containsSame(subscription);
    }

    @Test
    public void findByTimetableAndSubscriber_shouldFilterOutByTimetable() {
        Subscription subscription = getNotApprovedSubscription(user1, timetable1);
        subject.save(subscription);

        Optional<Subscription> actual = subject.findByTimetableAndSubscriber(timetable2, user1);

        assertThat(actual).isEmpty();
    }

    @Test
    public void findByTimetableAndSubscriber_shouldFilterOutByUser() {
        Subscription subscription = getNotApprovedSubscription(user1, timetable1);
        subject.save(subscription);

        Optional<Subscription> actual = subject.findByTimetableAndSubscriber(timetable1, user2);

        assertThat(actual).isEmpty();
    }
}