package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.MessageView;

public class MenuController {

    @FXML
    private Button aboutBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button patientBtn;

    @FXML
    private Button statisticsBtn;

    @FXML
    void aboutBtnAction(ActionEvent event) {

    }

    @FXML
    void exitBtnAction(ActionEvent event) {
        MessageView.displayExitDialog(event);
    }

    @FXML
    void patientBtnAction(ActionEvent event) {
        App.openNewWindow("PatientView", "Patients");
    }

    @FXML
    void statisticsBtnAction(ActionEvent event) {
        App.openNewWindow("StatisticsView", "Test Statistics");
    }

}
