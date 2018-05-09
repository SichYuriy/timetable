package com.gmail.at.sichyuriyy.event.repository;

import com.gmail.at.sichyuriyy.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select e from Event e " +
            "where e.timetable.id = :timetableId " +
            "and ((e.usePeriod = true and e.startDate < :endDate) or e.startDate between :startDate and :endDate)")
    List<Event> getTimetableEventsBetween(@Param("timetableId") Long timetableId,
                                          @Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate);
}
