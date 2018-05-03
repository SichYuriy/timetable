package com.gmail.at.sichyuriyy.timetable.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTimetableDto {
    private String title;
    private String description;
    private Boolean isPrivate;
    private Boolean usePeriod;
    private Integer periodWeeks;
    private Integer periodDays;
}
