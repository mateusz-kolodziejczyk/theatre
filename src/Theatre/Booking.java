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
        // Total cost is just the sum of all the seats prices;
        for (Seat s:seats) {
            cost+=s.getSeatPrice();
        }
        this.bookingID = UUID.randomUUID();
    }

    public TheatreLinkedList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(TheatreLinkedList<Seat> seats) {
        this.seats = seats;
    }

    public int getPrice() {
        return cost;
    }

    public void setPrice(int cost) {
        this.cost = cost;
    }
}
