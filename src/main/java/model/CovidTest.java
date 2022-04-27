package model;

import java.time.LocalDate;

public class CovidTest {
    
    private int covidTestID;
    private LocalDate testDate;
    private String testMethod;
    private String testResult;

    
    /**
     * default constructor
     */
    public CovidTest() {
        this.covidTestID = 0;
        this.testDate = LocalDate.now();
        this.testMethod = "not set";
        this.testResult = "not set";
    }

    /**
     * contructor without a test id
     * @param testDate
     * @param testMethod
     * @param testResult
     */
    public CovidTest(LocalDate testDate, String testMethod, String testResult) {
        this.testDate = testDate;
        this.testMethod = testMethod;
        this.testResult = testResult;
    }

    /**
     * complete contructor
     * @param covidTestID
     * @param testDate
     * @param testMethod
     * @param testResult
     */
    public CovidTest(int covidTestID, LocalDate testDate, String testMethod, String testResult) {
        this.covidTestID = covidTestID;
        this.testDate = testDate;
        this.testMethod = testMethod;
        this.testResult = testResult;
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

    @Override
    public String toString() {
        return "CovidTest [covidTestID=" + covidTestID + 
                            ", testDate=" + testDate + 
                            ", testMethod=" + testMethod + 
                            ", testResult=" + testResult + "]";
    }

}
