// programmer: Joshua Turner
// student id: s0258441
// purpose: COIT12200
// Assessment: Assessment 2
// Date: 20 May 2022

package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.MessageView;
import controller.App;

public class PatientModel {
    
    /**
     * add patient to the database
     * @param patient to be added
     */
    public static void addPatientToDB(Patient patient){

        // create sql string
        String statement = "INSERT INTO PATIENT (FIRSTNAME, LASTNAME, PHONE, DOB, GENDER) " +
                            "VALUES ('" + patient.getFirstName() + "', '" + patient.getLastName() +
                            "', '" + patient.getPhoneNbr() + "', '" + patient.getDateOfBirth() +
                            "', '" + patient.getGenderDb() + "');";

        try {   // run statement			

			Statement st = App.conn.createStatement();

			st.executeUpdate(statement);

		} catch (Exception e) { // display error message if statement fails
			MessageView.displayException(e, "Error adding patient to database");
		}

    }

    /**
     * update existing patient in database
     * @param patient patient to be updated
     */
    public static void updatePatientInDB(Patient patient){

        // create sql string
        String statement = "UPDATE PATIENT " +
                            "SET FIRSTNAME = '" + patient.getFirstName() + 
                            "', LASTNAME = '" + patient.getLastName() +
                            "', PHONE = '" + patient.getPhoneNbr() + 
                            "', DOB = '" + patient.getDateOfBirth() +
                            "', GENDER = '" + patient.getGenderDb() + 
                            "' WHERE PATIENTID = " + patient.getPatientID() + ";";

        try {	// run statement		

			Statement st = App.conn.createStatement();

			st.executeUpdate(statement);

		} catch (Exception e) { // display error message if statement fails
			MessageView.displayException(e, "Error updating patient in database");
		}

    }

    /**
     * get list of all patients
     * @return list of patient objects
     */
    public static ObservableList<Patient> getPatientListFromDB(){
        
        ArrayList<Patient> patients = new ArrayList<Patient>(); // create array

        String statement = "SELECT * FROM PATIENT;";    // create sql string

        try {   // run statement
			Statement stat = App.conn.createStatement();
			ResultSet rs = stat.executeQuery(statement);
			
			while(rs.next()){   // loop through results
				Patient patient = new Patient(); // create temp patient

                // add patient details to temp patient
				patient.setPatientID(rs.getInt("PATIENTID"));
				patient.setFirstName(rs.getString("FIRSTNAME"));
				patient.setLastName(rs.getString("LASTNAME"));
				patient.setDateOfBirth(rs.getDate("DOB").toLocalDate());
				patient.setPhoneNbr(rs.getString("PHONE"));
                patient.setGenderDb(rs.getString("GENDER"));

				patients.add(patient);  // add temp patient to the list
			}
			
		} catch (Exception e) { // display error is statement fails
            MessageView.displayException(e, "Error getting patient list from database");
		}

        // covert array list to observable list
        ObservableList<Patient> patientList = FXCollections.observableArrayList(patients);
        return patientList;
    }

    /**
     * search the patient table for a matching last name or phone number
     * @param search the value to search
     * @return the list of matching patients
     */
    public static ObservableList<Patient> getSearchResultsFromDB(String search){
        ArrayList<Patient> patients = new ArrayList<Patient>(); // create list

        //create sql string
        String statement = "SELECT * FROM PATIENT WHERE PHONE LIKE '%" + search + 
                            "%' OR LASTNAME LIKE '%" + search + "%';";

        try {   // run statement
			Statement stat = App.conn.createStatement();
			ResultSet rs = stat.executeQuery(statement);
			
			while(rs.next()){ // loop through results
				Patient patient = new Patient(); // create temp patient

                // add patient details to the temp patient
				patient.setPatientID(rs.getInt("PATIENTID"));
				patient.setFirstName(rs.getString("FIRSTNAME"));
				patient.setLastName(rs.getString("LASTNAME"));
				patient.setDateOfBirth(rs.getDate("DOB").toLocalDate());
				patient.setPhoneNbr(rs.getString("PHONE"));
                patient.setGenderDb(rs.getString("GENDER"));

				patients.add(patient); // add temp patient to the list
			}
			
		} catch (Exception e) { // display error if statement fails
            MessageView.displayException(e, "Error getting search results from database");
		}

        // conver array list to observable list
        ObservableList<Patient> patientList = FXCollections.observableList(patients);
        return patientList;
    }
}
