package com.gmail.at.sichyuriyy.event.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto {
    private Long id;
    private Long timetableId;
    private String title;
    private String location;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean usePeriod;
    private Integer periodDays;
    private Integer periodWeeks;
}
