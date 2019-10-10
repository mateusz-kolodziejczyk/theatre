package Theatre;

import Utilities.TheatreLinkedList;

public class Booking {
    // in the form b1, b10, s5 etc
    private TheatreLinkedList<String> seats;
    private int cost;

    public Booking(TheatreLinkedList<String> seats, int cost) {
        this.seats = seats;
        this.cost = cost;
    }

    public TheatreLinkedList<String> getSeats() {
        return seats;
    }

    public void setSeats(TheatreLinkedList<String> seats) {
        this.seats = seats;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
