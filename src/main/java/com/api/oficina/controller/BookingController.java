package com.api.oficina.controller;

import com.api.oficina.model.Booking;
import com.api.oficina.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oficina/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/{cliente_id}")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, @PathVariable Long cliente_id) {
        return ResponseEntity.ok(bookingService.save(booking, cliente_id));
    }

    @PostMapping("/pay/{id}")
    public ResponseEntity<Map<String,String>> payBooking(@PathVariable Long id) {
        bookingService.payBooking(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Booking has been paid successfully.");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Map<String, String>> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Booking has been cancelled successfully.");

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        Booking booking = bookingService.findById(id);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(booking);
    }
}