package application.service;

import application.entity.Trip;
import application.dao.TripDao;
import application.dao.TripDaoImpl;

import java.util.List;
import java.util.stream.Collectors;

public class TripServiceImpl implements TripService {

    TripDao tripDao= new TripDaoImpl();

    @Override
    public List<Trip> getAll() {
        return tripDao.findAll();
    }

    @Override
    public List<Trip> showAvailableTrips() {
        return getAll()
                .stream()
                .filter(trip -> trip.getDisponibile().equalsIgnoreCase("SI"))
                .collect(Collectors.toList());
    }

    @Override
    public Trip getAvailableTripById(Integer id) {
        return showAvailableTrips().stream().filter(trip -> trip.getId().equals(id)).findAny().orElse(null);
    }

    @Override//usato per creare una prenotazione
    public void updateAvailableToYes(Trip trip) {
        trip.setDisponibile("SI");
        tripDao.update(trip);
    }

    @Override//usato per eliminare una prenotazione
    public void updateAvailableToNo(Trip trip) {
        trip.setDisponibile("NO");
        tripDao.update(trip);
    }

    @Override
    public Trip getTripById(Integer tripId) {
        return getAll().stream().filter(trip -> trip.getId().equals(tripId)).findAny().orElse(null);
    }

    @Override
    public void writeNewAvailableTripsCsv(String filepath) {
    tripDao.writeNewAvailableTripsCsv(filepath);
    }
}
