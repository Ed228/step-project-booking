package com.eduard.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Reservation implements StringToDB, OfDBString<Reservation> {
    private long id;
    private String firstNameOwnerReservation;
    private String lastNameOwnerReservation;
    private int flightId;
    private List<Passenger> otherPassengers = new ArrayList<>();
    private int countOfSeats;

    public Reservation(long id, String firstNameOwnerReservation, String lastNameOwnerReservation, int flightId, List<Passenger> otherPassengers) {
        this.id = id;
        this.firstNameOwnerReservation = firstNameOwnerReservation;
        this.lastNameOwnerReservation = lastNameOwnerReservation;
        this.flightId = flightId;
        this.otherPassengers = otherPassengers;
        this.countOfSeats = otherPassengers.size();
    }

    public Reservation() {}

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
                .filter(r -> r.firstNameOwnerReservation.equals(firstName) && r.lastNameOwnerReservation.equals(lastName)
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
        final StringBuilder sb = new StringBuilder();
        return sb.append(this.id).append(" ")
        .append(this.firstNameOwnerReservation).append(" ")
        .append(this.lastNameOwnerReservation).append(" ")
        .append(this.flightId).append(" ")
        .append(this.otherPassengers.stream()
                .map(p -> String.format("%s,%s", p.getFirstName(), p.getLastName()))
                .reduce((s1, s2) -> s1 + ";" + s2).get())
                .append(" ")
        .append(this.countOfSeats).toString();
    }

    @Override
    public Reservation ofDBString(String s) {
        String[] args = s.split("\\s+");
        return new Reservation(
                Long.parseLong(args[0]),
                args[1],
                args[2],
                Integer.parseInt(args[3]),
                Arrays.stream(args[4].split(";"))
                        .map(s1 -> {
                    String[] initials = s1.split(",");
                    return new Passenger(initials[0], initials[1]);
                })
                .collect(Collectors.toList())
        );
    }
}
