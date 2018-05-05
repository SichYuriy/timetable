package com.gmail.at.sichyuriyy.timetable.service;

import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import org.springframework.data.domain.Page;

public interface TimetableService {
    Timetable create(Timetable timetable);
    Page<Timetable> getOwnNotActiveTimetables(int pageNum);
}
