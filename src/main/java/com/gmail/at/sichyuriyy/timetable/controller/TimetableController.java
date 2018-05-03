package com.gmail.at.sichyuriyy.timetable.controller;

import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.dto.CreateTimetableDto;
import com.gmail.at.sichyuriyy.timetable.dto.CreateTimetableTransformer;
import com.gmail.at.sichyuriyy.timetable.dto.TimetableDto;
import com.gmail.at.sichyuriyy.timetable.dto.TimetableTransformer;
import com.gmail.at.sichyuriyy.timetable.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timetables")
public class TimetableController {

    private final TimetableService timetableService;
    private final CreateTimetableTransformer createTimetableTransformer;
    private final TimetableTransformer timetableTransformer;

    @Autowired
    public TimetableController(TimetableService timetableService,
                               CreateTimetableTransformer createTimetableTransformer,
                               TimetableTransformer timetableTransformer) {
        this.timetableService = timetableService;
        this.createTimetableTransformer = createTimetableTransformer;
        this.timetableTransformer = timetableTransformer;
    }

    @PostMapping
    public TimetableDto createTimetable(@RequestBody CreateTimetableDto timetableDto) {
        Timetable timetable = createTimetableTransformer.fromDto(timetableDto);
        return timetableTransformer.toDto(timetableService.create(timetable));
    }
}
