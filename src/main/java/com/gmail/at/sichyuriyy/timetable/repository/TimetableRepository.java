package com.gmail.at.sichyuriyy.timetable.repository;

import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    Page<Timetable> findAllByActiveFalseAndOwnerAndDeletedFalseOrderByIdDesc(User owner, Pageable pageable);

    Page<Timetable> findAllByActiveTrueAndOwnerAndDeletedFalseOrderByIdDesc(User owner, Pageable pageRequest);

    @Query("select t from Timetable t " +
            "where t.id=:id and t.deleted = false " +
            "and (t.isPrivate = false or t.owner.id = :ownerId)")
    Optional<Timetable> findPublicOrOwnTimetableById(@Param("id") Long id, @Param("ownerId") Long ownerId);

    Optional<Timetable> findByIdAndIsPrivateFalseAndDeletedFalse(Long id);

    @Query("select t from Subscription s join s.timetable t " +
            "where s.subscriber = :subscriber " +
            "and t.active = true and t.deleted = false " +
            "and s.banned = false and s.approved = true " +
            "order by s.id desc")
    Page<Timetable> findOwnActiveSubscribedTimetables(@Param("subscriber") User subscriber, Pageable pageable);

    @Query("select t from Subscription s join s.timetable t " +
            "where s.subscriber = :subscriber " +
            "and t.active = false and t.deleted = false " +
            "and s.banned = false and s.approved = true " +
            "order by s.id desc")
    List<Timetable> findOwnNotActiveSubscribedTimetables(@Param("subscriber") User subscriber);

    @Query("select t from Subscription s join s.timetable t " +
            "where s.subscriber = :subscriber " +
            "and t.deleted = false " +
            "and s.banned = false and s.approved = false " +
            "order by s.id desc")
    List<Timetable> findOwnRejectedTimetables(@Param("subscriber") User subscriber);

    @Query("select t from Subscription s join s.timetable t " +
            "where s.subscriber = :subscriber " +
            "and t.deleted = false " +
            "and s.banned = false and s.approved = true " +
            "order by s.id desc")
    List<Timetable> findOwnBannedTimetables(@Param("subscriber") User subscriber);

}
