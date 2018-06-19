package com.gmail.at.sichyuriyy.timetable.repository;

import com.gmail.at.sichyuriyy.app.Application;
import com.gmail.at.sichyuriyy.subscription.domain.Subscription;
import com.gmail.at.sichyuriyy.subscription.repository.SubscriptionRepository;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
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
import java.util.Optional;

import static com.gmail.at.sichyuriyy.subscription.SubscriptionTestData.*;
import static com.gmail.at.sichyuriyy.timetable.TimetableTestData.*;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
@Rollback
public class TimetableRepositoryTest {

    @Autowired
    private TimetableRepository subject;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    public void save_shouldSaveAllFields() {
        User user = User.builder().username("user1").password("123321").build();
        userRepository.save(user);

        Timetable timetable = getTimetable(user);
        Timetable saved = subject.save(timetable);

        Optional<Timetable> found = subject.findById(saved.getId());

        assertThat(found).isNotEmpty();
        Timetable actual = found.orElse(new Timetable());
        assertThat(actual.getTitle()).isEqualTo("title");
        assertThat(actual.getDescription()).isEqualTo("description");
        assertThat(actual.getIsPrivate()).isFalse();
        assertThat(actual.getActivatedBefore()).isFalse();
        assertThat(actual.getUsePeriod()).isTrue();
        assertThat(actual.getPeriodDays()).isEqualTo(1);
        assertThat(actual.getPeriodWeeks()).isEqualTo(0);
        assertThat(actual.getOwner()).isEqualTo(user);
        assertThat(actual.getDeleted()).isFalse();
    }

    @Test
    public void findByActiveFalseAndOwnerAndDeletedFalse_shouldSelectByActive() {
        User user1 = User.builder().username("user1").password("1").build();
        userRepository.save(user1);

        Timetable timetable1 = getTimetable(user1);
        Timetable timetable2 = getTimetable(user1);
        timetable2.setActive(true);
        subject.saveAll(Arrays.asList(timetable1, timetable2));

        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Timetable> actual =
                subject.findAllByActiveFalseAndOwnerAndDeletedFalseOrderByIdDesc(user1, pageRequest);

        assertThat(actual).containsExactly(timetable1);
    }

    @Test
    public void findByActiveFalseAndOwnerAndDeletedFalse_shouldSelectByDeleted() {
        User user1 = User.builder().username("user1").password("1").build();
        userRepository.save(user1);

        Timetable timetable1 = getTimetable(user1);
        Timetable timetable2 = getTimetable(user1);
        timetable2.setDeleted(true);
        subject.saveAll(Arrays.asList(timetable1, timetable2));

        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Timetable> actual =
                subject.findAllByActiveFalseAndOwnerAndDeletedFalseOrderByIdDesc(user1, pageRequest);

        assertThat(actual).containsExactly(timetable1);
    }

    @Test
    public void findByActiveFalseAndOwnerAndDeletedFalse_shouldSelectByOwner() {
        User user1 = User.builder().username("user1").password("1").build();
        User user2 = User.builder().username("user2").password("2").build();
        userRepository.saveAll(Arrays.asList(user1, user2));

        Timetable timetable1 = getTimetable(user1);
        Timetable timetable2 = getTimetable(user2);
        subject.saveAll(Arrays.asList(timetable1, timetable2));

        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Timetable> actual =
                subject.findAllByActiveFalseAndOwnerAndDeletedFalseOrderByIdDesc(user1, pageRequest);

        assertThat(actual).containsExactly(timetable1);
    }

    @Test
    public void findByIdAndIsPrivateFalseAndDeletedFalse_whenAllMatch_shouldReturnMatchedResult() {
        User user1 = User.builder().username("user1").password("1").build();
        userRepository.save(user1);

        Timetable timetable = getTimetable(user1);
        subject.save(timetable);

        Optional<Timetable> actual = subject.findByIdAndIsPrivateFalseAndDeletedFalse(timetable.getId());

        assertThat(actual).containsSame(timetable);
    }

    @Test
    public void findByIdAndIsPrivateFalseAndDeletedFalse_whenPrivate_shouldNotMatch() {
        User user1 = User.builder().username("user1").password("1").build();
        userRepository.save(user1);

        Timetable timetable = getTimetable(user1);
        timetable.setIsPrivate(true);
        subject.save(timetable);

        Optional<Timetable> actual = subject.findByIdAndIsPrivateFalseAndDeletedFalse(timetable.getId());

        assertThat(actual).isEmpty();
    }

