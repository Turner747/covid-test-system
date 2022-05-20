// programmer: Joshua Turner
// student id: s0258441
// purpose: COIT12200
// Assessment: Assessment 2
// Date: 20 May 2022

package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.PatientTestStats;
import model.PatientTestStatsModel;
import view.MessageView;

public class StatisticsController {

    @FXML
    private Button searchBtn;       // search button

    @FXML
    private TextField searchTxtField; // search text field

    @FXML
    private TableView<PatientTestStats> statTabView; // statistics table
    
    @FXML
    private TableColumn<PatientTestStats, String> patNameCol; // name column

    @FXML
    private TableColumn<PatientTestStats, Integer> totTestCol; // total test column

    @FXML
    private TableColumn<PatientTestStats, Integer> posTestCol; // positive test column
    
    @FXML
    private TableColumn<PatientTestStats, Integer> negTestCol; // negative test column

    @FXML
    private Button exitBtn; // exit button

    @FXML
    void initialize(){ 
        // construct table
        patNameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        totTestCol.setCellValueFactory(new PropertyValueFactory<>("totalTests"));
        posTestCol.setCellValueFactory(new PropertyValueFactory<>("totalPosResults"));
        negTestCol.setCellValueFactory(new PropertyValueFactory<>("totalNegResults"));

        refreshStatsTable(); // fill table with all statistics
    }

    // action taken when exit button is clicked
    @FXML
    void exitBtnAction(ActionEvent event) {
        MessageView.displayExitDialogCloseBtn(event);
    }

    // action taken when search button is clicked
    @FXML
    void searchBtnAction(ActionEvent event) {
        try{
            ObservableList<PatientTestStats> statsList =    // search database with string from search field
                PatientTestStatsModel.getStatSearchResultsFromDB(searchTxtField.getText());

            if(statsList.size() == 0)   // if no matching patient, display message
                statTabView.setPlaceholder(new Label("No patients found")); 

            statTabView.setItems(statsList); // update table with search results

        }catch(Exception e){    // display error if database search fails
            MessageView.displayException(e, "Error loading search results");
        }
    }

    // get all statistics from database and display them in the table
    private void refreshStatsTable(){

        try{
            ObservableList<PatientTestStats> statsList = PatientTestStatsModel.getTestStatListFromDB(); // get all stats from database

            if(statsList.size() == 0)   // if no stats, then display message
                statTabView.setPlaceholder(new Label("No patients have been entered into the database"));

            statTabView.setItems(statsList);  // fill table with stats

        }catch(Exception e){    // display error if database query fails
            MessageView.displayException(e, "Error loading statistics table");
        }
    }
}
