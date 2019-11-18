package Driver;

import Theatre.Seat;
import Theatre.Show;
import Utilities.TheatreLinkedList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SeatGridController {
    @FXML
    public GridPane stalls;
    @FXML
    public GridPane circle;
    @FXML
    public GridPane balcony;

    @FXML
    public void bookSeats(){
        var seatList = new TheatreLinkedList<String>();
        // Looks through every section and adds tot he list
        seatList = bookSeatsLoop(stalls, 4, "s", seatList);
        seatList = bookSeatsLoop(circle, 3, "b", seatList);
        seatList = bookSeatsLoop(balcony, 3, "b", seatList);
        MainViewController.bookingPerformance.makeBooking(MainViewController.bookingName, seatList);
        var stage = (Stage) balcony.getScene().getWindow();
        stage.close();
    }

    private TheatreLinkedList<String> bookSeatsLoop(GridPane section, int rows, String letter, TheatreLinkedList<String> seatList){
        //made using help from https://stackoverflow.com/questions/31145884/javafx-iterate-gridpane-nodes-per-row-read-nodes-of-gridpane-per-row
        for (Node child:section.getChildren()){
            var column = GridPane.getColumnIndex(child);
            var row = GridPane.getRowIndex(child);
            if(column == null){
                column = 0;
            }
            if(row == null){
                row = 0;
            }
            column += 1;
            row += 1;
            //To get the correct seat number
            var seatNumber = (rows-row)*10+column;
            var seatButton = (ToggleButton) child;
            if(seatButton.isSelected()){
                seatList.addFront(letter + seatNumber);
            }
        }
        return seatList;
    }
}
