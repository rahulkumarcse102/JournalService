package com.apica.journalservice;

import com.apica.journalservice.entity.JournalEvent;
import com.apica.journalservice.repository.JournalEventRepository;
import com.apica.journalservice.service.JournalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JournalServiceTest {

    @Mock
    private JournalEventRepository repository;

    @InjectMocks
    private JournalService service;

    @Test
    void shouldReturnAllEvents() {
        List<JournalEvent> mockEvents = List.of(
                new JournalEvent()
        );

        when(repository.findAll()).thenReturn(mockEvents);

        List<JournalEvent> result = service.getAllEvents();

        assertEquals(1, result.size());
        assertEquals("User registered: john", result.get(0).getMessage());
        verify(repository, times(1)).findAll();
    }
}
