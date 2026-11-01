package com.ticketflow.eventservice.controllers;

import com.ticketflow.eventservice.dto.EventResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(
            @PathVariable Long id) {

        if (id.equals(1L)) {
            EventResponse event = new EventResponse(
                    1L,
                    "Tech Conference 2026",
                    "A conference about technology"
            );

            return ResponseEntity.ok(event);
        }

        return ResponseEntity.notFound().build();
    }
}
