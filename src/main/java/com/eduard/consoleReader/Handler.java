package com.eduard.consoleReader;

import com.eduard.*;
import com.eduard.controller.FlightController;
import com.eduard.controller.FlightControllerImpl;
import com.eduard.controller.ReservationController;
import com.eduard.controller.ReservationControllerImpl;
import com.eduard.dao.FlightDaoImpl;
import com.eduard.dao.ReservationDAOImpl;
import com.eduard.service.FlightsServiceImpl;
import com.eduard.service.ReservationServiceImpl;

import java.io.IOException;
import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

public class Handler {
    private FlightController flightController;
    private ReservationController reservationController;

    public Handler(FlightController flightController, ReservationController reservationController) {
        this.flightController = flightController;
        this.reservationController = reservationController;
    }

    public void getRaceTable() {
        printList(flightController.getAll());
    }

    public void getRaceById() {
        System.out.println("Enter the id of the race:");
        try {
            int id = new Scanner(System.in).nextInt();
            System.out.println(flightController.getById(id));
        } catch (InputMismatchException e) {
            System.out.println("Invalid input id, the id must be integer");
        }
    }

    public void searchAndReserve() {
        while (true) {
            System.out.println("Enter destination:");
            String destination = new Scanner(System.in).next();
            if (Stream.of(Cities.values()).map(Cities::getCity).noneMatch(c -> c.equalsIgnoreCase(destination))) {
                System.out.println("Entered destination city is absent, do you want try again? Y/N");
                String answer = new Scanner(System.in).next();
                if (answer.toLowerCase().equals("N")) break;
                else continue;
            }
            System.out.println("Enter departure date in format \"yyyy-dd-mm\"(for example:\"2019-11-18\"):");
            String departureDateString = new Scanner(System.in).next();

            if (!departureDateString.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")){
                System.err.println("Invalid date format, date format must be a \"yyyy-dd-mm\"");
                System.out.println("Do you want try again? Y/N");
                String answer = new Scanner(System.in).next();
                if (answer.toLowerCase().equals("N")) break;
                else continue;
            }
            System.out.println("Enter a number of free seat:");
            int freeSeat = 0;
            try {
                freeSeat = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input id, the id must be integer");
            }

            List<Flight> flights = flightController.searchByCityDateFreeSet(
                    Cities.valueOf(destination.toUpperCase()),
                    departureDateString,
                    freeSeat
            );

            if (flights.size() == 0) {
                System.out.println("No races founds by entered parameters, do you want enter again? Y/N");
                String answer = new Scanner(System.in).next();
                if (answer.equals("Y")) continue;
                else break;
            } else {
                printList(flights);
                while (true) {
                    System.out.println("To book race, enter the id of the race");
                    int id;
                    try {
                        id = new Scanner(System.in).nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input id, the id must be integer");
                        continue;
                    }
                    if (flights.stream().anyMatch(f -> f.getId() == id)) {
                        long idReservation = this.reservationController.getAll().size();
                        Reservation reservation = createReservation(freeSeat, idReservation);
                        try {
                            this.reservationController.addReservation(reservation);
                        } catch (FlightException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }
            break;
        }
    }

    private Reservation createReservation(int countFreeSeat, long idReservation) {
        String firstName;
        String lastName;
        ArrayList<Passenger> passengers = new ArrayList<>();
        while (true) {
            System.out.println("Enter first name:");
            firstName = new Scanner(System.in).next();
            if (isNotAlphabeticString(firstName)) continue;
            System.out.println("Enter last name:");
            lastName = new Scanner(System.in).next();
            if (isNotAlphabeticString(lastName)) continue;
            if (countFreeSeat > 0) {
                String firstNameTmp;
                String lastNameTmp;
                for (int i = 1; i <= countFreeSeat; i++) {
                    System.out.printf("Enter first name of %d passenger\n", i);
                    firstNameTmp = new Scanner(System.in).next();
                    if (isNotAlphabeticString(firstNameTmp)) {
                        i--;
                        continue;
                    }
                    System.out.printf("Enter last name of %d passenger\n", i);
                    lastNameTmp = new Scanner(System.in).next();
                    if (isNotAlphabeticString(lastNameTmp)) {
                        i--;
                        continue;
                    }
                    passengers.add(new Passenger(firstNameTmp, lastNameTmp));
                }
            }
            break;
        }
        return new Reservation(idReservation, firstName, lastName, countFreeSeat, passengers);
    }

    private boolean isNotAlphabeticString(String s) {
        if (!s.chars().allMatch(Character::isAlphabetic)) {
            System.out.println("Name must only contains of letters");
            return true;
        }
        return false;
    }

    public void cancelReserve() throws FlightException {
        while (true) {
            System.out.println("Enter the id of booking, for cancel him:");
            long id = -1;
            try {
                id = new Scanner(System.in).nextLong();
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
                System.out.println("Do you want try again? Y or any key for exit");
                String answer = new Scanner(System.in).next();
                if (answer.equals("Y")) continue;
                else break;
            }
            if (reservationController.removeReservation(id)) System.out.println("reservation removed successful");
            else System.out.println("No races found by this id");
            break;
        }
    }

    public void printMyRacesByInitials() {
        String firstName;
        String lastName;
        while (true) {
            firstName = new Scanner(System.in).next();
            if (isNotAlphabeticString(firstName)) {
                System.out.println("Name must only contains of letters");
                continue;
            }
            lastName = new Scanner(System.in).next();
            if (isNotAlphabeticString(lastName)) {
                System.out.println("Name must only contains of letters");
                continue;
            }
            System.out.println(
                    reservationController.getReservationsByFirstAndLastName(firstName, lastName).size() == 0 ?
                    reservationController.getReservationsByFirstAndLastName(firstName, lastName) : "No bookings found by this id"
            );
            break;
        }
    }

    private String generateSpaces(long count){
        return Stream.generate(() -> " ")
                .limit(count)
                .reduce((s1, s2) -> s1 + s2)
                .get();
    }

    public void printList(List<Flight> flightList) {
        String separator = "-------------------------------------------------------";
        String header = "ID  | FROM | DESTINATION | DEPARTURE DATE   | FREE SEAT\n" + separator;
        System.out.println(header);
        flightList.forEach(new Consumer<Flight>() {
            @Override
            public void accept(Flight f) {
                System.out.printf("%s| %s | %s | %s | %s\n",
                        f.getId() + generateSpaces(4 - String.valueOf(f.getId()).length()),
                        f.getFrom().getCity(),
                        f.getDestination().getCity() + generateSpaces(11- f.getDestination().getCity().length()),
                        f.getDepartureDate().toString(),
                        f.getFreeSeat()
                );
                System.out.println(separator);
            }
        });
    }

    public void printLst(List<Reservation> reservationList) {
        int firstNameMaxLength = reservationList.stream()
                .map(r -> r.getFirstNameOwnerReservation().length())
                .max(Integer::compareTo)
                .get();
        int lastNameMaxLength = reservationList.stream()
                .map(r -> r.getLastNameOwnerReservation().length())
                .max(Integer::compareTo)
                .get();

        String header = String.format("BOOK ID | FIRST NAME%s | LAST NAME%s | RACE ID | NUMBER OF SEATS",
                firstNameMaxLength > 10 ?  generateSpaces(firstNameMaxLength ) : "",
                lastNameMaxLength > 9 ? generateSpaces(lastNameMaxLength) : ""
        );
        String separator = Stream.generate(() -> "-").limit(header.length()).reduce((s1, s2) -> s1 + s2).get();


    }


    public static void main(String[] args) throws IOException {
        DBofFile<Flight> db = new DBofFile<>("DataBase");
        List<Flight> flights = db.getAll();
        /*------------------------------------------------------------------------*/
        FlightDaoImpl flightDao = new FlightDaoImpl(flights);
        FlightsServiceImpl flightsService = new FlightsServiceImpl(flightDao);
        FlightControllerImpl flightController = new FlightControllerImpl(flightsService);
        ReservationDAOImpl reservationDAO = new ReservationDAOImpl();
        ReservationServiceImpl reservationService = new ReservationServiceImpl(flightController, reservationDAO);
        ReservationControllerImpl reservationController = new ReservationControllerImpl(reservationService);
        /*------------------------------------------------------------------------*/
        Handler handler = new Handler(flightController, reservationController);
        handler.printList(flights);
    }

}
