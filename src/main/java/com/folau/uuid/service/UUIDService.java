package com.folau.uuid.service;

import com.folau.uuid.dao.UID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface UUIDService {
    String generateUUID();

    String generateUUID(LocalDateTime dateTime);

    Page<UID> getByDate(LocalDate date, Pageable pageable);
}
