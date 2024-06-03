package com.folau.uuid.service;

import java.time.LocalDateTime;

public interface UUIDService {
    String generateUUID();

    String generateUUID(LocalDateTime dateTime);
}
