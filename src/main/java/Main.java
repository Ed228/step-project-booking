
import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        DBofFile<Flight> db = new DBofFile<>("DataBase");

        List<String> data = Stream.generate(() -> String.valueOf(new StringBuilder()
                        .append(Cities.values()[(int) (Math.random() * (Cities.values().length - 1))].getCity())
                        .append("\n")
                )
        )
                .limit(10)
                .collect(Collectors.toList());

        db.addMany(data);
        System.out.println(db.getAll());
    }


}
