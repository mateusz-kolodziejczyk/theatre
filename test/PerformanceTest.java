import Theatre.Performance;
import Theatre.Show;
import Utilities.TheatreLinkedList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformanceTest {
    static Show show;

    @BeforeAll
    public static void before() {
        show = new Show("The Lion King",
                LocalDate.of(2019, 9, 1), LocalDate.of(2019, 10, 1),
                20, 15, 10);
        show.addPerformance(LocalDate.of(2019,10,2), "m");
        show.addPerformance(LocalDate.of(2019, 10, 2), "e");
        show.addPerformance(LocalDate.of(2019, 10, 16), "e");
    }
    @Test
    public void setNameTest(){
    }

    @Test
    public void makeBookingtest(){
        var seats = new TheatreLinkedList<String>();
        seats.addFront("b1");
        seats.addFront("b2");
        seats.addFront("g1");
        seats.addFront("s10");
        assertEquals(true,show.addBooking(LocalDate.of(2019, 10, 2), "m", seats, "New Name"));
    }

    @AfterAll
    public static void after() {

    }
}
