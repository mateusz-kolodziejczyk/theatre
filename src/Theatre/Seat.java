package Theatre;

public class Seat {
    private boolean isOccupied = false;
    private char seatLocation;
    private int seatNumber;
    private int price = 0;


    public Seat(char seatLocation, int seatNumber, int seatPrice){
       setSeatLocation(seatLocation);
       setSeatNumber(seatNumber);
    }
    public char getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(char seatLocation) {
        this.seatLocation = seatLocation;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String toString() {
        // Will return seats in the format "B4" "C5" etc
        return Character.toString(seatLocation).toUpperCase() + Integer.toString(seatNumber);
    }
}
