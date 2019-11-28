package Theatre;

import Utilities.TheatreLinkedList;

public class SeatArrangement {
    private Seat[] balcony = new Seat[24];
    private Seat[] circle = new Seat[30];
    private Seat[] stalls = new Seat[40];

    public SeatArrangement(int balconyPrice, int circlePrice, int stallsPrice) {
        assignSeats('b', balcony, balconyPrice);
        assignSeats('c', circle, circlePrice);
        assignSeats('s', stalls, stallsPrice);
    }

    // Pass a linked list of seats in the format s1 b2 etc
    public TheatreLinkedList<Seat> bookSeats(TheatreLinkedList<String> seats) {
        var seatList = new TheatreLinkedList<Seat>();
        for (String seatName : seats) {
            int seatNumber = Integer.parseInt(seatName.substring(1));
            switch (seatName.charAt(0)) {
                case 'b':
                    balcony[seatNumber - 1].setOccupied(true);
                    seatList.addFront(balcony[seatNumber - 1]);
                    break;
                case 'c':
                    circle[seatNumber - 1].setOccupied(true);
                    seatList.addFront(circle[seatNumber - 1]);
                    break;
                case 's':
                    stalls[seatNumber - 1].setOccupied(true);
                    seatList.addFront(stalls[seatNumber - 1]);
                    break;
                default:
                    break;
            }
        }
        return seatList;
    }

    private void assignSeats(char seatLocation, Seat[] seatSection, int price) {
        for (int i = 0; i < seatSection.length; i++) {
            seatSection[i] = new Seat(seatLocation, i + 1, price);
        }

    }

    public TheatreLinkedList<Seat> findContinuousSeats(char section, int number) {
        switch (section) {
            case 'b':
                return continuousSeatsLoop(number, 8, balcony);
            case 'c':
                return continuousSeatsLoop(number, 10, circle);
            case 's':
                return continuousSeatsLoop(number, 10, stalls);
            default:
                return new TheatreLinkedList<>();
        }
    }

    private TheatreLinkedList<Seat> continuousSeatsLoop(int number, int seatsPerRow, Seat[] seatSection) {
        var continuousSeatList = new TheatreLinkedList<Seat>();
        for (int i = 0; i < seatSection.length; i++) {
            // If it's the first seat on the row
            if (i % seatsPerRow == 0) {
                // Reset linked list
                continuousSeatList.removeAll();
            }
            // If its occupied, go to the next one
            if (seatSection[i].isOccupied()) {
                continuousSeatList.removeAll();
                continue;
            }
            continuousSeatList.addFront(seatSection[i]);
            // If the list is the desired length, return
            if (continuousSeatList.size() >= number) {
                return continuousSeatList;
            }
        }
        // If not enough seats were found, return new empty list
        return new TheatreLinkedList<>();
    }

    public Seat[] getSection(char section) {
        switch (section) {
            case 's':
                return stalls;
            case 'c':
                return circle;
            case 'b':
                return balcony;
            default:
                return new Seat[0];
        }
    }

    public Seat getSeat(String seatName) {
        // Section name
        char section = seatName.charAt(0);
        int seatIndex = Integer.parseInt(seatName.substring(1)) - 1;
        switch (section) {
            case 's':
                return stalls[seatIndex];
            case 'c':
                return circle[seatIndex];
            case 'b':
                return balcony[seatIndex];
            default:
                return null;
        }
    }

}
