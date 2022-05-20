// programmer: Joshua Turner
// student id: s0258441
// purpose: COIT12200
// Assessment: Assessment 2
// Date: 20 May 2022

package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CovidTest;
import model.Patient;
import model.CovidTestModel;
import view.MessageView;

public class TestController {

    public static Patient currentPatient = null;   // public object used to store the patient being displayed
    
    @FXML
    private Button addTestBtn;      // add test button

    @FXML
    private Label dateOfBirthLabel; // date of birth label

    @FXML
    private Button editTestBtn;     // edit test button

    @FXML
    private Button exitBtn;     // exit test button

    @FXML
    private Label genderLabel;  // gender label

    @FXML
    private Label patientNameLabel;     // patient name label

    @FXML
    private Label patientIdLabel;       // patient id label

    @FXML
    private Label phoneLabel;       // phone label

    @FXML
    private TableView<CovidTest> testTabView;   // test table

    @FXML
    private TableColumn<CovidTest, Integer> testIdCol;      // test id column

    @FXML
    private TableColumn<CovidTest, LocalDate> dateCol;      // date of test column

    @FXML
    private TableColumn<CovidTest, String> resultCol;       // result column

    @FXML
    private TableColumn<CovidTest, String> methodCol;       // method column

    private static DateTimeFormatter dateFormatter =    // date formatter for date of birth
        DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    void initialize(){ 

        // construct column
        testIdCol.setCellValueFactory(new PropertyValueFactory<>("covidTestID"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("testDate"));
        resultCol.setCellValueFactory(new PropertyValueFactory<>("testResult"));
        methodCol.setCellValueFactory(new PropertyValueFactory<>("testMethod"));

        try{    // display patient information
            patientIdLabel.setText(String.valueOf(currentPatient.getPatientID()));
            patientNameLabel.setText(currentPatient.getFirstName() + " " + currentPatient.getLastName());
            dateOfBirthLabel.setText(dateFormatter.format(currentPatient.getDateOfBirth()));
            phoneLabel.setText(currentPatient.getPhoneNbr());
            genderLabel.setText(currentPatient.getGender());
        }catch(Exception e){ // display error message
            MessageView.displayException(e, "Error displaying patient details");
        }

        refreshTestTable(); // fill test table
    }

    // action taken when add test button is clicked
    @FXML
    void addTestBtnAction(ActionEvent event) {
        MessageView.displayNewTestDialog(currentPatient.getPatientID()); //display new test dialog

        refreshTestTable(); // refresh test table with new test
    }

    // action taken when edit test button is clicked
    @FXML
    void editTestBtnAction(ActionEvent event) {
        try{
            CovidTest selectedTest = testTabView.getSelectionModel().getSelectedItem(); // get selected test
            
            MessageView.displayEditTestDialog(selectedTest); // display edit test dialog
            
        }catch(Exception e){    // display error message if no test selected
            MessageView.displayException(e, "No test selected");
        }

        refreshTestTable(); // update test table with updated test
    }

    // action taken when exit test button is clicked
    @FXML
    void exitBtnAction(ActionEvent event) {
        MessageView.displayExitDialogCloseBtn(event);
    }

    // get test for current patient patient and display them in the table
    private void refreshTestTable(){

        try{
            ObservableList<CovidTest> testList = CovidTestModel.getTestListFromDB(      // get list of tests
                                                                    currentPatient.getPatientID());

            if(testList.size() == 0)    // if no tests are found, show message
                testTabView.setPlaceholder(new Label("No tests have been entered for this patient"));
            
            testTabView.setItems(testList); // display tests in tabble

        }catch(Exception e){    // display error if database query fails
            MessageView.displayException(e, "Error loading test table");
        }
    }
}
