package Driver;

import Theatre.Booking;
import Theatre.Performance;
import Theatre.Show;
import Utilities.TheatreLinkedList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    // Theatre data fields
    @FXML
    public TreeView<String> theatreData;
    // General variables
    private TheatreLinkedList<Show> shows = new TheatreLinkedList<>();

    @FXML
    public void addShow() {
        // Checking if the fields are all valid
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

    @FXML
    public void bookSeatsUpdatePerformances(){

    }

    @FXML
    public void bookSeats(){

    }

    @FXML
    public void deleteShow(){

    }

    @FXML
    public void deletePerformance(){

    }

    @FXML
    public void deleteBooking(){

    }

    @FXML
    public void deleteItem(){
        var currentItem = theatreData.getSelectionModel().getSelectedItem();
        // Needed to prevent an exception
        if(currentItem.equals(theatreData.getRoot())){
           return;
        }
        var showItem = currentItem;
        // Go through each parent of the current item until it finds the root
        // Show item makes searching more efficient
        while(!showItem.getParent().equals(theatreData.getRoot())){
           showItem = showItem.getParent();
        }
        //Checking if its the show itself
        if(currentItem.equals(showItem)) {
            for (var show:shows) {
                if(show.toString().equals(showItem.getValue())){
                    shows.removeItem(show);
                    return;
                }
            }
        }
        for (var item:theatreData.getRoot().getChildren()) {
           if(item.equals(showItem)) {
               for (var performanceItem:item.getChildren()) {
                   // If the selected item is a performance
                   if(performanceItem.equals(currentItem)){
                       for (var show:shows) {
                          if(show.toString().equals(showItem.getValue())) {
                              show.deletePerformance(currentItem.getValue());
                          }
                       }
                   }
                   else{
                       for (var bookingItem:performanceItem.getChildren()) {
                          if(bookingItem.equals(currentItem)) {
                              for(var show:shows){
                                  if(show.toString().equals(showItem.getValue())){
                                      for (var performance:show.getPerformances()) {
                                         if(performance.toString().equals(performanceItem.getValue())) {
                                             performance.deleteBooking(currentItem.getValue());
                                         }
                                      }
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
    public void updateBooking(){

    }

    // created using help from https://docs.oracle.com/javafx/2/ui_controls/tree-view.htm
    @FXML
    public void updateTheatreData(){
        var rootNode = new TreeItem<String>("Shows");
        for (Show show:shows) {
           var showItem = new TreeItem<String>(show.toString());
            for (Performance performance: show.getPerformances()) {
                var performanceItem = new TreeItem<String>(performance.toString());
                for (Booking booking:performance.getBookings()) {
                    var bookingItem = new TreeItem<String>(booking.toString());
                    performanceItem.getChildren().add(bookingItem);
                }
                showItem.getChildren().add(performanceItem);
            }
            rootNode.getChildren().add(showItem);
        }
        theatreData.setRoot(rootNode);
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
    public void reset(){
        shows = new TheatreLinkedList<>();
    }



}
