package com.gmail.at.sichyuriyy.timetable.repository;

import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Page<Timetable> findAllByActiveFalseAndOwnerAndDeletedFalseOrderByIdDesc(User owner, Pageable pageable);
}
