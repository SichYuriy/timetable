package com.gmail.at.sichyuriyy.subscription.dto;

import com.gmail.at.sichyuriyy.common.dto.ToDtoTransformer;
import com.gmail.at.sichyuriyy.subscription.domain.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionTransformer implements ToDtoTransformer<Subscription, SubscriptionDto> {
    @Override
    public SubscriptionDto toDto(Subscription subscription) {
        if (subscription == null) {
            return null;
        }
        return SubscriptionDto.builder()
                .id(subscription.getId())
                .timetableId(subscription.getTimetable().getId())
                .subscriberId(subscription.getSubscriber().getId())
                .approved(subscription.getApproved())
                .muted(subscription.getMuted())
                .banned(subscription.getBanned())
                .build();
    }
}
