package com.folau.uuid.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UIDRepository extends JpaRepository<UID, Long> {

    @Query(value = "SELECT u.* FROM uid as u WHERE CAST(u.created_at AS DATE) = :date", nativeQuery = true)
    Page<UID> findByDate(@Param("date") LocalDate date, Pageable pageable);
}
