package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import controller.App;
import view.MessageView;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class PatientTestStatsModel {
    
    public static ObservableList<PatientTestStats> getTestStatListFromDB(){
        ArrayList<PatientTestStats> statsList = new ArrayList<PatientTestStats>();

        String tempTable1 = "CREATE TEMPORARY TABLE POSITIVETESTS " +
                "SELECT PATIENTID, COUNT(*) AS POSITIVETESTS " +
                "FROM VIRUSTEST WHERE RESULT = 'P' GROUP BY PATIENTID;";

        String tempTable2 = "CREATE TEMPORARY TABLE NEGATIVETESTS " +
                "SELECT PATIENTID, COUNT(*) AS NEGATIVETESTS " +
                "FROM VIRUSTEST WHERE RESULT = 'N' GROUP BY PATIENTID;";

        String statement = 
                "SELECT CONCAT(P.FIRSTNAME, ' ', P.LASTNAME) AS PATIENTNAME, " +
                "COUNT(V.TESTID) AS TOTALTESTS, IFNULL(PR.POSITIVETESTS,0) AS POSITIVERESULTS, " +
                "IFNULL(NR.NEGATIVETESTS,0) AS NEGATIVERESULTS " +
                "FROM PATIENT AS P LEFT JOIN VIRUSTEST AS V ON P.PATIENTID = V.PATIENTID " +
                "LEFT JOIN POSITIVETESTS AS PR ON PR.PATIENTID = P.PATIENTID " +
                "LEFT JOIN	NEGATIVETESTS AS NR ON NR.PATIENTID = P.PATIENTID " +
                "GROUP BY CONCAT(P.FIRSTNAME, ' ', P.LASTNAME), PR.POSITIVETESTS, NR.NEGATIVETESTS;";
        
        String dropTable1 = "DROP TABLE POSITIVETESTS;";
        String dropTable2 = "DROP TABLE NEGATIVETESTS;";

        try {
			Statement stat = App.conn.createStatement();

            stat.execute(tempTable1);
            stat.execute(tempTable2);

			ResultSet rs = stat.executeQuery(statement);
			
			while(rs.next()){
				PatientTestStats stats = new PatientTestStats();

                stats.setPatientName(rs.getString("PATIENTNAME"));
                stats.setTotalTests(rs.getInt("TOTALTESTS"));
                stats.setTotalPosResults(rs.getInt("POSITIVERESULTS"));
                stats.setTotalNegResults(rs.getInt("NEGATIVERESULTS"));

				statsList.add(stats);
			}

            stat.execute(dropTable1);
            stat.execute(dropTable2);
			
		} catch (Exception e) {
			
            MessageView.displayException(e, "Error getting patient test statistics list from database");
		}

        ObservableList<PatientTestStats> patientStatsList = FXCollections.observableArrayList(statsList);
        return patientStatsList;
    }

    
    public static ObservableList<PatientTestStats> getStatSearchResultsFromDB(String search){
        
        ArrayList<PatientTestStats> statsList = new ArrayList<PatientTestStats>();

        String tempTable1 = "CREATE TEMPORARY TABLE POSITIVETESTS " +
                "SELECT PATIENTID, COUNT(*) AS POSITIVETESTS " +
                "FROM VIRUSTEST WHERE RESULT = 'P' GROUP BY PATIENTID;";

        String tempTable2 = "CREATE TEMPORARY TABLE NEGATIVETESTS " +
                "SELECT PATIENTID, COUNT(*) AS NEGATIVETESTS " +
                "FROM VIRUSTEST WHERE RESULT = 'N' GROUP BY PATIENTID;";

        String statement = 
                "SELECT CONCAT(P.FIRSTNAME, ' ', P.LASTNAME) AS PATIENTNAME, " +
                "COUNT(V.TESTID) AS TOTALTESTS, IFNULL(PR.POSITIVETESTS,0) AS POSITIVERESULTS, " +
                "IFNULL(NR.NEGATIVETESTS,0) AS NEGATIVERESULTS " +
                "FROM PATIENT AS P LEFT JOIN VIRUSTEST AS V ON P.PATIENTID = V.PATIENTID " +
                "LEFT JOIN POSITIVETESTS AS PR ON PR.PATIENTID = P.PATIENTID " +
                "LEFT JOIN	NEGATIVETESTS AS NR ON NR.PATIENTID = P.PATIENTID " +
                "WHERE P.PHONE LIKE '%" + search + "%' OR P.LASTNAME LIKE '%" + search +
                "%' GROUP BY CONCAT(P.FIRSTNAME, ' ', P.LASTNAME), PR.POSITIVETESTS, NR.NEGATIVETESTS;";
        
        String dropTable1 = "DROP TABLE POSITIVETESTS;";
        String dropTable2 = "DROP TABLE NEGATIVETESTS;";

        try {
			Statement stat = App.conn.createStatement();

            stat.execute(tempTable1);
            stat.execute(tempTable2);

			ResultSet rs = stat.executeQuery(statement);
			
			while(rs.next()){
				PatientTestStats stats = new PatientTestStats();

                stats.setPatientName(rs.getString("PATIENTNAME"));
                stats.setTotalTests(rs.getInt("TOTALTESTS"));
                stats.setTotalPosResults(rs.getInt("POSITIVERESULTS"));
                stats.setTotalNegResults(rs.getInt("NEGATIVERESULTS"));

				statsList.add(stats);
			}

            stat.execute(dropTable1);
            stat.execute(dropTable2);
			
		} catch (Exception e) {
			
            MessageView.displayException(e, "Error getting patient test statistics list from database");
		}

        ObservableList<PatientTestStats> patientStatsList = FXCollections.observableArrayList(statsList);
        return patientStatsList;

    }


}
