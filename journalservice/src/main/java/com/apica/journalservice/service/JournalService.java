package com.apica.journalservice.service;

import com.apica.journalservice.entity.JournalEvent;
import com.apica.journalservice.repository.JournalEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalService {

    @Autowired
    private JournalEventRepository repository;

    public List<JournalEvent> getAllEvents() {
        return repository.findAll();
    }
}

