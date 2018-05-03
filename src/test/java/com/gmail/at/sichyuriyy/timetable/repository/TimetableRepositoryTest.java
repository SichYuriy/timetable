package com.gmail.at.sichyuriyy.timetable.repository;

import com.gmail.at.sichyuriyy.app.Application;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
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

        Timetable timetable = Timetable.builder()
                .title("title")
                .description("description")
                .isPrivate(false)
                .activatedBefore(false)
                .active(false)
                .usePeriod(true)
                .periodDays(1)
                .periodWeeks(0)
                .subscribersCount(0)
                .owner(user)
                .deleted(false)
                .build();

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
}