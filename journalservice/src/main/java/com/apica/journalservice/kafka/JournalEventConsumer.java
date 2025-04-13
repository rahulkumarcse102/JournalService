package com.apica.journalservice.kafka;

import com.apica.journalservice.entity.JournalEvent;
import com.apica.journalservice.repository.JournalEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JournalEventConsumer {

    @Autowired
    private JournalEventRepository repository;

    @KafkaListener(topics = "user-events", groupId = "journal-group")
    public void consume(String message) {
        JournalEvent event = new JournalEvent();
        event.setMessage(message);
        event.setTimestamp(LocalDateTime.now());
        repository.save(event);
    }
}
