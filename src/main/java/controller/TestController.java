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

    public static Patient currentPatient = null;   // used to store the current patient who's details will be display
    //public static Patient currentPatient = new Patient(1,"Joshua", "Turner", LocalDate.of(1994, 01, 16), "Male", "0427 644 922"); //used for testing purposes

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

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    void initialize(){ 

        testIdCol.setCellValueFactory(new PropertyValueFactory<>("covidTestID"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("testDate"));
        resultCol.setCellValueFactory(new PropertyValueFactory<>("testResult"));
        methodCol.setCellValueFactory(new PropertyValueFactory<>("testMethod"));

        try{
            patientIdLabel.setText(String.valueOf(currentPatient.getPatientID()));
            patientNameLabel.setText(currentPatient.getFirstName() + " " + currentPatient.getLastName());
            dateOfBirthLabel.setText(dateFormatter.format(currentPatient.getDateOfBirth()));
            phoneLabel.setText(currentPatient.getPhoneNbr());
            genderLabel.setText(currentPatient.getGender());
        }catch(Exception e){
            MessageView.displayException(e, "Error displaying patient details");
        }

        refreshTestTable();
    }

    @FXML
    void addTestBtnAction(ActionEvent event) {
        MessageView.displayNewTestDialog(currentPatient.getPatientID());

        refreshTestTable();
    }

    @FXML
    void editTestBtnAction(ActionEvent event) {
        try{
            CovidTest selectedTest = testTabView.getSelectionModel().getSelectedItem();
            //CovidTest selectedTest = new CovidTest(1, LocalDate.of(2022, 04, 30), "PCR", "Positive", 1); //used for testing purposes
            MessageView.displayEditTestDialog(selectedTest);
            
        }catch(Exception e){
            MessageView.displayException(e, "No test selected");
        }

        refreshTestTable();
    }

    @FXML
    void exitBtnAction(ActionEvent event) {
        MessageView.displayExitDialogCloseBtn(event);
    }

    private void refreshTestTable(){

        try{
            ObservableList<CovidTest> testList = CovidTestModel.getTestListFromDB(
                                                                    currentPatient.getPatientID());

            if(testList.size() == 0)
                testTabView.setPlaceholder(new Label("No tests have been entered for this patient"));
            
            testTabView.setItems(testList);

        }catch(Exception e){
            MessageView.displayException(e, "Error loading test table");
        }
    }
}
