package com.gmail.at.sichyuriyy.subscription.service;

import com.gmail.at.sichyuriyy.subscription.domain.Subscription;

import java.util.Optional;

public interface SubscriptionService {
    void createPublicSubscription(Long timetableId);
    Optional<Subscription> getOwnSubscriptionByTimetableId(Long timetableId);
}
