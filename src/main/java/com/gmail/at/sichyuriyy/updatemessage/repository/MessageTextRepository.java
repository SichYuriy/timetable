package com.gmail.at.sichyuriyy.updatemessage.repository;

import com.gmail.at.sichyuriyy.updatemessage.domain.MessageText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTextRepository extends JpaRepository<MessageText, Long> {
}
