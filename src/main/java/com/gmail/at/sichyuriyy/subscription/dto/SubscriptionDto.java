package com.gmail.at.sichyuriyy.subscription.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubscriptionDto {
    private Long id;
    private Long subscriberId;
    private Long timetableId;
    private Boolean muted;
    private Boolean approved;
    private Boolean banned;
}
