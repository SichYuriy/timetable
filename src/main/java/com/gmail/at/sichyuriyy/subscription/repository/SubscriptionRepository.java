package com.gmail.at.sichyuriyy.subscription.repository;

import com.gmail.at.sichyuriyy.subscription.domain.Subscription;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("select s from Subscription s " +
            "where s.timetable.deleted = false and s.subscriber = :subscriber " +
            "order by s.timetable.active desc")
    Page<Subscription> findAllBySubscriber(@Param("subscriber") User subscriber,
                                           Pageable pageable);

    Optional<Subscription> findByTimetableAndSubscriber(Timetable timetable,
                                                        User subscriber);

//    @Query("select t from Subscription s join s.timetable t " +
//            "where s.subscriber = :subscriber " +
//            "and s.timetable.id = t.id " +
//            "and t.active = true and t.deleted = false " +
//            "and s.banned = false and s.approved = true " +
//            "order by id desc")
//    List<Timetable> findOwnActiveSubscribedTimetables(@Param("subscriber") User owner);
}
