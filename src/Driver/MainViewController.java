package Driver;

import Theatre.Show;
import Utilities.TheatreLinkedList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class MainViewController {
    // Add show fields
    @FXML
    public DatePicker addShowStartDate;
    @FXML
    public TextField addShowName;
    @FXML
    public DatePicker addShowEndDate;
    @FXML
    public TextField addShowBalconyPrice;
    @FXML
    public TextField addShowCirclePrice;
    @FXML
    public TextField addShowStallsPrice;

    // Add performance fields
    @FXML
    public ChoiceBox<String> addPerformanceShow;
    @FXML
    public DatePicker addPerformanceDate;
    @FXML
    public ChoiceBox<String> addPerformanceTime;
    // General variables
    private TheatreLinkedList<Show> shows = new TheatreLinkedList<>();

    @FXML
    public void addShow(){
        var show = new Show(addShowName.getText(), addShowStartDate.getValue(), addShowEndDate.getValue(),
                Integer.parseInt(addShowBalconyPrice.getText()), Integer.parseInt(addShowCirclePrice.getText()), Integer.parseInt(addShowStallsPrice.getText()));
        shows.addFront(show);
        addShowName.clear();
        addShowBalconyPrice.clear();
        addShowCirclePrice.clear();
        addShowStallsPrice.clear();
        // fill the performance showlist
        addPerformanceShow.getItems().add(show.getName());
    }

    @FXML
    public void addPerformance(){
        for (Show show:shows) {
           if(show.getName().equals(addPerformanceShow.getValue())){
               show.addPerformance(addPerformanceDate.getValue(), addPerformanceTime.getValue());
           }
        }
    }



}
