package com.mycompany.database_grouppro;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

	private int Appointment_id;
        private LocalDate Appointment_Date;
	private LocalTime Appointment_Time;
        private String Patient_id;
	private String Patient_name;
        private String Dentist_id;
	private String Dentist_Name;
        

	public Appointment() {

	}

    public Appointment(int Appointment_id,LocalDate Appointment_Date,  LocalTime Appointment_Time, String Patient_id, String Patient_name,String Dentist_id, String Dentist_Name) {
        this.Appointment_id = Appointment_id;
        this.Appointment_Date = Appointment_Date;
        this.Patient_id = Patient_id;
        this.Patient_name = Patient_name;this.Appointment_Time = Appointment_Time;
        this.Patient_id = Patient_id;
        this.Patient_name = Patient_name;
        this.Dentist_id = Dentist_id;
        this.Dentist_Name = Dentist_Name;
        
    }

    public void setAppointment_Date(LocalDate Appointment_Date) {
        this.Appointment_Date = Appointment_Date;
    }

    public void setAppointment_id(int Appointment_id) {
        this.Appointment_id = Appointment_id;
    }

    public void setAppointment_Time(LocalTime Appointment_Time) {
        this.Appointment_Time = Appointment_Time;
    }

    public void setDentist_id(String Dentist_id) {
        this.Dentist_id = Dentist_id;
    }

    public void setDentist_Name(String Dentist_Name) {
        this.Dentist_Name = Dentist_Name;
    }

    public void setPatient_id(String Patient_id) {
        this.Patient_id = Patient_id;
    }

    public void setPatient_name(String Patient_name) {
        this.Patient_name = Patient_name;
    }

    public LocalDate getAppointment_Date() {
        return Appointment_Date;
    }

    public int getAppointment_id() {
        return Appointment_id;
    }

    public LocalTime getAppointment_Time() {
        return Appointment_Time;
    }

    public String getDentist_id() {
        return Dentist_id;
    }

    public String getDentist_Name() {
        return Dentist_Name;
    }

    public String getPatient_id() {
        return Patient_id;
    }

    public String getPatient_name() {
        return Patient_name;
    }

	

}