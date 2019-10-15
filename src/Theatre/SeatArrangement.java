package Theatre;

import java.util.LinkedList;

public class SeatArrangement {
    private Seat[] balcony = new Seat[24];
    private Seat[] circle = new Seat[30];
    private Seat[] stalls = new Seat[40];

    public SeatArrangement(int balconyPrice, int circlePrice, int stallsPrice){
        assignSeats('b', balcony, balconyPrice);
        assignSeats('c', circle, circlePrice);
        assignSeats('s', stalls, stallsPrice);
    }
    // Pass a linked list of seats in the format s1 b2 etc
    public Booking bookSeats(LinkedList<String> seats){
        int totalPrice = 0;
        for (String seatName:seats) {
            int seatNumber = Integer.parseInt(seatName.substring(1));
            switch (seatName.charAt(0)){
                case 'b':
                    //if the given seat isn't occupied
                    if(!balcony[seatNumber-1].isOccupied()){
                        totalPrice += balcony[seatNumber-1].getPrice();
                        balcony[seatNumber-1].setOccupied(true);
                    }
            }
        }
        return null;
    }

    private void assignSeats(char seatLocation, Seat[] seatSection, int price){
        for (int i = 0; i < seatSection.length; i++){
            balcony[i] = new Seat(seatLocation, i, price);
        }
    }



}
