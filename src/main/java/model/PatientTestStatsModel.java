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
import view.MessageView;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class PatientTestStatsModel {
    
    /**
     * get list of test statistics
     * @return list of statistics objects
     */
    public static ObservableList<PatientTestStats> getTestStatListFromDB(){

        ArrayList<PatientTestStats> statsList = new ArrayList<PatientTestStats>();

        // create temp table for positive test statistics
        String tempTable1 = "CREATE TEMPORARY TABLE POSITIVETESTS " +
                "SELECT PATIENTID, COUNT(*) AS POSITIVETESTS " +
                "FROM VIRUSTEST WHERE RESULT = 'P' GROUP BY PATIENTID;";

        // create temp table for negative test statistics
        String tempTable2 = "CREATE TEMPORARY TABLE NEGATIVETESTS " +
                "SELECT PATIENTID, COUNT(*) AS NEGATIVETESTS " +
                "FROM VIRUSTEST WHERE RESULT = 'N' GROUP BY PATIENTID;";

        // get output to be read
        String statement = 
                "SELECT CONCAT(P.FIRSTNAME, ' ', P.LASTNAME) AS PATIENTNAME, " +
                "COUNT(V.TESTID) AS TOTALTESTS, IFNULL(PR.POSITIVETESTS,0) AS POSITIVERESULTS, " +
                "IFNULL(NR.NEGATIVETESTS,0) AS NEGATIVERESULTS " +
                "FROM PATIENT AS P LEFT JOIN VIRUSTEST AS V ON P.PATIENTID = V.PATIENTID " +
                "LEFT JOIN POSITIVETESTS AS PR ON PR.PATIENTID = P.PATIENTID " +
                "LEFT JOIN	NEGATIVETESTS AS NR ON NR.PATIENTID = P.PATIENTID " +
                "GROUP BY CONCAT(P.FIRSTNAME, ' ', P.LASTNAME), PR.POSITIVETESTS, NR.NEGATIVETESTS;";
        
        // drop temp tables so method can be run multiple times
        String dropTable1 = "DROP TABLE POSITIVETESTS;";
        String dropTable2 = "DROP TABLE NEGATIVETESTS;";

        try {   // run all above statments
			Statement stat = App.conn.createStatement();

            stat.execute(tempTable1);
            stat.execute(tempTable2);

			ResultSet rs = stat.executeQuery(statement);
			
			while(rs.next()){ // loop through results
				PatientTestStats stats = new PatientTestStats(); // create temp stats object

                // add details to temp stats
                stats.setPatientName(rs.getString("PATIENTNAME"));
                stats.setTotalTests(rs.getInt("TOTALTESTS"));
                stats.setTotalPosResults(rs.getInt("POSITIVERESULTS"));
                stats.setTotalNegResults(rs.getInt("NEGATIVERESULTS"));

				statsList.add(stats); // add temp stats to output list
			}

            stat.execute(dropTable1);
            stat.execute(dropTable2);
			
		} catch (Exception e) { // display error message if statement fails			
            MessageView.displayException(e, "Error getting patient test statistics list from database");
		}

        // covert array list to observable list
        ObservableList<PatientTestStats> patientStatsList = FXCollections.observableArrayList(statsList);
        return patientStatsList;
    }

    /**
     * get list of patient test statistics
     * @param search last name of phone number to be searched
     * @return list of patient test statistics objects
     */
    public static ObservableList<PatientTestStats> getStatSearchResultsFromDB(String search){
        
        ArrayList<PatientTestStats> statsList = new ArrayList<PatientTestStats>();

        // create temp table for positive test statistics
        String tempTable1 = "CREATE TEMPORARY TABLE POSITIVETESTS " +
                "SELECT PATIENTID, COUNT(*) AS POSITIVETESTS " +
                "FROM VIRUSTEST WHERE RESULT = 'P' GROUP BY PATIENTID;";

        // create temp table for negative test statistics
        String tempTable2 = "CREATE TEMPORARY TABLE NEGATIVETESTS " +
                "SELECT PATIENTID, COUNT(*) AS NEGATIVETESTS " +
                "FROM VIRUSTEST WHERE RESULT = 'N' GROUP BY PATIENTID;";

        // get output to be read
        String statement = 
                "SELECT CONCAT(P.FIRSTNAME, ' ', P.LASTNAME) AS PATIENTNAME, " +
                "COUNT(V.TESTID) AS TOTALTESTS, IFNULL(PR.POSITIVETESTS,0) AS POSITIVERESULTS, " +
                "IFNULL(NR.NEGATIVETESTS,0) AS NEGATIVERESULTS " +
                "FROM PATIENT AS P LEFT JOIN VIRUSTEST AS V ON P.PATIENTID = V.PATIENTID " +
                "LEFT JOIN POSITIVETESTS AS PR ON PR.PATIENTID = P.PATIENTID " +
                "LEFT JOIN	NEGATIVETESTS AS NR ON NR.PATIENTID = P.PATIENTID " +
                "WHERE P.PHONE LIKE '%" + search + "%' OR P.LASTNAME LIKE '%" + search +
                "%' GROUP BY CONCAT(P.FIRSTNAME, ' ', P.LASTNAME), PR.POSITIVETESTS, NR.NEGATIVETESTS;";
        
        // drop temp tables so method can be run multiple times
        String dropTable1 = "DROP TABLE POSITIVETESTS;";
        String dropTable2 = "DROP TABLE NEGATIVETESTS;";

        try { // run all above statment
			Statement stat = App.conn.createStatement();

            stat.execute(tempTable1);
            stat.execute(tempTable2);

			ResultSet rs = stat.executeQuery(statement);
			
			while(rs.next()){ // loop through results
				PatientTestStats stats = new PatientTestStats();

                // add details to temp stats
                stats.setPatientName(rs.getString("PATIENTNAME"));
                stats.setTotalTests(rs.getInt("TOTALTESTS"));
                stats.setTotalPosResults(rs.getInt("POSITIVERESULTS"));
                stats.setTotalNegResults(rs.getInt("NEGATIVERESULTS"));

				statsList.add(stats); // add temp stats to output list
			}

            stat.execute(dropTable1);
            stat.execute(dropTable2);
			
		} catch (Exception e) {	// display error message if statement fails			
            MessageView.displayException(e, "Error getting patient test statistics list from database");
		}

        // covert array list to observable list
        ObservableList<PatientTestStats> patientStatsList = FXCollections.observableArrayList(statsList);
        return patientStatsList;
    }
}
