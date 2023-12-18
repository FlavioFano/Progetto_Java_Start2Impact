package application.dao;

import application.entity.Booking;

import java.util.List;

public interface BookingDao {
    List<Booking> getAllBookings();
    Booking createBooking(Booking booking);
    Booking deleteBooking(Booking booking);

}
