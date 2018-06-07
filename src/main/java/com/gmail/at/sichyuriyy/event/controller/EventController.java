package com.gmail.at.sichyuriyy.event.controller;

import com.gmail.at.sichyuriyy.event.domain.ActualEvent;
import com.gmail.at.sichyuriyy.event.domain.Event;
import com.gmail.at.sichyuriyy.event.dto.EventDto;
import com.gmail.at.sichyuriyy.event.dto.EventTransformer;
import com.gmail.at.sichyuriyy.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class EventController {

    private final EventTransformer eventTransformer;
    private final EventService eventService;

    @Autowired
    public EventController(EventTransformer eventTransformer, EventService eventService) {
        this.eventTransformer = eventTransformer;
        this.eventService = eventService;
    }

    @PostMapping("/timetables/{timetableId}/events")
    public void createEvent(@RequestBody EventDto eventDto, @PathVariable Long timetableId) {
        Event event = eventTransformer.fromDto(eventDto);
        eventService.create(event, timetableId);
    }

    @GetMapping("/timetables/{timetableId}/events")
    public List<ActualEvent> getActualEventsForPeriod(@PathVariable Long timetableId,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return eventService.findActualEventsBetween(timetableId, startDate, endDate);
    }
}
