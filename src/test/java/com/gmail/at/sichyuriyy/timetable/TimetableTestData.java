package com.gmail.at.sichyuriyy.timetable;

import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.user.domain.User;

public class TimetableTestData {

    public static Timetable getTestTimetable(User owner) {
        return Timetable.builder()
                .title("title")
                .description("description")
                .isPrivate(false)
                .activatedBefore(false)
                .active(false)
                .usePeriod(true)
                .periodDays(1)
                .periodWeeks(0)
                .subscribersCount(0)
                .owner(owner)
                .deleted(false)
                .build();
    }
}
