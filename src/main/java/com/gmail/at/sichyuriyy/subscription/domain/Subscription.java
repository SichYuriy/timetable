package com.gmail.at.sichyuriyy.subscription.domain;

import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "subscription")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User subscriber;
    @ManyToOne(fetch = FetchType.LAZY)
    private Timetable timetable;
    private Boolean muted = false;
    private Boolean approved;
    private Boolean banned = false;
}
