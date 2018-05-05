package com.gmail.at.sichyuriyy.timetable.repository;

import com.gmail.at.sichyuriyy.app.Application;
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


    @Test
    public void save_shouldSaveAllFields() {
        User user = User.builder().username("user1").password("123321").build();
        userRepository.save(user);

        Timetable timetable = getTestTimetable(user);
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

        Timetable timetable1 = getTestTimetable(user1);
        Timetable timetable2 = getTestTimetable(user1);
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

        Timetable timetable1 = getTestTimetable(user1);
        Timetable timetable2 = getTestTimetable(user1);
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

        Timetable timetable1 = getTestTimetable(user1);
        Timetable timetable2 = getTestTimetable(user2);
        subject.saveAll(Arrays.asList(timetable1, timetable2));

        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Timetable> actual =
                subject.findAllByActiveFalseAndOwnerAndDeletedFalseOrderByIdDesc(user1, pageRequest);

        assertThat(actual).containsExactly(timetable1);
    }

    private Timetable getTestTimetable(User owner) {
        return Timetable.builder()
                .title("title")
                .description("description")
                .isPrivate(false)
                .activatedBefore(false)
                .active(false)
                .usePeriod(true)
                .periodDays(1)
                .periodWeeks(0)
                .subscribersCount(0)
                .owner(owner)
                .deleted(false)
                .build();
    }
}