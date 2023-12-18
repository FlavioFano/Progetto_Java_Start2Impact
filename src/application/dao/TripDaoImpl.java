package application.dao;

import application.entity.Trip;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TripDaoImpl implements TripDao {
    private final String CSV_FILE_PATH = "src/File/viaggi.csv";
    @Override
    public List<Trip> findAll() {
        List<Trip> trips = new ArrayList<>();

        try (BufferedReader tripReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            skipFirstLine(tripReader);
            String line;

            while ((line = tripReader.readLine()) != null) {
                trips.add(createTripFromLine(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return trips;
    }

    private Trip createTripFromLine(String line) {
        Trip trip = new Trip();
        String[] words = line.split(";");

        trip.setId(Integer.valueOf(words[0]));
        trip.setData(words[1]);
        trip.setDurata(words[2]);
        trip.setPartenza(words[3]);
        trip.setArrivo(words[4]);
        trip.setDisponibile(words[5]);

        return trip;
    }

    private void skipFirstLine(BufferedReader tripReader) throws IOException {
        tripReader.readLine();
    }

    @Override
    public Trip update(Trip tripRequest) {
        List<Trip> trips = findAll();
        updateTripInTrips(tripRequest, trips);
        writeFromZeroTripCsvWithUpdatedTrip(trips);
        return tripRequest;
    }

    private void updateTripInTrips(Trip tripRequest, List<Trip> trips) {
        for(Trip trip:trips) {
            if(Objects.equals(trip.getId(), tripRequest.getId())){
                trip.setDisponibile(tripRequest.getDisponibile());
            }
        }
    }

    private void writeFromZeroTripCsvWithUpdatedTrip(List<Trip> trips) {
        try {
            Writer writer = new FileWriter(CSV_FILE_PATH);
            writeFirstTripCsvLine(writer);

            for (Trip trip : trips) {
                writeTripCsvLineWithTripFields(writer, trip);
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFirstTripCsvLine(Writer writer) throws IOException {
        writer.write("ID;Data;Durata (ore);Partenza;Arrivo;Disponibile\n");
    }

    private void writeTripCsvLineWithTripFields(Writer writer, Trip trip) throws IOException {
        writer.write(trip.getId() + ";" + trip.getData() + ";" +
                trip.getDurata() + ";" + trip.getPartenza() + ";" +
                trip.getArrivo() + ";" + trip.getDisponibile() + "\n");
    }

    @Override
    public void writeNewAvailableTripsCsv(String filepath) {
        List<Trip> tripList=findAll();

        LocalDate todaysDate = LocalDate.now();
        try(BufferedWriter tripWriter = new BufferedWriter(new FileWriter(filepath+"\\viaggi_"+todaysDate+".csv"))) {
            writeFirstTripCsvLine(tripWriter);
            writeAvailableCsvFileRecords(tripList, tripWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeAvailableCsvFileRecords(List<Trip> tripList, BufferedWriter tripWriter) throws IOException {
        for(Trip trip: tripList){
            if (trip.getDisponibile().equals("SI")){
                tripWriter.write(trip.getId() + ";" + trip.getData() + ";" + trip.getDurata() +
                        ";" + trip.getPartenza() + ";" + trip.getArrivo() + ";" + trip.getDisponibile() + "\n");
            }
        }
    }

}
