package Theatre;

import java.util.LinkedList;

public class SeatArrangement {
    private Seat[] balcony = new Seat[30];
    private Seat[] circle = new Seat[30];
    private Seat[] stalls = new Seat[40];

    // Pass a linked list of seats in the format s1 b2 etc
    public Booking bookSeats(LinkedList<String> seats){
        int totalCost = 0;
        for (String seatName:seats) {
            int seatNumber = Integer.parseInt(seatName.substring(1));
            switch (seatName.charAt(0)){
                case 'b':
                    //if the given seat isn't occupied
                    if(!balcony[seatNumber-1].isOccupied()){
                        totalCost += balcony[seatNumber-1].getPrice();
                        balcony[seatNumber-1].setOccupied(true);
                    }
            }
        }
        return null;
    }


}
