// programmer: Joshua Turner
// student id: s0258441
// purpose: COIT12200
// Assessment: Assessment 2
// Date: 20 May 2022

package controller;

import java.lang.ref.Reference;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Patient;
import model.PatientModel;
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
    void initialize(){ 
        
        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNbr"));

        refreshPatientTable();
    }

    @FXML
    void addBtnAction(ActionEvent event) {
        MessageView.displayNewPatientDialog();

        refreshPatientTable();
    }

    @FXML
    void editBtnAction(ActionEvent event) {

        try{
            Patient selectedPatient = patientTabView.getSelectionModel().getSelectedItem();
            //Patient selectedPatient = new Patient(5,"Joshua", "Turner", LocalDate.of(1994, 01, 16), "Male", "0427 644 922"); //used for testing purposes
            MessageView.displayEditPatientDialog(selectedPatient);
            
        }catch(Exception e){
            MessageView.displayException(e, "No patient selected");
        }
        refreshPatientTable();
    }

    @FXML
    void exitBtnAction(ActionEvent event) {
        MessageView.displayExitDialogCloseBtn(event);
    }

    @FXML
    void searchBtnAction(ActionEvent event) {

        try{
            ObservableList<Patient> patientList = 
                PatientModel.getSearchResultsFromDB(searchTxtField.getText());


            if(patientList.size() == 0)
               patientTabView.setPlaceholder(new Label("No patients found"));
                
            patientTabView.setItems(patientList);
            

        }catch(Exception e){
            MessageView.displayException(e, "Error loading search results");
        }

    }

    @FXML
    void viewBtnAction(ActionEvent event) {

        try{
            Patient selectedPatient = patientTabView.getSelectionModel().getSelectedItem();
            //Patient selectedPatient = new Patient(1,"Joshua", "Turner", LocalDate.of(1994, 01, 16), "Male", "0427 644 922"); //used for testing purposes
            TestController.currentPatient = selectedPatient;
            App.openNewWindow("TestView", selectedPatient.getFirstName() + " " +
                                                selectedPatient.getLastName() + " - Tests");
        }catch(Exception e){
            MessageView.displayException(e, "No patient selected");
        }
        refreshPatientTable();
    }

    private void refreshPatientTable(){

        try{
            ObservableList<Patient> patientList = PatientModel.getPatientListFromDB();

            if(patientList.size() == 0)
               patientTabView.setPlaceholder(new Label("No patients have been entered into the database"));

            patientTabView.setItems(patientList);

        }catch(Exception e){
            MessageView.displayException(e, "Error loading patient table");
        }
    }
}
