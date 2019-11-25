package com.eduard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Reservation implements StringToDB {
    private long id;
    private String firstNameOwnerReservation;
    private String lastNameOwnerReservation;
    private int flightId;
    private List<Passenger> otherPassengers = new ArrayList<>();
    private int countOfSeats;

    public Reservation(long id, String firstName, String lastName, int flightId) {
        this.id = id;
        this.firstNameOwnerReservation = firstName;
        this.lastNameOwnerReservation = lastName;
        this.flightId = flightId;
        this.countOfSeats = 1;
    }

    public Reservation(long id, String firstNameOwnerReservation, String lastNameOwnerReservation, int flightId, List<Passenger> otherPassengers) {
        this.id = id;
        this.firstNameOwnerReservation = firstNameOwnerReservation;
        this.lastNameOwnerReservation = lastNameOwnerReservation;
        this.flightId = flightId;
        this.otherPassengers = otherPassengers;
        this.countOfSeats = otherPassengers.size() + 1;
    }

    public long getId() {
        return id;
    }

    public String getFirstNameOwnerReservation() {
        return firstNameOwnerReservation;
    }

    public String getLastNameOwnerReservation() {
        return lastNameOwnerReservation;
    }

    public int getFlightId() {
        return flightId;
    }

    public int getCountOfSeats() {
        return countOfSeats;
    }

    public List<Passenger> getOtherPassengers() {
        return otherPassengers;
    }

    public static List<Reservation> getReservesByFirstAndLastName(String firstName, String lastName, List<Reservation> AllReserves){
        return AllReserves.stream()
                .filter(r -> r.firstNameOwnerReservation.equals(firstName) && r.firstNameOwnerReservation.equals(lastName)
                        || r.otherPassengers.stream().anyMatch(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id &&
                countOfSeats == that.countOfSeats &&
                Objects.equals(firstNameOwnerReservation, that.firstNameOwnerReservation) &&
                Objects.equals(lastNameOwnerReservation, that.lastNameOwnerReservation) &&
                Objects.equals(flightId, that.flightId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstNameOwnerReservation, lastNameOwnerReservation, flightId, countOfSeats);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", firstNameOwnerReservation='" + firstNameOwnerReservation + '\'' +
                ", lastNameOwnerReservation='" + lastNameOwnerReservation + '\'' +
                ", flightId=" + flightId +
                ", countOfSeats=" + countOfSeats +
                '}';
    }

    @Override
    public String toDBSting() {
        return null;
    }

}
