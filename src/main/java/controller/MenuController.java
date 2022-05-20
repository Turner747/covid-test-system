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
    private Button aboutBtn;    // about button

    @FXML
    private Button exitBtn;     // exit button

    @FXML
    private Button patientBtn;     // patient button

    @FXML
    private Button statisticsBtn;   // statistics button

    // action taken when about button is clicked
    @FXML
    void aboutBtnAction(ActionEvent event) {
        MessageView.displayAboutDialog();
    }

    // action taken when exit button is clicked
    @FXML
    void exitBtnAction(ActionEvent event) {
        MessageView.displayExitDialogCloseBtn(event);
    }

    // action taken when patient button is clicked
    @FXML
    void patientBtnAction(ActionEvent event) {
        App.openNewWindow("PatientView", "Patients");
    }

    // action taken when statistics button is clicked
    @FXML
    void statisticsBtnAction(ActionEvent event) {
        App.openNewWindow("StatisticsView", "Test Statistics");
    }

}
