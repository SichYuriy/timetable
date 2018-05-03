package com.gmail.at.sichyuriyy.timetable.dto;

import com.gmail.at.sichyuriyy.common.dto.FromDtoTransformer;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import org.springframework.stereotype.Component;

@Component
public class CreateTimetableTransformer implements FromDtoTransformer<Timetable, CreateTimetableDto> {
    @Override
    public Timetable fromDto(CreateTimetableDto timetableDto) {
        if (timetableDto == null) {
            return null;
        }
        return Timetable.builder()
                .title(timetableDto.getTitle())
                .description(timetableDto.getDescription())
                .isPrivate(timetableDto.getIsPrivate())
                .usePeriod(timetableDto.getUsePeriod())
                .periodDays(timetableDto.getPeriodDays())
                .periodWeeks(timetableDto.getPeriodWeeks())
                .build();
    }
}
