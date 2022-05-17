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
    private Button searchBtn;

    @FXML
    private TextField searchTxtField;

    @FXML
    private TableView<PatientTestStats> statTabView;
    
    @FXML
    private TableColumn<PatientTestStats, String> patNameCol;

    @FXML
    private TableColumn<PatientTestStats, Integer> totTestCol;

    @FXML
    private TableColumn<PatientTestStats, Integer> posTestCol;
    
    @FXML
    private TableColumn<PatientTestStats, Integer> negTestCol;

    @FXML
    private Button exitBtn;

    @FXML
    void initialize(){ 
        patNameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        totTestCol.setCellValueFactory(new PropertyValueFactory<>("totalTests"));
        posTestCol.setCellValueFactory(new PropertyValueFactory<>("totalPosResults"));
        negTestCol.setCellValueFactory(new PropertyValueFactory<>("totalNegResults"));

        refreshStatsTable();
    }

    @FXML
    void exitBtnAction(ActionEvent event) {
        MessageView.displayExitDialogCloseBtn(event);
    }

    @FXML
    void searchBtnAction(ActionEvent event) {
        try{
            ObservableList<PatientTestStats> statsList = 
                PatientTestStatsModel.getStatSearchResultsFromDB(searchTxtField.getText());

            if(statsList.size() == 0)
                statTabView.setPlaceholder(new Label("No patients found"));

            statTabView.setItems(statsList);

        }catch(Exception e){
            MessageView.displayException(e, "Error loading search results");
        }
    }

    private void refreshStatsTable(){

        try{
            ObservableList<PatientTestStats> statsList = PatientTestStatsModel.getTestStatListFromDB();

            if(statsList.size() == 0)
                statTabView.setPlaceholder(new Label("No patients have been entered into the database"));

            statTabView.setItems(statsList);

        }catch(Exception e){
            MessageView.displayException(e, "Error loading statistics table");
        }
    }
}
