package com.gmail.at.sichyuriyy.updatemessage.repository;

import com.gmail.at.sichyuriyy.app.Application;
import com.gmail.at.sichyuriyy.timetable.TimetableTestData;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.repository.TimetableRepository;
import com.gmail.at.sichyuriyy.updatemessage.domain.MessageText;
import com.gmail.at.sichyuriyy.updatemessage.domain.UpdateMessage;
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

import static com.gmail.at.sichyuriyy.TestData.TEST_DATE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
@Rollback
public class UpdateMessageRepositoryTest {

    @Autowired
    private UpdateMessageRepository subject;
    @Autowired
    private MessageTextRepository messageTextRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TimetableRepository timetableRepository;

    @Test
    public void findAllByUserOrderByDateDesc_shouldMatchByUserAndOrderByDate() {
        User timetableOwner = User.builder().username("owner").password("0").build();
        User user1 = User.builder().username("user1").password("1").build();
        User user2 = User.builder().username("user2").password("2").build();
        Timetable timetable = TimetableTestData.getTestTimetable(timetableOwner);
        MessageText messageText = MessageText.builder().text("some text").build();
        UpdateMessage.UpdateMessageBuilder messageBuilder = UpdateMessage.builder()
                .text(messageText)
                .timetable(timetable);
        UpdateMessage updateMessage1 = messageBuilder.date(TEST_DATE).user(user1).build();
        UpdateMessage updateMessage2 = messageBuilder.date(TEST_DATE.plusDays(1)).user(user1).build();
        UpdateMessage updateMessage3 = messageBuilder.date(TEST_DATE).user(user2).build();

        userRepository.saveAll(Arrays.asList(timetableOwner, user1, user2));
        timetableRepository.save(timetable);
        messageTextRepository.save(messageText);
        subject.saveAll(Arrays.asList(updateMessage1, updateMessage2, updateMessage3));

        Page<UpdateMessage> actual =
                subject.findAllByUserOrderByDateDesc(user1, PageRequest.of(0, 5));

        assertThat(actual).containsExactly(updateMessage2, updateMessage1);
        assertThat(actual.getContent().get(0).getText().getStringValue()).isEqualTo("some text");
    }
}