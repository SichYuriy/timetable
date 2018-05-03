package com.gmail.at.sichyuriyy.timetable.repository;

import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
