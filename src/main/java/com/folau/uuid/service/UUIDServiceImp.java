package com.folau.uuid.service;

import com.folau.uuid.dao.UID;
import com.folau.uuid.dao.UIDDAO;
import com.folau.uuid.dao.UIDRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class UUIDServiceImp implements UUIDService {

    @Autowired
    private UIDDAO uidDAO;

    private final long nodeId = getNodeId();
    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public synchronized String generateUUID() {
        return generateUUID(LocalDateTime.now());
    }

    @Override
    public String generateUUID(LocalDateTime dateTime) {
        LocalDateTime now = dateTime;
        long timestamp = now.toInstant(ZoneOffset.UTC).toEpochMilli() * 10000;
        long timeLow = timestamp & 0xFFFFFFFFL;
        long timeMid = (timestamp >> 32) & 0xFFFFL;
        long timeHiAndVersion = ((timestamp >> 48) & 0x0FFFL) | 0x1000L;

        long clockSeq = secureRandom.nextInt(1 << 14);
        long clockSeqHiAndReserved = (clockSeq & 0x3FFFL) | 0x8000L;

        String uuid = String.format("%08x-%04x-%04x-%02x%02x-%012x",
                timeLow, timeMid, timeHiAndVersion,
                clockSeqHiAndReserved, clockSeq & 0xFF, nodeId);

        uidDAO.save(new UID(null, uuid, now));

        return uuid;
    }

    @Override
    public Page<UID> getByDate(LocalDate date, Pageable pageable) {
        return uidDAO.getByDate(date, pageable);
    }

    private long getNodeId() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (networkInterface.getHardwareAddress() != null) {
                    ByteBuffer buffer = ByteBuffer.wrap(networkInterface.getHardwareAddress());
                    long node = 0;
                    for (int i = 0; i < 6; i++) {
                        node = (node << 8) | (buffer.get() & 0xFF);
                    }
                    return node;
                }
            }
        } catch (Exception e) {
            // Use a random node ID if there is an issue getting the network interface
        }
        return secureRandom.nextLong();
    }

}
