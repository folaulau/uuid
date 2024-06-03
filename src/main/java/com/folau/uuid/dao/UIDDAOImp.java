package com.folau.uuid.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
@Slf4j
public class UIDDAOImp implements UIDDAO{

    @Autowired
    private UIDRepository uidRepository;

    @Override
    public UID save(UID uid) {
        return uidRepository.saveAndFlush(uid);
    }

    @Override
    public Page<UID> getByDate(LocalDate date, Pageable pageable) {
        return uidRepository.findByDate(date, pageable);
    }
}
