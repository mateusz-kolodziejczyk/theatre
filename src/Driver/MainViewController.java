package Driver;

import Theatre.Show;
import Utilities.TheatreLinkedList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    @FXML
    public Label addShowError;

    // Add performance fields
    @FXML
    public ChoiceBox<String> addPerformanceShow;
    @FXML
    public DatePicker addPerformanceDate;
    @FXML
    public ChoiceBox<String> addPerformanceTime;
    @FXML
    public Label addPerformanceError;

    // Book seats fields
    @FXML
    public ChoiceBox<String> bookSeatsShow;
    // General variables
    private TheatreLinkedList<Show> shows = new TheatreLinkedList<>();

    @FXML
    public void addShow() {
        if (addShowName.getText().equals("")
                || addShowBalconyPrice.getText().equals("")
                || addShowCirclePrice.getText().equals("")
                || addShowStallsPrice.getText().equals("")
                || addShowStartDate.getValue() == null
                || addShowEndDate.getValue() == null) {
            addShowError.setText("Not all fields are set. Please fill out all the fields");
            return;
        }
        // Not for whole or.
        if (!(addShowStartDate.getValue().isBefore(addShowEndDate.getValue()) || addShowStartDate.getValue().isEqual(addShowEndDate.getValue()))) {
            addShowError.setText("Start date has to be before or the same as the end date");
            return;
        }
        var show = new Show(addShowName.getText(), addShowStartDate.getValue(), addShowEndDate.getValue(),
                Integer.parseInt(addShowBalconyPrice.getText()), Integer.parseInt(addShowCirclePrice.getText()), Integer.parseInt(addShowStallsPrice.getText()));
        shows.addFront(show);
        addShowName.clear();
        addShowBalconyPrice.clear();
        addShowCirclePrice.clear();
        addShowStallsPrice.clear();
        addShowStartDate.getEditor().clear();
        addShowEndDate.getEditor().clear();
        addShowError.setText("");

        // fill the performance showlist
    }

    @FXML
    public void addPerformance() {
        for (Show show : shows) {
            if (show.toString().equals(addPerformanceShow.getValue())) {
                // Date checking done with help of https://stackoverflow.com/questions/494180/java-how-do-i-check-if-a-date-is-within-a-certain-range
                if (!(addPerformanceDate.getValue().isBefore(show.getStartDate()) || addPerformanceDate.getValue().isAfter(show.getEndDate()))) {
                    // If adding the show succeeded
                    if (show.addPerformance(addPerformanceDate.getValue(), addPerformanceTime.getValue())) {
                        addPerformanceError.setText("");
                        addPerformanceDate.getEditor().clear();
                    }
                    else {
                        addPerformanceError.setText("Performance already exists at this date and time.");
                    }
                }
                else {
                    addPerformanceError.setText("Performance isn't within the range of dates for show: " + show.toString());
                }
            }
        }
    }

    @FXML
    public void addPerformanceStart() {
        addPerformanceShow.getItems().clear();
        for (Show show : shows) {
            addPerformanceShow.getItems().add(show.toString());
        }
        addPerformanceTime.getItems().clear();
        addPerformanceTime.getItems().add("matinee");
        addPerformanceTime.getItems().add("evening");
    }


}
