package com.apica.journalservice.repository;

import com.apica.journalservice.entity.JournalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEventRepository extends JpaRepository<JournalEvent, Long> {
}
