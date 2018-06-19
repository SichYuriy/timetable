package com.gmail.at.sichyuriyy.event.repository;

import com.gmail.at.sichyuriyy.app.Application;
import com.gmail.at.sichyuriyy.event.domain.Event;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.repository.TimetableRepository;
import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static com.gmail.at.sichyuriyy.timetable.TimetableTestData.getTimetable;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
@Rollback
public class EventRepositoryTest {

    @Autowired
    private EventRepository subject;

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create_shouldSaveAllFields() {
        User user = User.builder().username("user1").password("1").build();
        userRepository.save(user);
        Timetable timetable = getTimetable(user);
        timetableRepository.save(timetable);

        Event event = Event.builder()
                .timetable(timetable)
                .title("title")
                .description("description")
                .location("location")
                .usePeriod(true)
                .periodDays(1)
                .periodWeeks(0)
                .startDate(LocalDateTime.of(2018, Month.MAY, 7, 13, 20))
                .endDate(LocalDateTime.of(2018, Month.MAY, 7, 14, 0))
                .build();

        subject.save(event);

        Optional<Event> found = subject.findById(event.getId());

        assertThat(found).isNotEmpty();
        Event actual = found.orElse(new Event());
        assertThat(actual.getTitle()).isEqualTo("title");
        assertThat(actual.getDescription()).isEqualTo("description");
        assertThat(actual.getTimetable()).isEqualTo(timetable);
        assertThat(actual.getUsePeriod()).isTrue();
        assertThat(actual.getPeriodDays()).isEqualTo(1);
        assertThat(actual.getPeriodWeeks()).isEqualTo(0);
        assertThat(actual.getStartDate()).isEqualTo(LocalDateTime.of(2018, Month.MAY, 7, 13, 20));
        assertThat(actual.getEndDate()).isEqualTo(LocalDateTime.of(2018, Month.MAY, 7, 14, 0));
        assertThat(actual.getLocation()).isEqualTo("location");
    }
}