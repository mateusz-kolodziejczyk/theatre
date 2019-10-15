package Theatre;

import Utilities.TheatreLinkedList;

public class Booking {
    // in the form b1, b10, s5 etc
    private String name;
    private int bookingID;
    private TheatreLinkedList<Seat> seats;
    private Performance performance;
    private int cost;

    public Booking(TheatreLinkedList<Seat> seats, int cost) {
        this.seats = seats;
        this.cost = cost;
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
