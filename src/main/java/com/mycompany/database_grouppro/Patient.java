package com.mycompany.database_grouppro;

import java.util.Date;

public class Patient {
	private String Patient_id;
	private String Patient_name;
	private String Patient_PhoneNum;
	private String Patient_Gender;
	private Date Birthday;
	private String Patient_Email;
	private Date Patient_LastVisit_Date;
	private String Patient_Insurance;

    public Patient(String Patient_id, String Patient_name, String Patient_PhoneNum, String Patient_Gender, Date Birthday, String Patient_Email, Date Patient_LastVisit_Date, String Patient_Insurance) {
        this.Patient_id = Patient_id;
        this.Patient_name = Patient_name;
        this.Patient_PhoneNum = Patient_PhoneNum;
        this.Patient_Gender = Patient_Gender;
        this.Birthday = Birthday;
        this.Patient_Email = Patient_Email;
        this.Patient_LastVisit_Date = Patient_LastVisit_Date;
        this.Patient_Insurance = Patient_Insurance;
    }

    public String getPatient_id() {
        return Patient_id;
    }

    public String getPatient_name() {
        return Patient_name;
    }

    public String getPatient_PhoneNum() {
        return Patient_PhoneNum;
    }

    public String getPatient_Gender() {
        return Patient_Gender;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public String getPatient_Email() {
        return Patient_Email;
    }

    public Date getPatient_LastVisit_Date() {
        return Patient_LastVisit_Date;
    }

    public String getPatient_Insurance() {
        return Patient_Insurance;
    }

    public void setPatient_id(String Patient_id) {
        this.Patient_id = Patient_id;
    }

    public void setPatient_name(String Patient_name) {
        this.Patient_name = Patient_name;
    }

    public void setPatient_PhoneNum(String Patient_PhoneNum) {
        this.Patient_PhoneNum = Patient_PhoneNum;
    }

    public void setPatient_Gender(String Patient_Gender) {
        this.Patient_Gender = Patient_Gender;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    public void setPatient_Email(String Patient_Email) {
        this.Patient_Email = Patient_Email;
    }

    public void setPatient_LastVisit_Date(Date Patient_LastVisit_Date) {
        this.Patient_LastVisit_Date = Patient_LastVisit_Date;
    }

    public void setPatient_Insurance(String Patient_Insurance) {
        this.Patient_Insurance = Patient_Insurance;
    }

    @Override
    public String toString() {
        return "Patient{" + "Patient_id=" + Patient_id + ", Patient_name=" + Patient_name + ", Patient_PhoneNum=" + Patient_PhoneNum + ", Patient_Gender=" + Patient_Gender + ", Birthday=" + Birthday + ", Patient_Email=" + Patient_Email + ", Patient_LastVisit_Date=" + Patient_LastVisit_Date + ", Patient_Insurance=" + Patient_Insurance + '}';
    }
        
        

}