    package com.apica.journalservice.controller;

    import com.apica.journalservice.entity.JournalEvent;
    import com.apica.journalservice.service.JournalService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/journals")
    @RequiredArgsConstructor
    public class JournalController {

        @Autowired
        private JournalService journalService;

        @GetMapping
        //@PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<List<JournalEvent>> getAllJournalEvents() {
            return ResponseEntity.ok(journalService.getAllEvents());
        }
    }

