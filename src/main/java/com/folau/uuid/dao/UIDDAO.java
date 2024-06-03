package com.folau.uuid.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface UIDDAO {
    UID save(UID uid);

    Page<UID> getByDate(LocalDate date, Pageable pageable);
}
