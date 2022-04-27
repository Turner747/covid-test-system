package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    // database connection details
    private static final String DATABASE = "COVIDTESTDB";
    private static final String USER = "PatientTestSystem";
    private static final String PWRD = "Asst-2-Password";
    private static final String HOST = "172.105.191.27";
   
    public Connection conn;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MenuView"));
        stage.setScene(scene);
        stage.show();

        conn = estDBConnection();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

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
			System.out.println("Connection established.\n");
			
		} catch(Exception e){
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
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
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}