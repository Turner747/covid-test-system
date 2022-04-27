package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.PatientTestStats;

public class StatisticsController {

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTxtField;

    @FXML
    private TableView<PatientTestStats> statTabView;
    
    @FXML
    private TableColumn<PatientTestStats, ?> patNameCol;

    @FXML
    private TableColumn<PatientTestStats, ?> totTestCol;

    @FXML
    private TableColumn<PatientTestStats, ?> posTestCol;
    
    @FXML
    private TableColumn<PatientTestStats, ?> negTestCol;

    @FXML
    private Button exitBtn;

    @FXML
    void exitBtnAction(ActionEvent event) {

    }

    @FXML
    void searchBtnAction(ActionEvent event) {

    }

}
