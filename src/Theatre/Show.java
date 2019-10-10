package Theatre;


import Utilities.TheatreLinkedList;

import java.time.LocalDate;

public class Show {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private TheatreLinkedList<Performance> Performances = new TheatreLinkedList<>();
    private int balconyCost;
    private int circleCost;
    private int stallsCost;

    public Show(String name, LocalDate startDate, LocalDate endDate, int balconyCost, int circleCost, int stallsCost){
        setName(name);
        // If the set failed, set it to unknown
        if(this.name == null){
            this.name = "Unknown";
        }

        setStartDate(startDate);
        setEndDate(endDate);
        setBalconyCost(balconyCost);
        setCircleCost(circleCost);
        setStallsCost(stallsCost);
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

    public int getBalconyCost() {
        return balconyCost;
    }

    public void setBalconyCost(int balconyCost) {
        this.balconyCost = balconyCost;
    }

    public int getCircleCost() {
        return circleCost;
    }

    public void setCircleCost(int circleCost) {
        this.circleCost = circleCost;
    }

    public int getStallsCost() {
        return stallsCost;
    }

    public void setStallsCost(int stallsCost) {
        this.stallsCost = stallsCost;
    }
}
