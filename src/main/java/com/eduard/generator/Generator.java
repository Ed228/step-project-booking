package com.eduard.generator;

import com.eduard.model.Cities;
import com.eduard.model.Flight;
import com.eduard.dataBase.DBofFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Generator {
    public static void main(String[] args) throws IOException {
        DBofFile<Flight> dbOfFile = new DBofFile<>("DataBaseRaces");
        ArrayList<Flight> flights = new ArrayList<>();
        int j = 0;
        int k = 0;
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime nowDateTimePer24Hours = LocalDateTime.of(
                nowDateTime.getYear(), nowDateTime.getMonth(), nowDateTime.getDayOfMonth() + 1, nowDateTime.getHour(), nowDateTime.getMinute(), nowDateTime.getSecond(), nowDateTime.getNano()
        );
        System.out.println(nowDateTime);
        System.out.println(nowDateTimePer24Hours);
        for (int i = 0; i < 13; i++) {
            LocalDateTime incrementDate = LocalDateTime.of(nowDateTime.getYear(), nowDateTime.getMonth(), nowDateTime.getDayOfMonth() + k++, nowDateTime.getHour(), nowDateTime.getMinute());
            flights.addAll(GeneratorFlightsFromCity.generate(Cities.KIEV, 40, j, incrementDate));
            j = j + 40;
        }
        dbOfFile.addMany(flights);

    }
}
