
import java.io.*;
import java.time.*;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

public class Main {

    public static void main2(String[] args) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT+02:00"));
       List<Flight> flights = GeneratorFlightFromCity.generate(Cities.KIEV, 15, now);
        flights.forEach(flight -> System.out.println(flight.toDBSting()));
    }

    public static void main(String[] args) throws IOException {
        DBofFile<Flight> db = new DBofFile<>("DataBase");
        LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT+02:00"));
        List<Flight> flights = GeneratorFlightFromCity.generate(Cities.KIEV, 15, now);
        db.addMany(flights);
    }

}
