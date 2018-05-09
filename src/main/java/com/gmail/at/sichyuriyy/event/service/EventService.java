package com.gmail.at.sichyuriyy.event.service;

import com.gmail.at.sichyuriyy.event.domain.ActualEvent;
import com.gmail.at.sichyuriyy.event.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    void create(Event event, Long timetableId);
    List<ActualEvent> findActualEventsBetween(Long timetableId, LocalDateTime startDate, LocalDateTime endTime);
}
