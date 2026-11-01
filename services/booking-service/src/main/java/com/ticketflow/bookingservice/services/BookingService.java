package com.ticketflow.bookingservice.services;

import com.ticketflow.bookingservice.clients.EventClient;
import com.ticketflow.bookingservice.dto.BookingRequest;
import com.ticketflow.bookingservice.dto.EventResponse;
import feign.FeignException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService {

    private final EventClient eventClient;

    public BookingService(EventClient eventClient) {
        this.eventClient = eventClient;
    }

    public EventResponse checkEvent(Long eventId) {
        try {
            return eventClient.getById(eventId);

        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Event with ID " + eventId + " not found"
            );

        } catch (RetryableException e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Event Service is currently unavailable",
                    e
            );

        } catch (FeignException e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Unable to communicate with Event Service",
                    e
            );
        }
    }

    public String createBooking(BookingRequest request) {

        if (request.eventId() == null ||
                request.quantity() == null ||
                request.quantity() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A valid event ID and positive quantity are required"
            );
        }

        EventResponse event = checkEvent(request.eventId());

        return "Booking accepted for "
                + request.quantity()
                + " ticket(s) to "
                + event.title();
    }

}
