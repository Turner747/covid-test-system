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
    
    public static void addTestToDB(CovidTest test){

        
        String statement = "INSERT INTO VIRUSTEST (METHOD, TESTDATE, RESULT, PATIENTID) " +
                            "VALUES ('" + test.getTestMethod() + "', '" + test.getTestDate() +
                            "', '" + test.getTestResultDb() + "', '" + test.getPatientId() +
                            "');";

        System.out.println(statement);          // for debugging
        System.out.println(test.toString());    // for debugging

        try {			

			Statement st = App.conn.createStatement();

			st.executeUpdate(statement);

		} catch (Exception e) {
			MessageView.displayException(e, "Error adding test to database");
		}

    }

    public static void updateTestInDB(CovidTest test){

        String statement = "UPDATE VIRUSTEST " +
                            "SET METHOD = '" + test.getTestMethod() + 
                            "', TESTDATE = '" + test.getTestDate() +
                            "', RESULT = '" + test.getTestResultDb() +
                            "' WHERE TESTID = " + test.getCovidTestID() + ";";
        

        System.out.println(statement);          // for debugging
        System.out.println(test.toString());    // for debugging

        try {			

			Statement st = App.conn.createStatement();

			st.executeUpdate(statement);

		} catch (Exception e) {
			MessageView.displayException(e, "Error updating test in database");
		}

    }

    
    public static ObservableList<CovidTest> getTestListFromDB(int patientId){
        ArrayList<CovidTest> tests = new ArrayList<CovidTest>();

        String statement = "SELECT * FROM VIRUSTEST WHERE PATIENTID = " + patientId +
                         " ORDER BY TESTDATE;";

        try {
			Statement stat = App.conn.createStatement();
			ResultSet rs = stat.executeQuery(statement);
			
			while(rs.next()){
				CovidTest test = new CovidTest();

                test.setCovidTestID(rs.getInt("TESTID"));
                test.setTestMethod(rs.getString("METHOD"));
                test.setTestDate(rs.getDate("TESTDATE").toLocalDate());
                test.setTestResultDb(rs.getString("RESULT"));
                test.setPatientId(rs.getInt("PATIENTID"));

				tests.add(test);
			}
			
		} catch (Exception e) {
			
            MessageView.displayException(e, "Error getting test list from database");
		}

        ObservableList<CovidTest> testList = FXCollections.observableArrayList(tests);
        return testList;
    }
















}
