package application.service;
import application.entity.Trip;
import java.util.List;

public interface TripService {
    List<Trip> getAll();
    List<Trip> showAvailableTrips();
    Trip getAvailableTripById(Integer tripId);
    void updateAvailableToYes(Trip trip);
    void updateAvailableToNo(Trip trip);
    Trip getTripById(Integer tripId);
    void writeNewAvailableTripsCsv(String filepath);
}
