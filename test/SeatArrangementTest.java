import Theatre.Booking;
import Theatre.Seat;
import Theatre.SeatArrangement;
import Utilities.TheatreLinkedList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeatArrangementTest {
    private static SeatArrangement seatArrangement;
    private static TheatreLinkedList<String> seatNames;
    @BeforeAll
    public static void before(){
        seatArrangement = new SeatArrangement(20, 15, 10);
        seatNames = new TheatreLinkedList<>();
        seatNames.addFront("b1");
        seatNames.addFront("c4");
        // duplicate
        seatNames.addFront("b1");
        seatNames.addFront("b2");
        // incorrect
        seatNames.addFront("g1");
        seatNames.addFront("s10");
        // Total price == 10+40+15 = 65
    }

    @Test
    public void bookSeatsTest(){
        var seatList = seatArrangement.bookSeats(seatNames);
        var totalCost = 0;
        for (Seat s:seatList) {
            totalCost+=s.getSeatPrice();
        }
        assertEquals(65, totalCost);
    }

    @AfterAll
    public static void after(){

    }
}
