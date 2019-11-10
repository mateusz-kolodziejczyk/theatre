package Theatre;

import Utilities.TheatreLinkedList;

import java.time.LocalDate;
import java.util.UUID;

public class Performance {
   private SeatArrangement seatArrangement;
   private LocalDate date;
   // Matinee / evening
   private String time;
   private TheatreLinkedList<Booking> bookings = new TheatreLinkedList<>();

   public Performance(LocalDate date, String time, int balconyPrice, int circlePrice, int stallsPrice){
      seatArrangement = new SeatArrangement(balconyPrice, circlePrice, stallsPrice);
      this.date = date;
      this.time = time;
   }

   public void makeBooking(String name, TheatreLinkedList<String> seatList){
      bookings.addFront(new Booking(name, seatArrangement.bookSeats(seatList)));
   }
   public boolean deleteBooking(UUID id){
      for (Booking booking: bookings) {
         if(booking.getBookingID() == id){
            bookings.removeItem(booking);
            return true;
         }
      }
      return false;
   }

   public boolean deleteBooking(String bookingString){
      for (var booking: bookings) {
        if(booking.toString().equals(bookingString)) {
           bookings.removeItem(booking);
           return true;
        }
      }
      return false;
   }

   public SeatArrangement getSeatArrangement() {
      return seatArrangement;
   }

   public LocalDate getDate() {
      return date;
   }

   public String getTime() {
      return time;
   }

   public TheatreLinkedList<Booking> getBookings() {
      return bookings;
   }

   public String toString() {
       return date + "; " + time;
   }
}
