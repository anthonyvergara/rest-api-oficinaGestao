package com.api.oficina.serviceImpl;

import com.api.oficina.infrastructure.integrations.Sender;
import com.api.oficina.model.Booking;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.UploadedDocument;
import com.api.oficina.infrastructure.repository.BookingRepository;
import com.api.oficina.infrastructure.repository.ClienteRepository;
import com.api.oficina.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Transactional
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository BOOKING_REPOSITORY;
    private final ClienteRepository CLIENTE_REPOSITORY;
    private final Sender SENDER;

    public BookingServiceImpl(BookingRepository BOOKING_REPOSITORY, ClienteRepository CLIENTE_REPOSITORY, Sender SENDER) {
        this.BOOKING_REPOSITORY = BOOKING_REPOSITORY;
        this.CLIENTE_REPOSITORY = CLIENTE_REPOSITORY;
        this.SENDER = SENDER;
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

    @Transactional(readOnly = true)
    @Override
    public List<Booking> findAll() {
        return BOOKING_REPOSITORY.findAll();
    }

    @Transactional(readOnly = true)
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

        booking = BOOKING_REPOSITORY.save(booking);

        String body = "Dear "+ booking.getCliente().getNome() +" "+ booking.getCliente().getSobrenome()+",\n\n"
                + "Your MOT test booking has been successfully confirmed.\n\n"
                + "Date: "+ booking.getDate().toLocalDate()+"\n\n"
                + "Please find your booking reference attached to this email for your records.\n\n"
                + "If you have any questions or need to make changes to your appointment, feel free to get in touch.\n\n"
                + "Best regards,\n"
                + booking.getCliente().getOficina().getNomeOficina();

        this.SENDER.sendEmail(booking.getEmail(), "Booking Confirmed – MOT Test Appointment", body, booking.getPdfBase64());

        return booking;
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