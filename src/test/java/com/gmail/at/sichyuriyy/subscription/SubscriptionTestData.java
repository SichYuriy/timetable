package com.gmail.at.sichyuriyy.subscription;

import com.gmail.at.sichyuriyy.subscription.domain.Subscription;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.user.domain.User;

public class SubscriptionTestData {
    public static Subscription getTestSubscription(User subscriber, Timetable timetable) {
        return Subscription.builder()
                .approved(null)
                .banned(false)
                .muted(false)
                .subscriber(subscriber)
                .timetable(timetable)
                .build();
    }
}
