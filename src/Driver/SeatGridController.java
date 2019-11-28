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


    public void initialize(){
        // If updatebooking is true, set up the seats so that the seats in the booking are not disabled
        setUpSeats(stalls, 4, 10, 's', MainViewController.isUpdatingBooking);
        setUpSeats(circle, 3, 10, 'c', MainViewController.isUpdatingBooking);
        setUpSeats(balcony, 3, 8, 'b', MainViewController.isUpdatingBooking);
    }

    private void setUpSeats(GridPane section, int rows, int perRow, char letter, boolean isUpdatingBooking){
        for(Node child:section.getChildren()){
            var column = GridPane.getColumnIndex(child);
            var row = GridPane.getRowIndex(child);
            if(column == null){
                column = 0;
            }
            if(row == null){
                row = 0;
            }
            row += 1;
            column += 1;
            var seatNumber = (rows-row)*perRow+column;
            var seatName = Character.toString(letter) + seatNumber;
            var seatButton = (ToggleButton) child;
            var seat = MainViewController.bookingPerformance.getSeatArrangement().getSeat(seatName);
            if(seat.isOccupied()){
                // In either case set the button to selected
                seatButton.setSelected(true);
                // If the function is set to update a booking, do not set the buttons to 'disabled'
                if(isUpdatingBooking && MainViewController.booking.getSeats().contains(seat)){
                    continue;
                }
                seatButton.setDisable(true);
            }
        }
    }
    @FXML
    public void bookSeats(){
        var seatList = new TheatreLinkedList<String>();
        // Looks through every section and adds to the list
        seatList = bookSeatsLoop(stalls, 4, 10, 's', seatList);
        seatList = bookSeatsLoop(circle, 3, 10, 'c', seatList);
        seatList = bookSeatsLoop(balcony, 3, 8, 'b', seatList);
        // If updating booking change booking
        if(MainViewController.isUpdatingBooking){
            MainViewController.bookingPerformance.updateBooking(seatList, MainViewController.booking);
        }
        else{
            MainViewController.bookingPerformance.makeBooking(MainViewController.bookingName, seatList);
        }
        var stage = (Stage) balcony.getScene().getWindow();
        stage.close();
    }

    private TheatreLinkedList<String> bookSeatsLoop(GridPane section, int rows, int perRow, char letter, TheatreLinkedList<String> seatList){
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
            var seatNumber = (rows-row)*perRow+column;
            var seatButton = (ToggleButton) child;
            // If its selected and not already in another booking;
            if(seatButton.isSelected() && !seatButton.isDisabled()){
                seatList.addFront(Character.toString(letter) + seatNumber);
            }
        }
        return seatList;
    }
}
