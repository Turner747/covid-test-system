package view;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.CovidTest;
import model.Patient;
import model.PatientModel;
import model.CovidTestModel;
import controller.App;

public class MessageView {

    /**
     * display an error dialog for input errors
     * @param message contains the string to be displayed in the dialog
     */
    public static void displayInfoMessage(String message)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        a.setTitle("Covid Test System");
        a.setContentText(message);
        a.show();
    }


    public static Alert displayDBConnection(String message)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        a.setTitle("Covid Test System Database");
        a.setHeaderText("Database");
        a.setContentText(message);
        
        return a;
    }

    
    /**
     * Display the close window dialog with close window and quit application options
     * @param e is the event used to display this window
     */
    public static void displayExitDialog(Event e){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, 
                    "Are you sure you want to quit?", 
                    ButtonType.YES,
                    ButtonType.NO);
        a.setTitle("Close Program");
        a.setContentText("Would you like to close this window or quit the application?");
        
        ButtonType closeButton = new ButtonType("Close this window");
        ButtonType closeAllButton = new ButtonType("Quit application");
        //ButtonType cancelButton = new ButtonType("Cancel". ButtonData.CANCEL_CLOSE);
        
        a.getButtonTypes().setAll(closeButton, closeAllButton, ButtonType.CANCEL);
        
        Optional<ButtonType> confirm = a.showAndWait();
        if (confirm.isPresent() && confirm.get() == closeButton){
            // close the window
            Node node = (Node) e.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();
            currentStage.close();
        }
        else if (confirm.isPresent() && confirm.get() == closeAllButton){
            try{
                App.conn.close();
            }catch (Exception ex){
                displayException(ex, "Error closing connection to database");
            }
            System.exit(0);
        }
        else {
            e.consume();
        }         
    }

    /**
     * display an error dialog for input errors
     * @param message contains the string to be displayed in the dialog
     */
    public static void displayError(String message){
        Alert a = new Alert(Alert.AlertType.ERROR, message);
        
        a.setTitle("Input Error");
        a.setHeaderText("Covid Test System Error");
        a.show();
    }

    /**
     * display stack trace exception dialog
     * @param throwable the exception thrown
     * @param message unique message for how the error occurred
     */
    public static void displayException(Throwable throwable, String message) 
    {
        // print the stack trace to the console
        //throwable.printStackTrace();

        // create aleart window and set titles
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Covid Test System Exception");
        alert.setHeaderText("Exception Thrown");
        alert.setContentText(message);

        // create stack trace string
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Stacktrace details:");

        // create text area and add stacktrace string
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        
        // set size and behaviour of text area
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        // create new gridpane and add the label and text area
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // add the above gridpane to the alert window
        alert.getDialogPane().setExpandableContent(expContent);

        // display the alert
        alert.showAndWait();
    }

    /**
     * Display the add patient dialog window
     */
    public static void displayNewPatientDialog(){
        Patient newPatient = null;      // create local object
        
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle("Covid Test System - Add Patient");      // add title
        dialog.setHeaderText("Create Patient");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Add", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("Enter first name");

        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("Enter last name");

        DatePicker dobDatePicker = new DatePicker();
        dobDatePicker.setPromptText("Date of birth");
        
        TextField phoneTextField = new TextField();
        phoneTextField.setPromptText("Enter phone number");
        
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.setPromptText("Select gender");
        ArrayList<String> gender = new ArrayList<String>();
        gender.add("Male");
        gender.add("Female");
        gender.add("Other");
        ObservableList<String> genderList = FXCollections.observableList(gender);
        genderComboBox.setItems(genderList);

        
        // add labels and textfields to the gridpane
        grid.add(new Label("First name"), 0, 0);
        grid.add(firstNameTextField, 1, 0);
        grid.add(new Label("*"), 2, 0);
        
        grid.add(new Label("Last name"), 0, 1);
        grid.add(lastNameTextField, 1, 1);
        grid.add(new Label("*"), 2, 1);        
        
        grid.add(new Label("Date of birth"), 0, 2);
        grid.add(dobDatePicker, 1, 2);
        grid.add(new Label("*"), 2, 2);
        
        grid.add(new Label("Phone"), 0, 3);
        grid.add(phoneTextField, 1, 3);
        grid.add(new Label("*"), 2, 3);

        grid.add(new Label("Gender"), 0, 4);
        grid.add(genderComboBox, 1, 4);
        grid.add(new Label("*"), 2, 4);
        
        grid.add(new Label("* Required fields"), 1, 6);

        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);

        add.disableProperty().bind(
                firstNameTextField.textProperty().isEmpty().
                or(lastNameTextField.textProperty().isEmpty()).
                or(dobDatePicker.valueProperty().isNull()).
                or(phoneTextField.textProperty().isEmpty()).
                or(genderComboBox.valueProperty().isNull()));
        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //add input validation

            //event.consume();
        });
        
        // Request focus on the date field by default.
        Platform.runLater(() -> firstNameTextField.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            // attempt to create object
            try{
                // instantiate temporary object
                newPatient = new Patient(
                        firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        dobDatePicker.getValue(),
                        genderComboBox.getSelectionModel().getSelectedItem(),
                        phoneTextField.getText()
                        );

                PatientModel.addPatientToDB(newPatient);

            } // if incorrect data type has been entered an exception will be thrown
            catch(Exception e)
            {
                // create error message
                String message = "Add patient error";

                // display the exception error window
                displayException(e, message);
            }
        }
    }

    /**
     * Display the edit patient dialog window
     */
    public static void displayEditPatientDialog(Patient patient){
        
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle("Covid Test System - Edit Patient");      // add title
        dialog.setHeaderText("Update Patient");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Update", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        TextField firstNameTextField = new TextField();
        firstNameTextField.setText(patient.getFirstName());

        TextField lastNameTextField = new TextField();
        lastNameTextField.setText(patient.getLastName());

        DatePicker dobDatePicker = new DatePicker();
        dobDatePicker.setValue(patient.getDateOfBirth());
        
        TextField phoneTextField = new TextField();
        phoneTextField.setText(patient.getPhoneNbr());
        
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.setPromptText("Select gender");
        ArrayList<String> gender = new ArrayList<String>();
        gender.add("Male");
        gender.add("Female");
        gender.add("Other");
        ObservableList<String> genderList = FXCollections.observableList(gender);
        genderComboBox.setItems(genderList);
        genderComboBox.getSelectionModel().select(patient.getGender());

        
        // add labels and textfields to the gridpane
        grid.add(new Label("First name"), 0, 0);
        grid.add(firstNameTextField, 1, 0);
        grid.add(new Label("*"), 2, 0);
        
        grid.add(new Label("Last name"), 0, 1);
        grid.add(lastNameTextField, 1, 1);
        grid.add(new Label("*"), 2, 1);        
        
        grid.add(new Label("Date of birth"), 0, 2);
        grid.add(dobDatePicker, 1, 2);
        grid.add(new Label("*"), 2, 2);
        
        grid.add(new Label("Phone"), 0, 3);
        grid.add(phoneTextField, 1, 3);
        grid.add(new Label("*"), 2, 3);

        grid.add(new Label("Gender"), 0, 4);
        grid.add(genderComboBox, 1, 4);
        grid.add(new Label("*"), 2, 4);
        
        grid.add(new Label("* Required fields"), 1, 6);

        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);

        add.disableProperty().bind(
                firstNameTextField.textProperty().isEmpty().
                or(lastNameTextField.textProperty().isEmpty()).
                or(dobDatePicker.valueProperty().isNull()).
                or(phoneTextField.textProperty().isEmpty()).
                or(genderComboBox.valueProperty().isNull()));
        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //add input validation

            //event.consume();
        });
        
        // Request focus on the date field by default.
        Platform.runLater(() -> firstNameTextField.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            // attempt to create object
            try{
                // instantiate temporary object
                patient.setFirstName(firstNameTextField.getText());
                patient.setLastName(lastNameTextField.getText());
                patient.setDateOfBirth(dobDatePicker.getValue());
                patient.setPhoneNbr(phoneTextField.getText());
                patient.setGender(genderComboBox.getSelectionModel().getSelectedItem());

                PatientModel.updatePatientInDB(patient);

            } // if incorrect data type has been entered an exception will be thrown
            catch(Exception e)
            {
                // create error message
                String message = "Edit patient error";

                // display the exception error window
                displayException(e, message);
            }
        }
    }

    /**
     * Display the add test dialog window
     */
    public static void displayNewTestDialog(int patientId){
        CovidTest newTest = null;      // create local object
        
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle("Covid Test System - Edit Test");      // add title
        dialog.setHeaderText("Update Test");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Add", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        DatePicker testDatePicker = new DatePicker();
        testDatePicker.setPromptText("Select date");
        testDatePicker.setValue(LocalDate.now());
        
        ComboBox<String> methodComboBox = new ComboBox<>();
        methodComboBox.setPromptText("Select method");
        ArrayList<String> tests = new ArrayList<String>();
        tests.add("RAT");
        tests.add("PCR");
        ObservableList<String> testList = FXCollections.observableList(tests);
        methodComboBox.setItems(testList);

        ComboBox<String> resultComboBox = new ComboBox<>();
        resultComboBox.setPromptText("Select result");
        ArrayList<String> results = new ArrayList<String>();
        results.add("Positive");
        results.add("Negative");
        ObservableList<String> resultList = FXCollections.observableList(results);
        resultComboBox.setItems(resultList);

        
        // add labels and textfields to the gridpane
        grid.add(new Label("Test Date"), 0, 0);
        grid.add(testDatePicker, 1, 0);
        grid.add(new Label("*"), 2, 0);
        
        grid.add(new Label("Testing method"), 0, 1);
        grid.add(methodComboBox, 1, 1);
        grid.add(new Label("*"), 2, 1);        
        
        grid.add(new Label("Test result"), 0, 2);
        grid.add(resultComboBox, 1, 2);
        grid.add(new Label("*"), 2, 2);
        
        grid.add(new Label("* Required fields"), 1, 4);

        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);
                     
        add.disableProperty().bind(
                testDatePicker.valueProperty().isNull().
                or(methodComboBox.valueProperty().isNull()).
                or(resultComboBox.valueProperty().isNull()));
        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //add input validation

            //event.consume();
        });
        
        // Request focus on the test method field by default.
        Platform.runLater(() -> methodComboBox.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            // attempt to create object
            try{
                // instantiate temporary object
                newTest = new CovidTest(
                        testDatePicker.getValue(),
                        methodComboBox.getSelectionModel().getSelectedItem(),
                        resultComboBox.getSelectionModel().getSelectedItem(),
                        patientId
                        );

                CovidTestModel.addTestToDB(newTest);

            } // if incorrect data type has been entered an exception will be thrown
            catch(Exception e)
            {
                // create error message
                String message = "Add test error";

                // display the exception error window
                displayException(e, message);
            }
        }
    }

    /**
     * Display the edit test dialog window
     */
    public static void displayEditTestDialog(CovidTest test){
        CovidTest newTest = null;      // create local object
        
        Dialog dialog = new Dialog<>();     // new dialog pane
        
        dialog.setTitle("Covid Test System - New Test");      // add title
        dialog.setHeaderText("Add Test");     // add header
        
        ButtonType addInputButton = new         // initialise add button
                ButtonType("Update", ButtonData.OK_DONE);
        
        dialog.getDialogPane().getButtonTypes().        // Set the button types
                addAll(addInputButton, ButtonType.CANCEL);

        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 30, 30));

        // create the input fields
        DatePicker testDatePicker = new DatePicker();
        testDatePicker.setPromptText("Select date");
        testDatePicker.setValue(test.getTestDate());
        
        ComboBox<String> methodComboBox = new ComboBox<>();
        methodComboBox.setPromptText("Select method");
        ArrayList<String> tests = new ArrayList<String>();
        tests.add("RAT");
        tests.add("PCR");
        ObservableList<String> testList = FXCollections.observableList(tests);
        methodComboBox.setItems(testList);
        methodComboBox.getSelectionModel().select(test.getTestMethod());

        ComboBox<String> resultComboBox = new ComboBox<>();
        resultComboBox.setPromptText("Select result");
        ArrayList<String> results = new ArrayList<String>();
        results.add("Positive");
        results.add("Negative");
        ObservableList<String> resultList = FXCollections.observableList(results);
        resultComboBox.setItems(resultList);
        resultComboBox.getSelectionModel().select(test.getTestResult());
        
        // add labels and textfields to the gridpane
        grid.add(new Label("Test Date"), 0, 0);
        grid.add(testDatePicker, 1, 0);
        grid.add(new Label("*"), 2, 0);
        
        grid.add(new Label("Testing method"), 0, 1);
        grid.add(methodComboBox, 1, 1);
        grid.add(new Label("*"), 2, 1);        
        
        grid.add(new Label("Test result"), 0, 2);
        grid.add(resultComboBox, 1, 2);
        grid.add(new Label("*"), 2, 2);
        
        grid.add(new Label("* Required fields"), 1, 4);

        dialog.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        // disable add button if all required inputs have not been entered
        Button add = (Button) dialog.getDialogPane().
                                lookupButton(addInputButton);
                     
        add.disableProperty().bind(
                testDatePicker.valueProperty().isNull().
                or(methodComboBox.valueProperty().isNull()).
                or(resultComboBox.valueProperty().isNull()));
        
        // validate inputs
        add.addEventFilter(ActionEvent.ACTION, event -> 
        {
            //add input validation

            //event.consume();
        });
        
        // Request focus on the date field by default.
        Platform.runLater(() -> testDatePicker.requestFocus());
        
        // display the dialog and wait for a button to be pressed
        Optional<ButtonType> result = dialog.showAndWait();
        
        // if the add button is pressed
        if(result.isPresent() && result.get() == addInputButton)
        {
            // attempt to create object
            try{
                // instantiate temporary object
                test.setTestDate(testDatePicker.getValue());
                test.setTestMethod(methodComboBox.getSelectionModel().getSelectedItem());
                test.setTestResult(resultComboBox.getSelectionModel().getSelectedItem());
                
                CovidTestModel.updateTestInDB(test);

            } // if incorrect data type has been entered an exception will be thrown
            catch(Exception e)
            {
                // create error message
                String message = "Update test error";

                // display the exception error window
                displayException(e, message);
            }
        }
    }

    /**
     * Display about window
     */
    public static void displayAboutDialog(){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        
        a.setTitle("Covid Test System");
        a.setHeaderText("About");
        
        // create the grid padding and set the sizes
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 20, 30));
        
        // add labels and textfields to the gridpane
        grid.add(new Label("Developed by:"), 0, 0);
        grid.add(new Label("Joshua Turner"), 1, 0);
        
        grid.add(new Label("Student Number:"), 0, 1);
        grid.add(new Label("s0258441"), 1, 1);        
        
        grid.add(new Label("Version Number:"), 0, 2);
        grid.add(new Label("0"), 1, 2);
        
        grid.add(new Label("Version Date:"), 0, 3);
        grid.add(new Label("TBA"), 1, 3);
        
        a.getDialogPane().setContent(grid);    // add the grid to the dialog
        
        a.show();
    }
}
