package com.api.oficina.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class UploadedDocument implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] data;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String mimeType; // ← NOVO CAMPO

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }

    public String getMimeType() { return mimeType; }
    public void setMimeType(String mimeType) { this.mimeType = mimeType; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }
}