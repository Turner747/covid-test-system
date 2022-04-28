package view;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

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
        a.setContentText("Would you liek to close this window or quit the application?");
        
        ButtonType closeButton = new ButtonType("Close this window");
        ButtonType closeAllButton = new ButtonType("Quit application");
        //ButtonType cancelButton = new ButtonType("Cancel". ButtonData.CANCEL_CLOSE);
        
        a.getButtonTypes().setAll(closeButton, closeAllButton, ButtonType.CANCEL);
        
        Optional<ButtonType> confirm = a.showAndWait();
        if (confirm.isPresent() && confirm.get() == closeButton){
            // close the window
        }
        else if (confirm.isPresent() && confirm.get() == closeAllButton){
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



}
