package com.gmail.at.sichyuriyy.timetable.service;

import com.gmail.at.sichyuriyy.security.service.SecurityService;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.repository.TimetableRepository;
import com.gmail.at.sichyuriyy.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TimetableServiceImplTest {

    @Mock
    private SecurityService securityService;
    @Mock
    private TimetableRepository timetableRepository;

    private TimetableService subject;

    private ArgumentCaptor<Timetable> captor = ArgumentCaptor.forClass(Timetable.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subject = new TimetableServiceImpl(timetableRepository, securityService);
    }

    @Test
    public void create_shouldSetUpTimetableAndCallRepo() {
        Timetable timetable = new Timetable();
        User owner  = User.builder().id(1L).build();
        when(securityService.findLoggedInUser()).thenReturn(Optional.of(owner));

        subject.create(timetable);

        verify(securityService).findLoggedInUser();
        verify(timetableRepository).save(captor.capture());
        Timetable saved = captor.getValue();

        assertThat(saved.getDeleted()).isFalse();
        assertThat(saved.getOwner().getId()).isEqualTo(1L);
        assertThat(saved.getActivatedBefore()).isFalse();
        assertThat(saved.getActive()).isFalse();
        assertThat(saved.getSubscribersCount()).isEqualTo(0);
    }
}