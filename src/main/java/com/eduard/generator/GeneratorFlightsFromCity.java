package com.eduard.generator;

import com.eduard.model.Cities;
import com.eduard.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneratorFlightsFromCity
{
    private static Cities exceptedCity;
    private static LocalDateTime ldtStart;
    private static class Counter {
        public void setCount (int count) {this.count = count;}
        private int count = 0;
        public int incAndReturnCount(){
            return this.count++;
        }
    }
    public static List<Flight> generate(Cities from, int count, int startCount, LocalDateTime dateRangeStart){
        ldtStart = dateRangeStart;
        exceptedCity = from;
        Counter c = new Counter();
        c.setCount(startCount);
        return Stream.generate(new Supplier<Flight>() {
            @Override
            public Flight get() {
                return new Flight(
                        c.incAndReturnCount(),
                        from,
                        generateRandomCity(),
                        generateRandomTimeInRange(),
                        generateRanFreeSeat()
                        );
            }
        }).limit(count).collect(Collectors.toList());
    }
    private static Cities generateRandomCity(){
        while (true){
            Cities randomCity = Cities.values()[(int)(Math.random() * Cities.values().length)];
            if (!randomCity.name().equals(exceptedCity.name())) return randomCity;
        }
    }
    private static LocalDateTime generateRandomTimeInRange() {
        return LocalDateTime.of(ldtStart.getYear(), ldtStart.getMonth(),
                ldtStart.getHour() == 0 ? ldtStart.getDayOfMonth() : ldtStart.getDayOfMonth() + new Random().nextInt(2),
                (int)(Math.random() * 23),
                (int)(Math.random() * 59)
        );
    }
    private static int generateRanFreeSeat(){
        return (int) (Math.random() * Flight.TOTAL_SEAT);
    }

}
