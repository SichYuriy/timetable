package com.gmail.at.sichyuriyy.event.domain.actualevent;

import com.gmail.at.sichyuriyy.event.domain.ActualEvent;
import com.gmail.at.sichyuriyy.event.domain.Event;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActualEventBuilderIml implements ActualEventBuilder {
    @Override
    public ActualEvent fromNonPeriodicalEvent(Event event) {
        return ActualEvent.builder()
                .usePeriod(false)
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .title(event.getTitle())
                .location(event.getLocation())
                .description(event.getDescription())
                .timetableId(event.getTimetable().getId())
                .eventId(event.getTimetable().getId())
                .build();
    }

    @Override
    public List<ActualEvent> fromPeriodicalEvent(Event event, LocalDateTime startDate, LocalDateTime endDate) {
        List<ActualEvent> result = new ArrayList<>();

        LocalDateTime firstStart = event.getStartDate();
        Duration period = Duration.ofDays(event.getPeriodDays() + 7 * event.getPeriodWeeks());

        LocalDateTime actualEventStartTime = firstStart;
        if (firstStart.compareTo(startDate) < 0) {
            Duration timeToIntervalBegin = Duration.between(firstStart, startDate);
            long periodsToIntervalBegin = timeToIntervalBegin.getSeconds() / period.getSeconds();
            actualEventStartTime = firstStart.plus(period.multipliedBy(periodsToIntervalBegin));
        }

        while (actualEventStartTime.compareTo(endDate) <= 0) {
            if (actualEventStartTime.compareTo(startDate) >= 0) {
                result.add(fromPeriodicalEvent(event, actualEventStartTime));
            }
            actualEventStartTime = actualEventStartTime.plus(period);
        }
        return result;
    }

    private ActualEvent fromPeriodicalEvent(Event event, LocalDateTime actualStartTime) {
        Duration eventDuration = Duration.between(event.getStartDate(), event.getEndDate());
        LocalDateTime actualEndTime = actualStartTime.plus(eventDuration);
        return ActualEvent.builder()
                .usePeriod(true)
                .startDate(actualStartTime)
                .endDate(actualEndTime)
                .title(event.getTitle())
                .location(event.getLocation())
                .description(event.getDescription())
                .timetableId(event.getTimetable().getId())
                .eventId(event.getId())
                .build();
    }
}
