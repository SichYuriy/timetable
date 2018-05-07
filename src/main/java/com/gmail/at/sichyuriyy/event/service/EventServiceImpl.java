package com.gmail.at.sichyuriyy.event.service;

import com.gmail.at.sichyuriyy.event.domain.Event;
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

@Service
public class EventServiceImpl implements EventService {

    private final SecurityService securityService;
    private final TimetableService timetableService;
    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(SecurityService securityService,
                            TimetableService timetableService,
                            EventRepository eventRepository) {
        this.securityService = securityService;
        this.timetableService = timetableService;
        this.eventRepository = eventRepository;
    }

    @Override
    public void create(Event event) {
        if (currentUserIsOwnerOfTimetable(event.getTimetable().getId())) {
            eventRepository.save(event);
        }
        throw new AccessDeniedException("Only owner can update timetable");
    }

    private boolean currentUserIsOwnerOfTimetable(Long timetableId) {
        User currentUser = securityService.findLoggedInUser().orElseThrow(ThereIsNoUserLoggedIn::new);
        Timetable timetable = timetableService.getSecuredTimetableById(timetableId)
                .orElseThrow(NoSuchTimetableException::new);
        return currentUser.getId().equals(timetable.getOwner().getId());
    }
}
