package com.gmail.at.sichyuriyy.event.controller;

import com.gmail.at.sichyuriyy.event.domain.Event;
import com.gmail.at.sichyuriyy.event.dto.EventDto;
import com.gmail.at.sichyuriyy.event.dto.EventTransformer;
import com.gmail.at.sichyuriyy.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final EventTransformer eventTransformer;
    private final EventService eventService;

    @Autowired
    public EventController(EventTransformer eventTransformer, EventService eventService) {
        this.eventTransformer = eventTransformer;
        this.eventService = eventService;
    }

    @PostMapping("/events")
    public void createEvent(@RequestBody EventDto eventDto) {
        Event event = eventTransformer.fromDto(eventDto);
        eventService.create(event);
    }
}
