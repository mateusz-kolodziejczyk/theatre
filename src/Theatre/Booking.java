package Theatre;

import Utilities.TheatreLinkedList;

import java.util.UUID;

public class Booking {
    // in the form b1, b10, s5 etc
    private String name;
    private UUID bookingID;
    private TheatreLinkedList<Seat> seats;
    private int cost = 0;

    public Booking(String name, TheatreLinkedList<Seat> seats) {
        this.name = name;
        this.seats = seats;
        updateCost();
        this.bookingID = UUID.randomUUID();
    }

    public UUID getBookingID() {
        return bookingID;
    }

    public void setBookingID(UUID bookingID) {
        this.bookingID = bookingID;
    }

    public TheatreLinkedList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(TheatreLinkedList<Seat> seats) {
        // Go through each seat in the booking, making sure to de-occupy any that have been removed
        for (var seat:this.seats) {
            // If the current seat list has the seat, but the new one doesn't, make it unoccupied as it is getting removed from the list
           if(!seats.contains(seat)) {
               seat.setOccupied(false);
           }
        }
        this.seats = seats;
        updateCost();
    }

    public int getCost() {
        updateCost();
        return cost;
    }

    public void updateCost(){
        for (Seat seat: seats) {
            cost+=seat.getSeatPrice();
        }
    }

    public String toString() {
        var returnString = new StringBuilder();
        returnString.append(name).append(": ");
        for (Seat seat:seats) {
           returnString.append(seat.toString()).append(", ") ;
        }
        returnString.append(": €").append(cost);
        return returnString.toString();
    }
}
