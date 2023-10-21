/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author osmel
 */
public class Detail extends Medication {
    
    private int registrationNumber, petIdentification, medicationIdentification;
    private String dosage;

    public Detail() {
    }

    public Detail(int registrationNumber, int petIdentification, int medicationIdentification, String dosage) {
        this.registrationNumber = registrationNumber;
        this.petIdentification = petIdentification;
        this.medicationIdentification = medicationIdentification;
        this.dosage = dosage;
    }

    public Detail(int registrationNumber, int petIdentification, int medicationIdentification, String dosage, int identification, String name, String description) {
        super(identification, name, description);
        this.registrationNumber = registrationNumber;
        this.petIdentification = petIdentification;
        this.medicationIdentification = medicationIdentification;
        this.dosage = dosage;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getPetIdentification() {
        return petIdentification;
    }

    public void setPetIdentification(int petIdentification) {
        this.petIdentification = petIdentification;
    }

    public int getMedicationIdentification() {
        return medicationIdentification;
    }

    public void setMedicationIdentification(int medicationIdentification) {
        this.medicationIdentification = medicationIdentification;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}
