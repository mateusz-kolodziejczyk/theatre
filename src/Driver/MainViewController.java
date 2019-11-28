package Driver;

import Theatre.Booking;
import Theatre.Performance;
import Theatre.Seat;
import Theatre.Show;
import Utilities.TheatreLinkedList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.server.ExportException;

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
	@FXML
	public TextField addShowRunTime;

    // Add performance fields
    @FXML
    public ChoiceBox<Show> addPerformanceShow;
    @FXML
    public DatePicker addPerformanceDate;
    @FXML
    public ChoiceBox<String> addPerformanceTime;
    @FXML
    public Label addPerformanceError;

    // Book seats fields
    @FXML
    public ChoiceBox<Show> bookSeatsShow;
    @FXML
    public ChoiceBox<Performance> bookSeatsPerformance;
    @FXML
    public ChoiceBox<String> bookSeatsSection;
    @FXML
    public TextField bookSeatsNumSeats;
    @FXML
    public Label bookSeatsResult;
    @FXML
    public TextField bookSeatsName;

    // Update Booking fields
    @FXML
    public ChoiceBox<Show> updateBookingShow;
    @FXML
    public ChoiceBox<Performance> updateBookingPerformance;
    @FXML
    public ChoiceBox<Booking> updateBookingBooking;

    // Theatre data fields
    @FXML
    public TreeView<String> theatreData;
    // General variables
    private TheatreLinkedList<Show> shows = new TheatreLinkedList<>();
    static Performance bookingPerformance;
    static String bookingName;
    static boolean isUpdatingBooking = false;
    static Booking booking;

    @FXML
    public void addShow() {
        // Checking if the fields are all valid
        if (addShowName.getText().equals("")
                || addShowBalconyPrice.getText().equals("")
                || addShowCirclePrice.getText().equals("")
                || addShowStallsPrice.getText().equals("")
                || addShowStartDate.getValue() == null
                || addShowEndDate.getValue() == null
				|| addShowRunTime.getText().equals("")) {
            addShowError.setText("Not all fields are set. Please fill out all the fields");
            return;
        }
        // Not for whole or.
        if (!(addShowStartDate.getValue().isBefore(addShowEndDate.getValue()) || addShowStartDate.getValue().isEqual(addShowEndDate.getValue()))) {
            addShowError.setText("Start date has to be before or the same as the end date");
            return;
        }
        var show = new Show(addShowName.getText(), addShowStartDate.getValue(), addShowEndDate.getValue(), Integer.parseInt(addShowRunTime.getText()),
                Integer.parseInt(addShowBalconyPrice.getText()), Integer.parseInt(addShowCirclePrice.getText()), Integer.parseInt(addShowStallsPrice.getText()));
        shows.addFront(show);
        addShowName.clear();
        addShowBalconyPrice.clear();
        addShowCirclePrice.clear();
        addShowStallsPrice.clear();
		addShowRunTime.clear();
        addShowStartDate.getEditor().clear();
        addShowEndDate.getEditor().clear();
        addShowError.setText("");
    }

    @FXML
    public void addPerformance() {
                // Date checking done with help of https://stackoverflow.com/questions/494180/java-how-do-i-check-if-a-date-is-within-a-certain-range
                if (!(addPerformanceDate.getValue().isBefore(addPerformanceShow.getValue().getStartDate()) || addPerformanceDate.getValue().isAfter(addPerformanceShow.getValue().getEndDate()))) {
                    // If adding the show succeeded
                    if (addPerformanceShow.getValue().addPerformance(addPerformanceDate.getValue(), addPerformanceTime.getValue())) {
                        addPerformanceError.setText("");
                        addPerformanceDate.getEditor().clear();
                    } else {
                        addPerformanceError.setText("Performance already exists at this date and time.");
                    }
                } else {
                    addPerformanceError.setText("Performance isn't within the range of dates for show: " + addPerformanceShow.getValue().toString());
                }
    }

    @FXML
    public void addPerformanceStart() {
        addPerformanceShow.getItems().clear();
        for (Show show : shows) {
            addPerformanceShow.getItems().add(show);
        }
        addPerformanceTime.getItems().clear();
        addPerformanceTime.getItems().add("matinee");
        addPerformanceTime.getItems().add("evening");
    }

    @FXML
    public void bookSeatsUpdatePerformances() {
        if(bookSeatsShow.getValue() == null){
            return;
        }
        updateChoiceBox(bookSeatsPerformance, bookSeatsShow.getValue().getPerformances());
    }

    // Update date in choicebox based on another choicebox
    private <T> void updateChoiceBox(ChoiceBox<T> choiceBox, TheatreLinkedList<T> dataList){
        choiceBox.getItems().clear();
        for (var data:dataList) {
            choiceBox.getItems().add(data);
        }
    }

    @FXML
    public void deleteItem() {
        var currentItem = theatreData.getSelectionModel().getSelectedItem();
        var chosenShow = new Show();
        // The root item isnt a specific show or anything
        // Without this it would try to delete it and raise an error
        if (currentItem.equals(theatreData.getRoot())) {
            return;
        }
        var showItem = currentItem;
        // Go through each parent of the current item until it finds the root
        // Show item makes searching more efficient
        while (!showItem.getParent().equals(theatreData.getRoot())) {
            showItem = showItem.getParent();
        }
        for (var show : shows) {
            if (showItem.getValue().equals(show.toString())) {
                chosenShow = show;
            }
        }
        if(currentItem.equals(showItem)){
            // Checking if its the show itself
            shows.removeItem(chosenShow);
        }
        for (var item : theatreData.getRoot().getChildren()) {
            if (item.equals(showItem)) {
                for (var performanceItem : item.getChildren()) {
                    // If the selected item is a performance
                    if (performanceItem.equals(currentItem)) {
                        chosenShow.deletePerformance(currentItem.getValue());
                    } else {
                        for (var bookingItem : performanceItem.getChildren()) {
                            if (bookingItem.equals(currentItem)) {
                                for (var performance : chosenShow.getPerformances()) {
                                    if (performance.toString().equals(performanceItem.getValue())) {
                                        performance.deleteBooking(currentItem.getValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // Update list afterwards
        updateTheatreData();
    }

    @FXML
    public void bookSeatsStart(){
        bookSeatsShow.getItems().clear();
        for (Show show : shows) {
            bookSeatsShow.getItems().add(show);
        }
        bookSeatsSection.getItems().clear();
        bookSeatsSection.getItems().add("balcony");
        bookSeatsSection.getItems().add("circle");
        bookSeatsSection.getItems().add("stalls");
    }

    // created using help from https://docs.oracle.com/javafx/2/ui_controls/tree-view.htm
    @FXML
    public void updateTheatreData() {
        var rootNode = new TreeItem<>("Shows");
        for (Show show : shows) {
            var showItem = new TreeItem<>(show.toString());
            for (Performance performance : show.getPerformances()) {
                var performanceItem = new TreeItem<>(performance.toString());
                for (Booking booking : performance.getBookings()) {
                    var bookingItem = new TreeItem<>(booking.toString());
                    performanceItem.getChildren().add(bookingItem);
                }
                showItem.getChildren().add(performanceItem);
            }
            rootNode.getChildren().add(showItem);
        }
        theatreData.setRoot(rootNode);
    }

    @FXML
    public void findContinuousSeats() {
        var continuousSeats = new TheatreLinkedList<Seat>();
        continuousSeats = bookSeatsPerformance.getValue().getSeatArrangement().findContinuousSeats(bookSeatsSection.getValue().charAt(0), Integer.parseInt(bookSeatsNumSeats.getText()));
        bookSeatsResult.setText("Seats found: ");
        for (var seat : continuousSeats) {
            bookSeatsResult.setText(bookSeatsResult.getText() + seat.toString() + ", ");
        }
       // Give a helpful message if unsuccesful
        if(bookSeatsResult.getText().equals("Seats found: ")){
            bookSeatsResult.setText("No set of continuous seats found");
        }
    }

    @FXML
    public void updateBookingUpdatePerformances(){
        // To prevent a null pointer
        if(updateBookingShow.getValue() == null){
            return;
        }
        updateChoiceBox(updateBookingPerformance, updateBookingShow.getValue().getPerformances());
    }
    @FXML
    public void updateBookingUpdateBookings(){
        // To prevent a null pointer
        if(updateBookingPerformance.getValue() == null){
            return;
        }
        updateChoiceBox(updateBookingBooking, updateBookingPerformance.getValue().getBookings());
    }

    @FXML
    public void updateBookingStart(){
        updateBookingShow.getItems().clear();
        for(var show: shows){
            updateBookingShow.getItems().add(show);
        }
        updateBookingPerformance.getItems().clear();
        updateBookingBooking.getItems().clear();
    }

    @FXML
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("shows.xml"));
        shows = (TheatreLinkedList<Show>) is.readObject();
        is.close();
    }

    @FXML
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("shows.xml"));
        out.writeObject(shows);
        out.close();
    }

    @FXML
    public void reset() {
        shows = new TheatreLinkedList<>();
    }

    @FXML
    public void bookSeats() throws Exception {
        bookingName = bookSeatsName.getText();
        bookingPerformance = bookSeatsPerformance.getValue();
		isUpdatingBooking = false;
        launchSeatGrid();
    }
    private void launchSeatGrid() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("seatgrid.fxml"));
        Scene scene = new Scene(root, 800, 800);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void updateBooking() throws Exception{
		isUpdatingBooking = true;
		bookingPerformance = updateBookingPerformance.getValue();
		booking = updateBookingBooking.getValue();
        launchSeatGrid();
    }


}
