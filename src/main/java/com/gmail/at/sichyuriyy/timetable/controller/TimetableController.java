package com.gmail.at.sichyuriyy.timetable.controller;

import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.dto.CreateTimetableDto;
import com.gmail.at.sichyuriyy.timetable.dto.CreateTimetableTransformer;
import com.gmail.at.sichyuriyy.timetable.dto.TimetableDto;
import com.gmail.at.sichyuriyy.timetable.dto.TimetableTransformer;
import com.gmail.at.sichyuriyy.timetable.service.TimetableService;
import com.gmail.at.sichyuriyy.timetable.validation.CreateTimetableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
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

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new CreateTimetableValidator());
    }

    @PostMapping("/timetables")
    public TimetableDto createTimetable(@Valid @RequestBody CreateTimetableDto timetableDto) {
        Timetable timetable = createTimetableTransformer.fromDto(timetableDto);
        return timetableTransformer.toDto(timetableService.create(timetable));
    }

    @GetMapping("/timetables/ownAndNotActive")
    public Page<TimetableDto> getOwnNotActiveTimetables(@RequestParam int pageNum) {
        Page<Timetable> timetables = timetableService.getOwnNotActiveTimetables(pageNum);
        return timetables.map(timetableTransformer::toDto);
    }

    @GetMapping("/timetables/{id}")
    public TimetableDto getById(@PathVariable Long id) {
        return timetableService.getSecuredTimetableById(id)
                .map(timetableTransformer::toDto)
                .orElse(null);
    }
}
