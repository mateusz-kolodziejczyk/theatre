package Theatre;


import Utilities.TheatreLinkedList;

public class Show {
    private String name;
    private TheatreLinkedList<Performance> Performances;

    public Show(String name){
        setName(name);
        // If the set failed, set it to unkown
        if(this.name == null){
            this.name = "Unknown";
        }
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
}
