import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneratorFlightFromCity
{
    private static Cities exceptedCity;
    private static class Counter {
        private int count = 0;
        public int incAndReturnCount(){
            return this.count++;
        }
    }
    public static List<Flight> generate(Cities from, int count){
        exceptedCity = from;
        Counter c = new Counter();
        return Stream.generate(new Supplier<Flight>() {
            @Override
            public Flight get() {
                return new Flight(c.incAndReturnCount(),
                        from,
                        generateRandomCity(),
                        generateRandomTime(),
                        generateRanFreeSeat()
                        );
            }
        }).limit(count).collect(Collectors.toList());
    }
    private static Cities generateRandomCity(){
        while (true){
            Cities randomCity = Cities.values()[(int)(Math.random() * Cities.values().length)];
            if(!randomCity.name().equals(exceptedCity.name())) return randomCity;
        }
    }
    private static LocalDateTime generateRandomTime(){
        return LocalDateTime.of(2019, 11, 14, (int)(Math.random()*23) ,(int)(Math.random()*59));
    }
    private static int generateRanFreeSeat(){
        return (int)(Math.random() * Flight.TOTAL_SEAT);
    }

}
