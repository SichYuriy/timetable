package com.gmail.at.sichyuriyy.subscription;

import com.gmail.at.sichyuriyy.subscription.domain.Subscription;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.user.domain.User;

public class SubscriptionTestData {
    public static Subscription getNotApprovedSubscription(User subscriber, Timetable timetable) {
        return Subscription.builder()
                .approved(null)
                .banned(false)
                .muted(false)
                .subscriber(subscriber)
                .timetable(timetable)
                .build();
    }

    public static Subscription getApprovedSubscription(User subscriber, Timetable timetable) {
        return Subscription.builder()
                .approved(true)
                .banned(false)
                .muted(false)
                .subscriber(subscriber)
                .timetable(timetable)
                .build();
    }

    public static Subscription getRejectedSubscription(User subscriber, Timetable timetable) {
        return Subscription.builder()
                .approved(false)
                .banned(false)
                .muted(false)
                .subscriber(subscriber)
                .timetable(timetable)
                .build();
    }

    public static Subscription getBannedSubscription(User subscriber, Timetable timetable) {
        return Subscription.builder()
                .approved(true)
                .banned(true)
                .muted(false)
                .subscriber(subscriber)
                .timetable(timetable)
                .build();
    }
}
