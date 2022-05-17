// programmer: Joshua Turner
// student id: s0258441
// purpose: COIT12200
// Assessment: Assessment 2
// Date: 20 May 2022

package model;

import java.time.LocalDate;

public class CovidTest {
    
    private int covidTestID;
    private LocalDate testDate;
    private String testMethod;
    private String testResult;
    private int patientId;

    
    /**
     * default constructor
     */
    public CovidTest() {
        this.covidTestID = 0;
        this.testDate = LocalDate.now();
        this.testMethod = "not set";
        this.testResult = "not set";
        this.patientId = 0;
    }

    /**
     * contructor without a test id
     * @param testDate
     * @param testMethod
     * @param testResult
     * @param patientId
     */
    public CovidTest(LocalDate testDate, String testMethod, String testResult, int patientId) {
        this.testDate = testDate;
        this.testMethod = testMethod;
        this.testResult = testResult;
        this.patientId = patientId;
    }

    /**
     * complete contructor
     * @param covidTestID
     * @param testDate
     * @param testMethod
     * @param testResult
     * @param patientId
     */
    public CovidTest(int covidTestID, LocalDate testDate, String testMethod, String testResult, int patientId) {
        this.covidTestID = covidTestID;
        this.testDate = testDate;
        this.testMethod = testMethod;
        this.testResult = testResult;
        this.patientId = patientId;
    }

    public int getCovidTestID() {
        return covidTestID;
    }

    public void setCovidTestID(int covidTestID) {
        this.covidTestID = covidTestID;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getTestMethod() {
        return testMethod;
    }

    public void setTestMethod(String testMethod) {
        this.testMethod = testMethod;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    /**
     * Used to convert full test result to single character string
     * @return single character string
     */
    public String getTestResultDb() {
        
        if(this.testResult.equals("Positive"))
            return "P";
        else if(this.testResult.equals("Negative"))
            return "N";
        else
            return "";

    }

    /**
     * Converts single character string from DB to full word
     * @param result single character string from database
     */
    public void setTestResultDb(String result) {

        if(result.equals("P"))
            this.testResult = "Positive";
        else if(result.equals("N"))
            this.testResult = "Negative";
        else
            this.testResult = "";
        
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "CovidTest [covidTestID=" + covidTestID + 
                            ", testDate=" + testDate + 
                            ", testMethod=" + testMethod + 
                            ", testResult=" + testResult + "]";
    }

}
