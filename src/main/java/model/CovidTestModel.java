// programmer: Joshua Turner
// student id: s0258441
// purpose: COIT12200
// Assessment: Assessment 2
// Date: 20 May 2022

package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import controller.App;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import view.MessageView;

public class CovidTestModel {
    
    // add test to the database
    public static void addTestToDB(CovidTest test){

        // create sql string 
        String statement = "INSERT INTO VIRUSTEST (METHOD, TESTDATE, RESULT, PATIENTID) " +
                            "VALUES ('" + test.getTestMethod() + "', '" + test.getTestDate() +
                            "', '" + test.getTestResultDb() + "', '" + test.getPatientId() +
                            "');";

        try {   // run statement 			

			Statement st = App.conn.createStatement();

			st.executeUpdate(statement);

		} catch (Exception e) { // display error if database statement fails
			MessageView.displayException(e, "Error adding test to database");
		}

    }

    // update and existing test in the database
    public static void updateTestInDB(CovidTest test){

        // create sql string
        String statement = "UPDATE VIRUSTEST " +
                            "SET METHOD = '" + test.getTestMethod() + 
                            "', TESTDATE = '" + test.getTestDate() +
                            "', RESULT = '" + test.getTestResultDb() +
                            "' WHERE TESTID = " + test.getCovidTestID() + ";";

        try {   // run statement			

			Statement st = App.conn.createStatement();

			st.executeUpdate(statement);

		} catch (Exception e) { // display error message if statement fails
			MessageView.displayException(e, "Error updating test in database");
		}

    }

    /**
     * 
     * @param patientId of the patient the test belongs to
     * @return list of test objects
     */
    public static ObservableList<CovidTest> getTestListFromDB(int patientId){
        ArrayList<CovidTest> tests = new ArrayList<CovidTest>();    // create array list

        // create sql string
        String statement = "SELECT * FROM VIRUSTEST WHERE PATIENTID = " + patientId +
                         " ORDER BY TESTDATE;";

        try {   // run statement
			Statement stat = App.conn.createStatement();
			ResultSet rs = stat.executeQuery(statement);
			
			while(rs.next()){
				CovidTest test = new CovidTest(); // create temp test objects

                // add details to temp test object
                test.setCovidTestID(rs.getInt("TESTID"));
                test.setTestMethod(rs.getString("METHOD"));
                test.setTestDate(rs.getDate("TESTDATE").toLocalDate());
                test.setTestResultDb(rs.getString("RESULT"));
                test.setPatientId(rs.getInt("PATIENTID"));

                // add temp test object to arrayList
				tests.add(test);
			}
			
		} catch (Exception e) { // display error message if database query fails
            MessageView.displayException(e, "Error getting test list from database");
		}

        // covert array list to observable list
        ObservableList<CovidTest> testList = FXCollections.observableArrayList(tests);
        return testList;
    }
}