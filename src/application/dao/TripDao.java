package application.dao;

import application.entity.Trip;

import java.util.List;

public interface TripDao {
    List<Trip> findAll();
    Trip update(Trip trip);
    void writeNewAvailableTripsCsv(String filepath);


}
