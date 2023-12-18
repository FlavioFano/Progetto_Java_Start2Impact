package application.service;

import application.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    Booking createBooking(Booking booking);
    Booking deleteBooking(Integer bookingIdToDelete);
    Booking getBookingById(Integer bookingId);
    }

