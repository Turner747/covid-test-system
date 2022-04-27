package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Patient;

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
    private TableColumn<Patient, ?> firstNameCol;

    @FXML
    private TableColumn<Patient, ?> lastNameCol;

    @FXML
    private TableColumn<Patient, ?> dateOfBirthCol;
    
    @FXML
    private TableColumn<Patient, ?> genderCol;

    @FXML
    private TableColumn<Patient, ?> phoneCol;

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

    }

    @FXML
    void searchBtnAction(ActionEvent event) {

    }

    @FXML
    void viewBtnAction(ActionEvent event) {
        Patient selectedPatient = patientTabView.getSelectionModel().getSelectedItem();
        //TestController.currentPatient = selectedPatient;
        App.openNewWindow("TestView", selectedPatient.getFirstName() + " " +
                                            selectedPatient.getLastName() + " - Tests");
    }

}
