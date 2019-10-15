package Theatre;


import Utilities.TheatreLinkedList;

import java.time.LocalDate;

public class Show {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private TheatreLinkedList<Performance> Performances = new TheatreLinkedList<>();
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

    public void addPerformance(Performance performance){
        Performances.addFront(performance);
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
}
