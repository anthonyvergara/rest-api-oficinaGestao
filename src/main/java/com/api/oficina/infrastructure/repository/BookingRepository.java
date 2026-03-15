package com.api.oficina.infrastructure.repository;

import com.api.oficina.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByOficinaId(Long oficinaId);
}