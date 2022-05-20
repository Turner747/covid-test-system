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
    private Button addBtn;      // add button

    @FXML
    private Button editBtn;     // edit button

    @FXML
    private Button exitBtn;     // exit button

    @FXML
    private TableView<Patient> patientTabView;      // patient table

    @FXML
    private TableColumn<Patient, Integer> patientIdCol;     // patient id column

    @FXML
    private TableColumn<Patient, String> firstNameCol;      // first name column

    @FXML
    private TableColumn<Patient, String> lastNameCol;       // last name column

    @FXML
    private TableColumn<Patient, LocalDate> dateOfBirthCol;     // date of birth column
    
    @FXML
    private TableColumn<Patient, String> genderCol;     // gender column

    @FXML
    private TableColumn<Patient, String> phoneCol;      // phone column

    @FXML
    private Button searchBtn;       // search button

    @FXML
    private TextField searchTxtField; // search field

    @FXML
    private Button viewBtn;     // view button

    @FXML
    void initialize(){ 
        
        // construct patient table view
        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNbr"));

        refreshPatientTable(); // populate table
    }

    // action taken when add button is clicked
    @FXML
    void addBtnAction(ActionEvent event) {
        MessageView.displayNewPatientDialog();

        refreshPatientTable();
    }

    // action taken when edit button is clicked
    @FXML
    void editBtnAction(ActionEvent event) {

        try{
            Patient selectedPatient = patientTabView.getSelectionModel().getSelectedItem(); // get selected patient
            MessageView.displayEditPatientDialog(selectedPatient);  // display edit patient window
            
        }catch(Exception e){
            MessageView.displayException(e, "No patient selected"); // display if no patient is selected
        }
        refreshPatientTable();      // refresh table to show update
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
            ObservableList<Patient> patientList = 
                PatientModel.getSearchResultsFromDB(searchTxtField.getText());      // search database with string from search field


            if(patientList.size() == 0)
               patientTabView.setPlaceholder(new Label("No patients found"));       // if the return list is empty display message in table
                
            patientTabView.setItems(patientList);   // update table with search results
            

        }catch(Exception e){
            MessageView.displayException(e, "Error loading search results");        // display is error occurs when searchihg database
        }

    }

    // action taken when view button is clicked
    @FXML
    void viewBtnAction(ActionEvent event) {

        try{
            Patient selectedPatient = patientTabView.getSelectionModel().getSelectedItem();     // get selected patient
            
            TestController.currentPatient = selectedPatient;    // pass the selected patient to the test controller
            App.openNewWindow("TestView", selectedPatient.getFirstName() + " " +
                                                selectedPatient.getLastName() + " - Tests");    // open test window
        }catch(Exception e){
            MessageView.displayException(e, "No patient selected"); // display is no patient is selected
        }
    }

    // gets list of patient objects and displays them in the patient table
    private void refreshPatientTable(){

        try{
            ObservableList<Patient> patientList = PatientModel.getPatientListFromDB(); // get patients from database

            if(patientList.size() == 0)     // if the return list is empty display message in table
               patientTabView.setPlaceholder(new Label("No patients have been entered into the database"));

            patientTabView.setItems(patientList); // display patients in table

        }catch(Exception e){
            MessageView.displayException(e, "Error loading patient table"); // show error message
        }
    }
}
