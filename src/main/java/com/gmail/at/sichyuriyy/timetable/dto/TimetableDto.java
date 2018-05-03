package com.gmail.at.sichyuriyy.timetable.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimetableDto {
    private Long id;
    private String title;
    private String description;
    private Boolean isPrivate;
    private Boolean active;
    private Integer subscribersCount;
    private Boolean usePeriod;
    private Integer periodWeeks;
    private Integer periodDays;
    private Boolean activatedBefore;
    private Long ownerId;
    private Boolean deleted;
}
