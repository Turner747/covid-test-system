package model;

public class PatientTestStats {
    
    private String patientName;
    private int totalTests;
    private int totalPosResults;
    private int totalNegResults;

    /**
     * default constructor
     */
    public PatientTestStats() {
    }

    /**
     * complete contructor
     * @param patientName
     * @param totalTests
     * @param totalPosResults
     * @param totalNegResults
     */
    public PatientTestStats(String patientName, int totalTests, int totalPosResults, int totalNegResults) {
        this.patientName = patientName;
        this.totalTests = totalTests;
        this.totalPosResults = totalPosResults;
        this.totalNegResults = totalNegResults;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public int getTotalPosResults() {
        return totalPosResults;
    }

    public void setTotalPosResults(int totalPosResults) {
        this.totalPosResults = totalPosResults;
    }

    public int getTotalNegResults() {
        return totalNegResults;
    }

    public void setTotalNegResults(int totalNegResults) {
        this.totalNegResults = totalNegResults;
    }

    @Override
    public String toString() {
        return "PatientTestStats [patientName=" + patientName + 
                                ", totalTests=" + totalTests +
                                ", totalNegResults=" + totalNegResults + 
                                ", totalPosResults=" + totalPosResults + "]";
    }

}
