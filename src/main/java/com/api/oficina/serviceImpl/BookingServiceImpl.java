package com.api.oficina.serviceImpl;

import com.api.oficina.model.Booking;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.UploadedDocument;
import com.api.oficina.repository.BookingRepository;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository BOOKING_REPOSITORY;
    private final ClienteRepository CLIENTE_REPOSITORY;

    public BookingServiceImpl(BookingRepository BOOKING_REPOSITORY, ClienteRepository CLIENTE_REPOSITORY) {
        this.BOOKING_REPOSITORY = BOOKING_REPOSITORY;
        this.CLIENTE_REPOSITORY = CLIENTE_REPOSITORY;
    }

    @Override
    public Booking save(Booking booking, Long client_id) {

        Cliente cliente = CLIENTE_REPOSITORY.findById(client_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        if (booking.getUploadedDocuments() != null) {
            for (UploadedDocument doc : booking.getUploadedDocuments()) {
                doc.setBooking(booking);
            }
        }
        booking.setStatus("PENDING");
        booking.setCliente(cliente);
        return BOOKING_REPOSITORY.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return BOOKING_REPOSITORY.findAll();
    }

    @Override
    public Booking findById(Long id) {
        return BOOKING_REPOSITORY.findById(id).orElse(null);
    }

    @Override
    public Booking payBooking(Long id) {

        Booking booking = BOOKING_REPOSITORY.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking not found"));

        if(booking.getStatus().toUpperCase().equals("CANCELLED")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot pay a cancelled booking");
        }

        booking.setStatus("PAID");

        return BOOKING_REPOSITORY.save(booking);
    }

    @Override
    public Booking cancelBooking(Long id) {
        Booking booking = BOOKING_REPOSITORY.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking not found"));

        if(booking.getStatus().toUpperCase().equals("PAID")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot cancel a paid booking");
        }

        booking.setStatus("CANCELLED");

        return BOOKING_REPOSITORY.save(booking);
    }
}