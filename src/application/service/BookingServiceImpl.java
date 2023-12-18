package application.service;

import application.dao.BookingDao;
import application.dao.BookingDaoImpl;
import application.entity.Booking;

import java.util.List;

public class BookingServiceImpl implements BookingService{
    BookingDao bookingDao= new BookingDaoImpl();

    @Override
    public List<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

    @Override
    public Booking createBooking(Booking bookingRequest) {
        return bookingDao.createBooking(bookingRequest);
    }

    @Override
    public Booking deleteBooking(Integer bookingIdToDelete) {
        return bookingDao.deleteBooking(getBookingById(bookingIdToDelete));
    }

    public Booking getBookingById(Integer bookingId) {
        return getAllBookings().stream().filter(booking ->booking.getId().equals(bookingId)).findAny().orElse(null);
    }
}
