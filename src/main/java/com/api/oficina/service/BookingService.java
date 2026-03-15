package com.api.oficina.service;

import com.api.oficina.model.Booking;
import java.util.List;

public interface BookingService {
    Booking save(Booking booking, Long client_id);
    List<Booking> findAll();
    Booking findById(Long id);
    Booking payBooking(Long id);
    Booking cancelBooking(Long id);
}