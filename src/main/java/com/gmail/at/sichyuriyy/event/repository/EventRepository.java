package com.gmail.at.sichyuriyy.event.repository;

import com.gmail.at.sichyuriyy.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
