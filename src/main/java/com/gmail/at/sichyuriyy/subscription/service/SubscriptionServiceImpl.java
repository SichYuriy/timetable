package com.gmail.at.sichyuriyy.subscription.service;

import com.gmail.at.sichyuriyy.security.exception.ThereIsNoUserLoggedIn;
import com.gmail.at.sichyuriyy.security.service.SecurityService;
import com.gmail.at.sichyuriyy.subscription.domain.Subscription;
import com.gmail.at.sichyuriyy.subscription.repository.SubscriptionRepository;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.exception.NoSuchTimetableException;
import com.gmail.at.sichyuriyy.timetable.service.TimetableService;
import com.gmail.at.sichyuriyy.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final TimetableService timetableService;
    private final SecurityService securityService;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void createPublicSubscription(Long timetableId) {
        Timetable timetable = timetableService.getPublicTimetableById(timetableId)
                .orElseThrow(NoSuchTimetableException::new);
        User user = securityService.findLoggedInUser()
                .orElseThrow(ThereIsNoUserLoggedIn::new);

        Subscription subscription = Subscription.builder()
                .timetable(timetable)
                .approved(true)
                .subscriber(user)
                .build();

        subscriptionRepository.save(subscription);
    }

    @Override
    public Optional<Subscription> getOwnSubscriptionByTimetableId(Long timetableId) {
        Timetable timetable = Timetable.builder().id(timetableId).build();
        return securityService.findLoggedInUser()
                .flatMap(user -> subscriptionRepository.findByTimetableAndSubscriber(
                        timetable, user
                ));
    }
}
