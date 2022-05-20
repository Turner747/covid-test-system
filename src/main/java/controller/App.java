// programmer: Joshua Turner
// student id: s0258441
// purpose: COIT12200
// Assessment: Assessment 2
// Date: 20 May 2022

package controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.IOException;

import view.MessageView;

/**
 * JavaFX App
 */
public class App extends Application {

    // database connection details
    private static final String DATABASE = "COVIDTESTDB";
    private static final String USER = "root";
    private static final String PWRD = "mypassword";
    private static final String HOST = "localhost";
   
    //database connection object to be used for all queries
    public static Connection conn = null;

    // main scene for the application
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        Alert connMessage = MessageView.displayDBConnection(        // show connection message
                                        "Connecting...");
        connMessage.show();

        try{
            conn = estDBConnection(); //connect to the database
            
        }catch(Exception e){   // if connection fails, show error message
            MessageView.displayException(e, "Database connection failed");

            MessageView.displayError("Failed to establish connection to the database");
            System.exit(0);
        }

        connMessage.setContentText("Connection successful");    // display success message
        connMessage.close();    // close success message

        // display the application and setup window event handle for close button
        scene = new Scene(loadFXML("MenuView"));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                MessageView.displayExitDialog(e);
            }
        });

    }

    // used to load fxml into a scene object
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // used to get fxml resource from file structure
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // main method
    public static void main(String[] args) {
        launch();
    }

    /**
     * establishes connection with database specified in class variables
     * @return database connection object
     */
    public static Connection estDBConnection() {
		Connection c = null;
		
		final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE;
		
		try {			
			c = DriverManager.getConnection(URL, USER, PWRD);
			
		} catch(Exception e){
			MessageView.displayException(e, "Error connecting to database");
		} 
		
        return c;
	}

    /**
     * Open a new window and load a scene into it
     * @param view is the name of the FXML file that is being loaded
     * @param title is the title of the window
     */
    public static void openNewWindow(String view, String title) {
        
        Stage newStage = new Stage();
        try{
            Scene newScene = new Scene(loadFXML(view));
            newStage.setScene(newScene);
            newStage.show();
            newStage.setTitle(title);
            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    MessageView.displayExitDialog(e);
                }
            });
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}