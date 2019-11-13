import java.time.LocalDateTime;

public class Flight implements StringToDB {
    private static final int TOTAL_SEAT = 100;
    private int id;
    private Cities from;
    private Cities destination;
    private LocalDateTime departureDate;
    private int freeSeat;

    public Flight(int id, Cities from, Cities destination, LocalDateTime departureDate, int freeSeat) {
        this.id = id;
        this.from = from;
        this.destination = destination;
        this.departureDate = departureDate;
        this.freeSeat = freeSeat;
    }

    public static int getTotalSeat() {
        return TOTAL_SEAT;
    }

    public int getId() {
        return this.id;
    }

    public Cities getFrom() {
        return this.from;
    }

    public Cities getDestination() {
        return this.destination;
    }

    public LocalDateTime getDepartureDate() {
        return this.departureDate;
    }

    public int getFreeSeat() {
        return this.freeSeat;
    }

    @Override
    public String toStringToDB() {
        return null;
    }
}
