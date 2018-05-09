package com.gmail.at.sichyuriyy.event.service;

import com.gmail.at.sichyuriyy.event.domain.ActualEvent;
import com.gmail.at.sichyuriyy.event.domain.Event;
import com.gmail.at.sichyuriyy.event.domain.actualevent.ActualEventBuilder;
import com.gmail.at.sichyuriyy.event.repository.EventRepository;
import com.gmail.at.sichyuriyy.security.exception.ThereIsNoUserLoggedIn;
import com.gmail.at.sichyuriyy.security.service.SecurityService;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.exception.NoSuchTimetableException;
import com.gmail.at.sichyuriyy.timetable.service.TimetableService;
import com.gmail.at.sichyuriyy.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EventServiceImpl implements EventService {

    private final SecurityService securityService;
    private final TimetableService timetableService;
    private final EventRepository eventRepository;
    private final ActualEventBuilder actualEventBuilder;

    @Autowired
    public EventServiceImpl(SecurityService securityService,
                            TimetableService timetableService,
                            EventRepository eventRepository,
                            ActualEventBuilder actualEventBuilder) {
        this.securityService = securityService;
        this.timetableService = timetableService;
        this.eventRepository = eventRepository;
        this.actualEventBuilder = actualEventBuilder;
    }

    @Override
    public void create(Event event, Long timetableId) {
        if (currentUserIsOwnerOfTimetable(timetableId)) {
            eventRepository.save(event);
            return;
        }
        throw new AccessDeniedException("Only owner can update timetable");
    }

    @Override
    public List<ActualEvent> findActualEventsBetween(
            Long timetableId, LocalDateTime startDate, LocalDateTime endDate) {
        Timetable timetable = timetableService.getById(timetableId).orElseThrow(NoSuchTimetableException::new);
        User currentUser = securityService.findLoggedInUser().orElse(new User());
        if (timetableService.hasViewAccess(currentUser, timetable)) {
            return eventRepository.getTimetableEventsBetween(timetableId, startDate, endDate).stream()
                    .flatMap(e -> extractActualEventsBetween(e, startDate, endDate))
                    .collect(Collectors.toList());
        }
        throw new AccessDeniedException("No access for viewing timetable");
    }

    private Stream<ActualEvent> extractActualEventsBetween(Event event,
                                                           LocalDateTime startDate,
                                                           LocalDateTime endDate) {
        if (!event.getUsePeriod()) {
            return Stream.of(actualEventBuilder.fromNonPeriodicalEvent(event));
        } else {
            return actualEventBuilder.fromPeriodicalEvent(event, startDate, endDate).stream();
        }
    }

    private boolean currentUserIsOwnerOfTimetable(Long timetableId) {
        User currentUser = securityService.findLoggedInUser().orElseThrow(ThereIsNoUserLoggedIn::new);
        Timetable timetable = timetableService.getSecuredTimetableById(timetableId)
                .orElseThrow(NoSuchTimetableException::new);
        return currentUser.getId().equals(timetable.getOwner().getId());
    }
}
