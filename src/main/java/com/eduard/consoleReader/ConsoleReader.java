package com.eduard.consoleReader;

import com.eduard.controller.FlightController;
import com.eduard.controller.ReservationController;
import com.eduard.dataBase.DBofFile;
import com.eduard.model.Flight;
import com.eduard.model.FlightException;
import com.eduard.model.Reservation;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleReader {
    private DBofFile<Flight> dbFlight;
    private DBofFile<Reservation> dbReservation;
    private Handler handler;
    private HashMap<Commands, ActionHandler> commandsAction = new HashMap<>();
    private FlightController flightController;
    private ReservationController reservationController;
    private final String MENU =
            "1. Get online table\n" +
            "2. Get information by race\n" +
            "3. Search and book race\n" +
            "4. Cancel book\n" +
            "5. My races\n" +
            "0. Exit\n";


    public ConsoleReader(FlightController flightController, ReservationController reservationController,
                         DBofFile<Flight> dbFlight, DBofFile<Reservation> dbReservation) {
        this.handler = new Handler(flightController, reservationController);
        this.flightController = flightController;
        this.reservationController = reservationController;
        this.dbFlight = dbFlight;
        this.dbReservation = dbReservation;
    }

    public void init(){
        commandsAction.put(Commands.RACES_TABLE, Handler::getRaceTable);
        commandsAction.put(Commands.GET_RACE_BY_ID, Handler::getRaceById);
        commandsAction.put(Commands.SEARCH_AND_RESERVE, Handler::searchAndReserve);
        commandsAction.put(Commands.CANCEL_RESERVE, Handler::cancelReserve);
        commandsAction.put(Commands.MY_RACES, Handler::printMyRacesByInitials);
    }

    public void run() throws FlightException {
        while (true) {
            System.out.println(MENU);
            System.out.println("-----------------------------------");
            System.out.println("Enter the number of menu:");
            String commandInput = new Scanner(System.in).next();

            Optional<Commands> command = Arrays.stream(Commands.values())
                    .filter(c -> c.getValue().equals(commandInput))
                    .findFirst();
            if (command.isPresent()){
                if (command.get().equals(Commands.EXIT)) {
                    try {
                        dbFlight.clearAndWrite(flightController.getAll());
                        dbReservation.clearAndWrite(reservationController.getAll());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                commandsAction.get(command.get()).doAction(this.handler);
            } else try {
                throw new ConsoleReaderException("Invalid command, try again");
            } catch (ConsoleReaderException e) {
                e.printStackTrace();
            }
        }
    }
}
