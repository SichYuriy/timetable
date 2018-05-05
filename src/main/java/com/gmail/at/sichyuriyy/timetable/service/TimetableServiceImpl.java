package com.gmail.at.sichyuriyy.timetable.service;

import com.gmail.at.sichyuriyy.security.service.SecurityService;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.exception.ThereIsNoUserLoggedIn;
import com.gmail.at.sichyuriyy.timetable.repository.TimetableRepository;
import com.gmail.at.sichyuriyy.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TimetableServiceImpl implements TimetableService {

    private static final int TIMETABLE_PAGE_SIZE = 5;

    private final TimetableRepository timetableRepository;
    private final SecurityService securityService;

    @Autowired
    public TimetableServiceImpl(TimetableRepository timetableRepository, SecurityService securityService) {
        this.timetableRepository = timetableRepository;
        this.securityService = securityService;
    }

    @Override
    public Timetable create(Timetable timetable) {
        User owner = securityService.findLoggedInUser().orElseThrow(ThereIsNoUserLoggedIn::new);
        timetable.setActivatedBefore(false);
        timetable.setActive(false);
        timetable.setSubscribersCount(0);
        timetable.setOwner(owner);
        timetable.setDeleted(false);
        return timetableRepository.save(timetable);
    }

    @Override
    public Page<Timetable> getOwnNotActiveTimetables(int pageNum) {
        User owner = securityService.findLoggedInUser().orElseThrow(ThereIsNoUserLoggedIn::new);
        PageRequest pageRequest = PageRequest.of(pageNum, TIMETABLE_PAGE_SIZE);
        return timetableRepository.findAllByActiveFalseAndOwnerAndDeletedFalseOrderByIdDesc(owner, pageRequest);
    }
}
