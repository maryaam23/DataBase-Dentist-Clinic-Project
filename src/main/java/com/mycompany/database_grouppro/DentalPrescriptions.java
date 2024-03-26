package com.mycompany.database_grouppro;

import java.time.LocalDate;
import java.util.Date;


public class DentalPrescriptions {
        private String Patient_id;
	private String Patient_name;
        private String Dentist_id;
	private String Dentist_Name;
	private String Treatment_Name;
	private double Treatment_Cost;
        private double The_Amount_Paid;
        private String Treatment_Description;

    public DentalPrescriptions(String Patient_id, String Patient_name, String Dentist_id, String Dentist_Name, String Treatment_Name, double Treatment_Cost, double The_Amount_Paid, String Treatment_Description) {
        this.Patient_id = Patient_id;
        this.Patient_name = Patient_name;
        this.Dentist_id = Dentist_id;
        this.Dentist_Name = Dentist_Name;
        this.Treatment_Name = Treatment_Name;
        this.Treatment_Cost = Treatment_Cost;
        this.The_Amount_Paid = The_Amount_Paid;
        this.Treatment_Description = Treatment_Description;
    }
    


    public String getPatient_id() {
        return Patient_id;
    }

    public String getPatient_name() {
        return Patient_name;
    }

    public String getDentist_id() {
        return Dentist_id;
    }

    public String getDentist_Name() {
        return Dentist_Name;
    }

    public String getTreatment_Name() {
        return Treatment_Name;
    }

    public double getTreatment_Cost() {
        return Treatment_Cost;
    }

    public double getThe_Amount_Paid() {
        return The_Amount_Paid;
    }

    public String getTreatment_Description() {
        return Treatment_Description;
    }

    public void setPatient_id(String Patient_id) {
        this.Patient_id = Patient_id;
    }

    public void setPatient_name(String Patient_name) {
        this.Patient_name = Patient_name;
    }

    public void setDentist_id(String Dentist_id) {
        this.Dentist_id = Dentist_id;
    }

    public void setDentist_Name(String Dentist_Name) {
        this.Dentist_Name = Dentist_Name;
    }

    public void setTreatment_Name(String Treatment_Name) {
        this.Treatment_Name = Treatment_Name;
    }

    public void setTreatment_Cost(double Treatment_Cost) {
        this.Treatment_Cost = Treatment_Cost;
    }

    public void setThe_Amount_Paid(double The_Amount_Paid) {
        this.The_Amount_Paid = The_Amount_Paid;
    }

    public void setTreatment_Description(String Treatment_Description) {
        this.Treatment_Description = Treatment_Description;
    }
        
        
        

}