package com.gmail.at.sichyuriyy.event.dto;

import com.gmail.at.sichyuriyy.common.dto.FromDtoTransformer;
import com.gmail.at.sichyuriyy.event.domain.Event;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import org.springframework.stereotype.Component;

@Component
public class EventTransformer implements FromDtoTransformer<Event, EventDto> {
    @Override
    public Event fromDto(EventDto dto) {
        if (dto == null) {
            return null;
        }
        return Event.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .usePeriod(dto.getUsePeriod())
                .periodDays(dto.getPeriodDays())
                .periodWeeks(dto.getPeriodWeeks())
                .timetable(Timetable.builder().id(dto.getTimetableId()).build())
                .build();

    }
}
