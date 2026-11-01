package com.ticketflow.bookingservice.controllers;

import com.ticketflow.bookingservice.dto.EventResponse;
import com.ticketflow.bookingservice.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ticketflow.bookingservice.dto.BookingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/check-event/{eventId}")
    public ResponseEntity<EventResponse> checkEvent(
            @PathVariable Long eventId) {

        EventResponse event = bookingService.checkEvent(eventId);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<String> createBooking(
            @RequestBody BookingRequest request) {

        String result = bookingService.createBooking(request);

        return ResponseEntity.ok(result);
    }

}
