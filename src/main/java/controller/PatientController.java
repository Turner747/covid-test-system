package controller;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Patient;
import view.MessageView;

public class PatientController {

    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TableView<Patient> patientTabView;

    @FXML
    private TableColumn<Patient, Integer> patientIdCol;

    @FXML
    private TableColumn<Patient, String> firstNameCol;

    @FXML
    private TableColumn<Patient, String> lastNameCol;

    @FXML
    private TableColumn<Patient, LocalDate> dateOfBirthCol;
    
    @FXML
    private TableColumn<Patient, String> genderCol;

    @FXML
    private TableColumn<Patient, String> phoneCol;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchTxtField;

    @FXML
    private Button viewBtn;

    @FXML
    void addBtnAction(ActionEvent event) {

    }

    @FXML
    void editBtnAction(ActionEvent event) {

    }

    @FXML
    void exitBtnAction(ActionEvent event) {
        MessageView.displayExitDialog(event);
    }

    @FXML
    void searchBtnAction(ActionEvent event) {

    }

    @FXML
    void viewBtnAction(ActionEvent event) {

        try{
            Patient selectedPatient = patientTabView.getSelectionModel().getSelectedItem();
            //TestController.currentPatient = selectedPatient;
            App.openNewWindow("TestView", selectedPatient.getFirstName() + " " +
                                                selectedPatient.getLastName() + " - Tests");
        }catch(Exception e){
            MessageView.displayException(e, "No patient selected");
        }
    }

}
