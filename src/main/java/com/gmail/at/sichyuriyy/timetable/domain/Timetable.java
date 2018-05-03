package com.gmail.at.sichyuriyy.timetable.domain;

import com.gmail.at.sichyuriyy.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "timetable")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "private")
    private Boolean isPrivate;
    private Boolean active;
    private Integer subscribersCount;
    private Boolean usePeriod;
    private Integer periodWeeks;
    private Integer periodDays;
    private Boolean activatedBefore;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
    private Boolean deleted;
}
