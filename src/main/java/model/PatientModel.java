package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.MessageView;
import controller.App;

public class PatientModel {
    
    public static void addPatientToDB(Patient patient){

        
        String statement = "INSERT INTO PATIENT (FIRSTNAME, LASTNAME, PHONE, DOB, GENDER) " +
                            "VALUES ('" + patient.getFirstName() + "', '" + patient.getLastName() +
                            "', '" + patient.getPhoneNbr() + "', '" + patient.getDateOfBirth() +
                            "', '" + patient.getGenderDb() + "');";
    
        System.out.println(statement);              // for debugging
        System.out.println(patient.toString());     // for debugging

        try {			

			Statement st = App.conn.createStatement();

			st.executeUpdate(statement);

		} catch (Exception e) {
			MessageView.displayException(e, "Error adding patient to database");
		}

    }

    public static void updatePatientInDB(Patient patient){

        String statement = "UPDATE PATIENT " +
                            "SET FIRSTNAME = '" + patient.getFirstName() + 
                            "', LASTNAME = '" + patient.getLastName() +
                            "', PHONE = '" + patient.getPhoneNbr() + 
                            "', DOB = '" + patient.getDateOfBirth() +
                            "', GENDER = '" + patient.getGenderDb() + 
                            "' WHERE PATIENTID = " + patient.getPatientID() + ";";
        
        System.out.println(statement);             // for debugging
        System.out.println(patient.toString());    // for debugging

        try {			

			Statement st = App.conn.createStatement();

			st.executeUpdate(statement);

		} catch (Exception e) {
			MessageView.displayException(e, "Error updating patient in database");
		}

    }

    
    public static ObservableList<Patient> getPatientListFromDB(){
        ArrayList<Patient> patients = new ArrayList<Patient>();

        String statement = "SELECT * FROM PATIENT;";

        try {
			Statement stat = App.conn.createStatement();
			ResultSet rs = stat.executeQuery(statement);
			
			while(rs.next()){
				Patient patient = new Patient();
				patient.setPatientID(rs.getInt("PATIENTID"));
				patient.setFirstName(rs.getString("FIRSTNAME"));
				patient.setLastName(rs.getString("LASTNAME"));
				patient.setDateOfBirth(rs.getDate("DOB").toLocalDate());
				patient.setPhoneNbr(rs.getString("PHONE"));
                patient.setGenderDb(rs.getString("GENDER"));
				patients.add(patient);
			}
			
		} catch (Exception e) {
			
            MessageView.displayException(e, "Error getting patient list from database");
		}

        ObservableList<Patient> patientList = FXCollections.observableArrayList(patients);
        return patientList;
    }

    /**
     * search the patient table for a matching last name or phone number
     * @param search the value to search
     * @return the list of matching patients
     */
    public static ObservableList<Patient> getSearchResultsFromDB(String search){
        ArrayList<Patient> patients = new ArrayList<Patient>();

        String statement = "SELECT * FROM PATIENT WHERE PHONE LIKE '%" + search + 
                            "%' OR LASTNAME LIKE '%" + search + "%';";

        try {
			Statement stat = App.conn.createStatement();
			ResultSet rs = stat.executeQuery(statement);
			
			while(rs.next()){
				Patient patient = new Patient();
				patient.setPatientID(rs.getInt("PATIENTID"));
				patient.setFirstName(rs.getString("FIRSTNAME"));
				patient.setLastName(rs.getString("LASTNAME"));
				patient.setDateOfBirth(rs.getDate("DOB").toLocalDate());
				patient.setPhoneNbr(rs.getString("PHONE"));
                patient.setGenderDb(rs.getString("GENDER"));
				patients.add(patient);
			}
			
		} catch (Exception e) {
			
            MessageView.displayException(e, "Error getting search results from database");
		}

        ObservableList<Patient> patientList = FXCollections.observableList(patients);
        return patientList;
    }

}
