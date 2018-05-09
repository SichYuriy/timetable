package com.gmail.at.sichyuriyy.timetable.service;

import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.user.domain.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TimetableService {
    Optional<Timetable> getById(Long id);
    Timetable create(Timetable timetable);
    Page<Timetable> getOwnNotActiveTimetables(int pageNum);
    Optional<Timetable> getSecuredTimetableById(Long id);
    Boolean hasViewAccess(User user, Timetable timetable);
}
