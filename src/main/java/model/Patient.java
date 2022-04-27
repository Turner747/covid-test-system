package model;

import java.time.LocalDate;

public class Patient {
    
    private int patientID;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String phoneNbr;

    /**
     * default constructor
     */
    public Patient() {
        this.firstName = "not set";
        this.lastName = "not set";
        this.dateOfBirth = LocalDate.now();
        this.gender = "not set";
        this.phoneNbr = "not set";
    }

    /**
     * contructor with no patient id
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param gender
     * @param phoneNbr
     */
    public Patient(String firstName, String lastName, LocalDate dateOfBirth, String gender, String phoneNbr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNbr = phoneNbr;
    }

    /**
     * complete contructor
     * @param patientID
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param gender
     * @param phoneNbr
     */
    public Patient(int patientID, String firstName, String lastName, LocalDate dateOfBirth, String gender,
            String phoneNbr) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNbr = phoneNbr;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    @Override
    public String toString() {
        return "Patient [patientID=" + patientID +
                        ", firstName=" + firstName + 
                        ", lastName=" + lastName + 
                        ", dateOfBirth=" + dateOfBirth + 
                        ", gender=" + gender + 
                        ", phoneNbr=" + phoneNbr + "]";
    }

}
