package com.folau.uuid;

import com.folau.uuid.dao.UID;
import com.folau.uuid.dao.UIDDAO;
import com.folau.uuid.service.UUIDService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
class UuidTests {

    @Autowired
    private UUIDService uuidService;

    @Autowired
    private UIDDAO uidDAO;

    @Transactional
    @Test
    void itShouldNotGetUuidsByDate() {

        LocalDateTime thursday = LocalDateTime.of(2024, 5, 30, 4, 0, 0);

        String uuid = uuidService.generateUUID(thursday);
        uuid = uuidService.generateUUID(thursday);

        LocalDateTime friday = LocalDateTime.of(2024, 5, 31, 4, 0, 0);

        int pageNumber = 0;
        Pageable pageable = PageRequest.of(pageNumber, 25);

        Page<UID> fridayUuids = uidDAO.getByDate(friday.toLocalDate(), pageable);

        assertThat(fridayUuids).isNotNull();
        assertThat(fridayUuids.getTotalElements()).isEqualTo(0);

    }

    @Transactional
    @Test
    void itShouldGetUuidsByDate() {

        LocalDateTime friday = LocalDateTime.of(2024, 5, 31, 4, 0, 0);

        String uuid = uuidService.generateUUID(friday);
        uuid = uuidService.generateUUID(friday);

        int pageNumber = 0;
        Pageable pageable = PageRequest.of(pageNumber, 25);

        Page<UID> fridayUuids = uidDAO.getByDate(friday.toLocalDate(), pageable);

        assertThat(fridayUuids).isNotNull();
        assertThat(fridayUuids.getTotalElements()).isEqualTo(2);

    }

}
