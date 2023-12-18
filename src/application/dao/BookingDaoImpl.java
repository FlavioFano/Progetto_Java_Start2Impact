package application.dao;

import application.entity.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookingDaoImpl implements BookingDao {
    private final String CSV_FILE_PATH = "src/File/prenotazioni.csv";

    @Override
    public List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();

        try (BufferedReader bookingsReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            skipFirstLine(bookingsReader);
            String line;

            while ((line = bookingsReader.readLine()) != null) {
                bookings.add(createBookingFromLine(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    private void skipFirstLine(BufferedReader bookingReader) throws IOException {
        bookingReader.readLine();
    }

    private Booking createBookingFromLine(String line) {
        Booking booking = new Booking();
        String[] words = line.split(";");

        booking.setId(Integer.valueOf(words[0]));
        booking.setIdViaggio(Integer.valueOf(words[1]));
        booking.setIdUtente(Integer.valueOf(words[2]));
        return booking;
    }

    @Override
    public Booking createBooking(Booking booking) {
        int lastId = getLastBookingId();
        booking.setId(lastId + 1);
        try {
            Writer writer = new FileWriter(CSV_FILE_PATH, true);
            writer.write("\n" + booking.getId() + ";" +
                    booking.getIdViaggio() + ";" + booking.getIdUtente());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return booking;
    }

    public Integer getLastBookingId() {
        List<Booking> bookingList = getAllBookings();
        Integer lastId = 0;
        for (Booking booking : bookingList) {
            lastId = booking.getId();
        }
        return lastId;
    }

    @Override
    public Booking deleteBooking(Booking bookingToDelete) {
        List<Booking> bookingsList = getAllBookings();
        Booking bookingCopy = new Booking();
        for (Booking booking : bookingsList) {
            if (Objects.equals(booking.getId(), bookingToDelete.getId())) {
                bookingCopy = booking;
            }
        }
        bookingsList.remove(bookingCopy);
        writeFromZeroBookingCsvWithUpdatedList(bookingsList);
        return bookingCopy;
    }

    private void writeFromZeroBookingCsvWithUpdatedList(List<Booking> bookingsList) {
        writeFirstBookingCsvLine();
        for (Booking booking : bookingsList) {
            writeBookingCsvLineWithBookingFields(booking);
        }

    }

    private void writeFirstBookingCsvLine() {
        try {
            Writer writer = new FileWriter(CSV_FILE_PATH);
            writer.write("ID;ID Viaggio;ID Utente");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void writeBookingCsvLineWithBookingFields(Booking booking) {
        try {
            Writer writer = new FileWriter(CSV_FILE_PATH, true);
            writer.write("\n" + booking.getId() + ";" + booking.getIdViaggio() + ";" + booking.getIdUtente());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
