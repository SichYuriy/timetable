package com.gmail.at.sichyuriyy.updatemessage.repository;

import com.gmail.at.sichyuriyy.updatemessage.domain.UpdateMessage;
import com.gmail.at.sichyuriyy.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdateMessageRepository extends JpaRepository<UpdateMessage, Long> {
    Page<UpdateMessage> findAllByUserOrderByDateDesc(User user, Pageable pageable);
}
