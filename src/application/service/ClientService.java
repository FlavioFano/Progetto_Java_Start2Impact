package application.service;

import application.entity.Booking;
import application.entity.Trip;
import application.dto.UserDto;
import application.request.UserRequest;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ClientService {
    private TripService tripService = new TripServiceImpl();
    private UserService userService = new UserServiceImpl();
    private BookingService bookingService = new BookingServiceImpl();

    public void getOperation(int input) {
        switch (input) {
            case 1://1 - Visualizza tutti i viaggi
                showAllTrips();
                break;
            case 2://2 - Prenota un viaggio
                bookTrip();
                break;
            case 3://3 - Disdici la prenotazione di un viaggio
                if (bookingService.getAllBookings().size() == 0) {
                    showMessage("Spiacente, non ci sono prenotazioni!");
                } else {
                    cancelTrip();
                }
                break;
            case 4://4 - Aggiungi un nuovo utente
                createNewUser();
                break;
            case 5://5 - Esporta un file con i viaggi disponibili
                if (dontExistAvailableTrips(tripService.showAvailableTrips())) {
                    System.err.println("Spiacente, non ci sono viaggi disponibili");
                    break;
                } else {
                    exportAvailableTripsCsvFile();
                }
                break;
        }
    }

    private void showAllTrips() {
        List<Trip> trips = tripService.getAll();
        showMessage("\nEcco i viaggi esistenti:\n");
        showFormattedTripList(trips);
    }

    private void bookTrip() {
        List<Trip> availableTrips = tripService.showAvailableTrips();
        if (dontExistAvailableTrips(availableTrips)) {
            System.err.println("Spiacente, non ci sono viaggi disponibili da prenotare!");
        } else {
            showMessage("Quale viaggio vuoi prenotare tra i seguenti?");
            showFormattedTripList(availableTrips);
            showMessage("\n\nInserire id del viaggio da scegliere:");
            int tripRequestId = getUserIntInput();

            Trip trip = tripService.getAvailableTripById(tripRequestId);
            trip = KeepAskingForTheTripIdIfTripDoesntExist(trip);


            List<UserDto> usersDto = userService.getAll();
            showMessage("Con quale utente vuoi prenotare il viaggio");
            showFormattedUserDtoList(usersDto);
            showMessage("Inserisci l'id dell'utente da scegliere");
            int userRequestId = getUserIntInput();

            UserDto userDto = keepAkingForUserIdIfUserDoesntExist(userService.getUserById(userRequestId));

            Booking bookingRequest = new Booking();
            bookingRequest.setIdViaggio(trip.getId());
            bookingRequest.setIdUtente(userDto.getUserDtoId());
            bookingRequest = bookingService.createBooking(bookingRequest);
            tripService.updateAvailableToNo(trip);
            System.out.println("Hai creato con successo la prenotazione:");
            showFormattedBooking(bookingRequest);
        }
    }

    private boolean dontExistAvailableTrips(List<Trip> availableTrips) {
        return availableTrips.size() == 0;
    }

    private UserDto keepAkingForUserIdIfUserDoesntExist(UserDto userDto) {
        int userRequestId;
        while (Objects.isNull(userDto)) {
            showMessage("Utente non trovato, riprovare!");
            userRequestId = getUserIntInput();
            userDto = userService.getUserById(userRequestId);
        }
        return userDto;
    }

    private Trip KeepAskingForTheTripIdIfTripDoesntExist(Trip trip) {
        int tripRequestId;
        while (Objects.isNull(trip)) {
            System.out.println("Viaggio non trovato, riprovare!");
            tripRequestId = getUserIntInput();
            trip = tripService.getAvailableTripById(tripRequestId);
        }
        return trip;
    }

    private int getUserIntInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String getUserStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void showMessage(String x) {
        System.out.println(x);
    }

    private void showFormattedTripList(List<Trip> trips) {
        System.out.println("ID-Data-Durata (ore)-Partenza-Arrivo-Disponibile");
        for (Trip trip : trips) {
            System.out.println(trip);
        }
    }

    private void showFormattedUserDtoList(List<UserDto> users) {
        System.out.println("ID-Nome-Cognome-Data di nascita");
        for (UserDto userDto : users) {
            System.out.println(userDto);
        }
    }

    private void showFormattedUserRequest(UserRequest userRequest) {
        System.out.println("ID-Nome-Cognome-Data di nascita-Indirizzo-ID Documento");
        System.out.println(userRequest);
    }

    private void showFormattedBookingList(List<Booking> bookings) {
        System.out.println("ID Prenotazione-ID Viaggio-ID Utente");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private void showFormattedBooking(Booking cancelledBooking) {
        System.out.println("ID Prenotazione-ID Viaggio-ID Utente");
        System.out.println(cancelledBooking);
    }

    private void cancelTrip() {
        showMessage("Quale delle seguenti prenotazioni vuoi eliminare?");
        List<Booking> bookingList = bookingService.getAllBookings();
        showFormattedBookingList(bookingList);
        showMessage("Digitare l'id della prenotazione da eliminare:");
        Integer idToDelete = getUserIntInput();
        updateTripAvailability(idToDelete);
        Booking cancelledBooking = bookingService.deleteBooking(idToDelete);
        showMessage("Hai eliminato con successo la prenotazione:");
        showFormattedBooking(cancelledBooking);
    }

    private void updateTripAvailability(Integer idToDelete) {
        Booking bookingRequest = bookingService.getBookingById(idToDelete);
        Booking bookingFound = KeepAskingForTheBookingIdIfDoesntExist(bookingRequest);
        Integer idViaggio = bookingFound.getIdViaggio();
        tripService.updateAvailableToYes(tripService.getTripById(idViaggio));
    }

    private Booking KeepAskingForTheBookingIdIfDoesntExist(Booking booking) {
        int bookingRequestId;
        while (Objects.isNull(booking)) {
            System.out.println("Prenotazione non trovata, riprovare!");
            bookingRequestId = getUserIntInput();
            booking = bookingService.getBookingById(bookingRequestId);
        }
        return booking;
    }

    private void createNewUser() {
        UserRequest userRequest = new UserRequest();
        showMessage("Inserire nome:");
        userRequest.setNome(getUserStringInput());
        showMessage("Inserire cognome:");
        userRequest.setCognome(getUserStringInput());
        showMessage("Inserire data di nascita:");
        userRequest.setDataDiNascita(getUserStringInput());
        showMessage("Inserire indirizzo di residenza:");
        userRequest.setIndirizzo(getUserStringInput());
        showMessage("Inserire l'id del documento:");
        userRequest.setIdDocumento(getUserStringInput());
        UserRequest createdUser = userService.createUser(userRequest);
        showMessage("Hai creato con successo l'utente:");
        showFormattedUserRequest(createdUser);
    }

    private void exportAvailableTripsCsvFile() {
        showMessage("Dove vuoi salvare il file? Inserire il percorso (Es. C:\\Desktop)");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        tripService.writeNewAvailableTripsCsv(path);
    }
}
