package com.gmail.at.sichyuriyy.event.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ActualEvent {
    private Boolean usePeriod;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private String location;
    private String description;
    private Long timetableId;
    private Long eventId;
}
