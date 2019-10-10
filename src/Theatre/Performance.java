package Theatre;

import Utilities.TheatreLinkedList;

import java.time.LocalDate;

public class Performance {
   private SeatArrangement seatArrangement;
   private LocalDate date;
   // Matinee / evening
   private String time;
   private TheatreLinkedList<Booking> bookings;

}
