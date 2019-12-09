package com.eduard.dao;

import com.eduard.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    private List<Reservation> reservations = new ArrayList<>();

    public ReservationDAOImpl(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    @Override
    public boolean removeReservation(long id) {
        Reservation reservation = this.getReservationById(id);
        if(reservation == null) return false;
        this.reservations.remove(reservation);
        return true;
    }

    @Override
    public List<Reservation> getReservationsByFirstAndLastName(String firstName, String lastName) {
        return Reservation.getReservesByFirstAndLastName(firstName, lastName, this.reservations);
    }

    @Override
    public List<Reservation> getAll() {
        return this.reservations;
    }

    @Override
    public Reservation getReservationById(long id) {
        return this.reservations.stream().filter(r -> r.getId() == id).findFirst().get();
    }
}
