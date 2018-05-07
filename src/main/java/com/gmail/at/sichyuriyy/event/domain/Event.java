package com.gmail.at.sichyuriyy.event.domain;

import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "event")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Timetable timetable;
    private String title;
    private String location;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean usePeriod;
    private Integer periodDays;
    private Integer periodWeeks;
}
