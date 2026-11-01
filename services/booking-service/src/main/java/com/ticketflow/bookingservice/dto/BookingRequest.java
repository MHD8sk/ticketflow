package com.ticketflow.bookingservice.dto;

public record BookingRequest(
        Long eventId,
        Integer quantity
) {
}
