package com.gmail.at.sichyuriyy.event.domain.actualevent;

import com.gmail.at.sichyuriyy.event.domain.ActualEvent;
import com.gmail.at.sichyuriyy.event.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface ActualEventBuilder {
    ActualEvent fromNonPeriodicalEvent(Event event);
    List<ActualEvent> fromPeriodicalEvent(Event event, LocalDateTime startDate, LocalDateTime endDate);
}
