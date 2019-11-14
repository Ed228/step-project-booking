import java.time.LocalDateTime;
import java.util.Objects;

public class Flight implements StringToDB {
    public static final int TOTAL_SEAT = 100;
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

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", from=" + from +
                ", destination=" + destination +
                ", departureDate=" + departureDate +
                ", freeSeat=" + freeSeat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id &&
                freeSeat == flight.freeSeat &&
                from == flight.from &&
                destination == flight.destination &&
                Objects.equals(departureDate, flight.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, destination, departureDate, freeSeat);
    }
}
