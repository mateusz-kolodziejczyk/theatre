package Theatre;

import Utilities.TheatreLinkedList;

import java.time.LocalDate;

public class Performance {
   private SeatArrangement seatArrangement;
   private LocalDate date;
   // Matinee / evening
   private String time;
   private TheatreLinkedList<Booking> bookings;

   public Performance(LocalDate date, String time, int balconyPrice, int circlePrice, int stallsPrice){
      seatArrangement = new SeatArrangement(balconyPrice, circlePrice, stallsPrice);
      this.date = date;
      this.time = time;
   }
}