    @Test
    public void findByIdAndIsPrivateFalseAndDeletedFalse_whenDeleted_shouldNotMatch() {
        User user1 = User.builder().username("user1").password("1").build();
        userRepository.save(user1);

        Timetable timetable = getTimetable(user1);
        timetable.setDeleted(true);
        subject.save(timetable);

        Optional<Timetable> actual = subject.findByIdAndIsPrivateFalseAndDeletedFalse(timetable.getId());

        assertThat(actual).isEmpty();
    }

    @Test
    public void findPublicOrOwnTimetableById_whenPublicButAnotherOwner_shouldMatch() {
        User user1 = User.builder().username("user1").password("1").build();
        User user2 = User.builder().username("user2").password("1").build();
        userRepository.saveAll(Arrays.asList(user1, user2));

        Timetable timetable = getTimetable(user1);
        subject.save(timetable);

        Optional<Timetable> actual = subject.findPublicOrOwnTimetableById(timetable.getId(), user2.getId());

        assertThat(actual).containsSame(timetable);
    }

    @Test
    public void findPublicOrOwnTimetableById_whenPrivateAndAnotherOwner_shouldNotMatch() {
        User user1 = User.builder().username("user1").password("1").build();
        User user2 = User.builder().username("user2").password("1").build();
        userRepository.saveAll(Arrays.asList(user1, user2));

        Timetable timetable = getTimetable(user1);
        timetable.setIsPrivate(true);
        subject.save(timetable);

        Optional<Timetable> actual = subject.findPublicOrOwnTimetableById(timetable.getId(), user2.getId());

        assertThat(actual).isEmpty();
    }

    @Test
    public void findPublicOrOwnTimetableById_whenPrivateButSameOwner_shouldMatch() {
        User user1 = User.builder().username("user1").password("1").build();
        User user2 = User.builder().username("user2").password("1").build();
        userRepository.saveAll(Arrays.asList(user1, user2));

        Timetable timetable = getTimetable(user1);
        timetable.setIsPrivate(true);
        subject.save(timetable);

        Optional<Timetable> actual = subject.findPublicOrOwnTimetableById(timetable.getId(), user1.getId());

        assertThat(actual).containsSame(timetable);
    }

    @Test
    public void findOwnActiveSubscribedTimetables() {
        User owner = User.builder().username("owner").password("0").build();
        User subscriber = User.builder().username("subscriber").password("0").build();
        User user = User.builder().username("other").password("1").build();
        Timetable matchedTimetable = getActiveTimetable(owner);
        Timetable deletedTimetable = getDeletedTimetable(owner);
        Timetable notActiveTimetable = getTimetable(owner);
        Timetable notApprovedTimetable = getActiveTimetable(owner);
        Timetable rejectedTimetable = getActiveTimetable(owner);
        Timetable bannedTimetable = getActiveTimetable(owner);
        Subscription matchedSubscription = getApprovedSubscription(subscriber, matchedTimetable);
        Subscription notApprovedSubscription = getNotApprovedSubscription(subscriber, notApprovedTimetable);
        Subscription rejectedSubscription = getRejectedSubscription(subscriber, rejectedTimetable);
        Subscription bannedSubscription = getBannedSubscription(subscriber, bannedTimetable);
        Subscription notOwnSubscription = getApprovedSubscription(user, matchedTimetable);
        Subscription subscriptionOnNotActiveTimetable = getApprovedSubscription(subscriber, notActiveTimetable);
        Subscription subscriptionOnDeletedTimetable = getApprovedSubscription(subscriber, deletedTimetable);

        userRepository.saveAll(Arrays.asList(owner, subscriber, user));
        subject.saveAll(Arrays.asList(matchedTimetable, deletedTimetable, notActiveTimetable,
                notApprovedTimetable, rejectedTimetable, bannedTimetable));
        subscriptionRepository.saveAll(Arrays.asList(matchedSubscription, notApprovedSubscription, rejectedSubscription,
                bannedSubscription, notOwnSubscription, subscriptionOnNotActiveTimetable,
                subscriptionOnDeletedTimetable));

        Page<Timetable> actual = subject.findOwnActiveSubscribedTimetables(subscriber, PageRequest.of(0, 5));

        assertThat(actual).containsExactly(matchedTimetable);
    }
}