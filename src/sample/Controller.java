package sample;

import Theatre.Show;
import Utilities.TheatreLinkedList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Controller {
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
    }



}
