package com.gmail.at.sichyuriyy.subscription.controller;

import com.gmail.at.sichyuriyy.subscription.domain.Subscription;
import com.gmail.at.sichyuriyy.subscription.dto.SubscriptionDto;
import com.gmail.at.sichyuriyy.subscription.dto.SubscriptionTransformer;
import com.gmail.at.sichyuriyy.subscription.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionTransformer subscriptionTransformer;

    private final SubscriptionService subscriptionService;

    @PostMapping("/timetables/public/{timetableId}/subscription}")
    public SubscriptionDto createSubscriptionOnPublicTimetable(@PathVariable Long timetableId) {
        Subscription subscription = subscriptionService.createPublicSubscription(timetableId);
        return subscriptionTransformer.toDto(subscription);
    }

    @GetMapping("/timetables/{timetableId}/subscription/own")
    public SubscriptionDto geOwnSubscriptionByTimetableId(@PathVariable Long timetableId) {
        return subscriptionService.getOwnSubscriptionByTimetableId(timetableId)
                .map(subscriptionTransformer::toDto)
                .orElse(null);
    }
}
