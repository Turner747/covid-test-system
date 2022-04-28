package controller;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.CovidTest;
//import model.Patient;
import view.MessageView;

public class TestController {

    //public static Patient currentPatient = null;   // used to store the current patient who's details will be display

    @FXML
    private Button addTestBtn;

    @FXML
    private Label dateOfBirthLabel;

    @FXML
    private Button editTestBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Label genderLabel;

    @FXML
    private Label patientNameLabel;

    @FXML
    private Label patientIdLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private TableView<CovidTest> testTabView;

    @FXML
    private TableColumn<CovidTest, Integer> testIdCol;

    @FXML
    private TableColumn<CovidTest, LocalDate> dateCol;

    @FXML
    private TableColumn<CovidTest, String> resultCol;

    @FXML
    private TableColumn<CovidTest, String> methodCol;

    @FXML
    void addTestBtnAction(ActionEvent event) {

    }

    @FXML
    void editTestBtnAction(ActionEvent event) {

    }

    @FXML
    void exitBtnAction(ActionEvent event) {
        MessageView.displayExitDialog(event);
    }

}
