package com.gmail.at.sichyuriyy.timetable.service;

import com.gmail.at.sichyuriyy.security.exception.ThereIsNoUserLoggedIn;
import com.gmail.at.sichyuriyy.security.service.SecurityService;
import com.gmail.at.sichyuriyy.timetable.domain.Timetable;
import com.gmail.at.sichyuriyy.timetable.repository.TimetableRepository;
import com.gmail.at.sichyuriyy.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private static final int TIMETABLE_PAGE_SIZE = 5;

    private final TimetableRepository timetableRepository;
    private final SecurityService securityService;

    @Override
    public Optional<Timetable> getById(Long id) {
        return timetableRepository.findById(id);
    }

    @Override
    public Optional<Timetable> getPublicTimetableById(Long id) {
        return getById(id).filter(t -> !t.getIsPrivate());
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

    @Override
    public Optional<Timetable> getSecuredTimetableById(Long id) {
        Optional<Timetable> publicTimetable = timetableRepository.findByIdAndIsPrivateFalseAndDeletedFalse(id);
        if (publicTimetable.isPresent()) {
            return publicTimetable;
        }
        return securityService.findLoggedInUser()
                .flatMap(u -> timetableRepository
                        .findPublicOrOwnTimetableById(id, u.getId()));
    }

    @Override
    public Boolean hasViewAccess(User user, Timetable timetable) {
        return !timetable.getIsPrivate()
                || user.getId().equals(timetable.getOwner().getId());
    }
}
