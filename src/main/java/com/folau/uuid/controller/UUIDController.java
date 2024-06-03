package com.folau.uuid.controller;


import com.folau.uuid.dao.UID;
import com.folau.uuid.service.UUIDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/uuids")
@Slf4j
public class UUIDController {

    @Autowired
    private UUIDService uuidService;

    @GetMapping(value = "/by-date")
    public ResponseEntity<Page<UID>> getByDate(@RequestParam(name = "date", required = true) LocalDate date,
                                               @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
                                               @RequestParam(name = "size", required = false, defaultValue = "25") int pageSize) {

        Page<UID> result = uuidService.getByDate(date, PageRequest.of(pageNumber, pageSize));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
