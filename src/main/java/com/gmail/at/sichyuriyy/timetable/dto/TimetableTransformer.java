package com.gmail.at.sichyuriyy.timetable.dto;

import com.gmail.at.sichyuriyy.common.dto.ToDtoTransformer;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import org.springframework.stereotype.Component;

@Component
public class TimetableTransformer implements ToDtoTransformer<Timetable, TimetableDto> {
    @Override
    public TimetableDto toDto(Timetable timetable) {
        if (timetable == null) {
            return null;
        }
        return TimetableDto.builder()
                .id(timetable.getId())
                .title(timetable.getTitle())
                .description(timetable.getDescription())
                .isPrivate(timetable.getIsPrivate())
                .active(timetable.getActive())
                .subscribersCount(timetable.getSubscribersCount())
                .usePeriod(timetable.getUsePeriod())
                .periodWeeks(timetable.getPeriodWeeks())
                .periodDays(timetable.getPeriodDays())
                .ownerId(timetable.getOwner().getId())
                .deleted(timetable.getDeleted())
                .build();
    }
}
