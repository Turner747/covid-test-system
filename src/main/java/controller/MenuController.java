// programmer: Joshua Turner
// student id: s0258441
// purpose: COIT12200
// Assessment: Assessment 2
// Date: 20 May 2022

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
        MessageView.displayAboutDialog();
    }

    @FXML
    void exitBtnAction(ActionEvent event) {
        MessageView.displayExitDialogCloseBtn(event);
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
