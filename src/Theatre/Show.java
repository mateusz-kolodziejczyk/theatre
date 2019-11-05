package Theatre;


import Utilities.TheatreLinkedList;

import java.time.LocalDate;

public class Show {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private TheatreLinkedList<Performance> performances = new TheatreLinkedList<>();
    private int balconyPrice;
    private int circlePrice;
    private int stallsPrice;

    public Show(String name, LocalDate startDate, LocalDate endDate, int balconyPrice, int circlePrice, int stallsPrice){
        setName(name);
        // If the set failed, set it to unknown
        if(this.name == null){
            this.name = "Unknown";
        }

        setStartDate(startDate);
        setEndDate(endDate);
        setBalconyPrice(balconyPrice);
        setCirclePrice(circlePrice);
        setStallsPrice(stallsPrice);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() > 0 && name.length() <= 30){
            this.name = name;
        }
    }

    public boolean addPerformance(LocalDate date, String time){
        // date checker
        if ((date.isAfter(startDate) || date.isEqual(startDate)) && (date.isAfter(endDate) || date.isEqual(endDate))){
            // Make sure there are no duplicates
            for (Performance performance:performances) {
               if(performance.getTime() == time && performance.getDate() == date) {
                   return false;
               }
            }
            performances.addFront(new Performance(date, time, balconyPrice, circlePrice, stallsPrice));
            return true;
        }
        else{
            return false;
        }

    }
    // Add booking to particular performance
    public boolean addBooking(LocalDate date, String time, TheatreLinkedList<String> seatList, String name){
        for (Performance performance:performances) {
            if(performance.getDate().equals(date) && performance.getTime().equals(time.toLowerCase())) {
                performance.makeBooking(name, seatList);
                return true;
            }
        }
        return false;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getBalconyPrice() {
        return balconyPrice;
    }

    public void setBalconyPrice(int balconyPrice) {
        this.balconyPrice = balconyPrice;
    }

    public int getCirclePrice() {
        return circlePrice;
    }

    public void setCirclePrice(int circlePrice) {
        this.circlePrice = circlePrice;
    }

    public int getStallsPrice() {
        return stallsPrice;
    }

    public void setStallsPrice(int stallsPrice) {
        this.stallsPrice = stallsPrice;
    }

    public String toString() {
        return name + "; Start: " + startDate + "; End: " + endDate;
    }
}
