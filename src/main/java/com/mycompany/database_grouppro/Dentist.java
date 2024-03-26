package com.mycompany.database_grouppro;

import java.util.Date;

public class Dentist {
	private String Dentist_id;
	private String Dentist_Name;
	private Date Dentist_Birthday;
	private String Dentist_Email;
	private String Dentist_phoneNum;
	private String Dentist_Address;
	private String Dentist_specialization;

    public Dentist(String Dentist_id, String Dentist_Name, Date Dentist_Birthday, String Dentist_Email, String Dentist_phoneNum, String Dentist_Address, String Dentist_specialization) {
        this.Dentist_id = Dentist_id;
        this.Dentist_Name = Dentist_Name;
        this.Dentist_Birthday = Dentist_Birthday;
        this.Dentist_Email = Dentist_Email;
        this.Dentist_phoneNum = Dentist_phoneNum;
        this.Dentist_Address = Dentist_Address;
        this.Dentist_specialization = Dentist_specialization;
    }

    public Dentist(String Dentist_id, String Dentist_Name) {
        this.Dentist_id = Dentist_id;
        this.Dentist_Name = Dentist_Name;
    }

    
    

    public String getDentist_id() {
        return Dentist_id;
    }

    public String getDentist_Name() {
        return Dentist_Name;
    }

    public Date getDentist_Birthday() {
        return Dentist_Birthday;
    }

    public String getDentist_Email() {
        return Dentist_Email;
    }

    public String getDentist_phoneNum() {
        return Dentist_phoneNum;
    }

    public String getDentist_Address() {
        return Dentist_Address;
    }

    public String getDentist_specialization() {
        return Dentist_specialization;
    }

    public void setDentist_id(String Dentist_id) {
        this.Dentist_id = Dentist_id;
    }

    public void setDentist_Name(String Dentist_Name) {
        this.Dentist_Name = Dentist_Name;
    }

    public void setDentist_Birthday(Date Dentist_Birthday) {
        this.Dentist_Birthday = Dentist_Birthday;
    }

    public void setDentist_Email(String Dentist_Email) {
        this.Dentist_Email = Dentist_Email;
    }

    public void setDentist_phoneNum(String Dentist_phoneNum) {
        this.Dentist_phoneNum = Dentist_phoneNum;
    }

    public void setDentist_Address(String Dentist_Address) {
        this.Dentist_Address = Dentist_Address;
    }

    public void setDentist_specialization(String Dentist_specialization) {
        this.Dentist_specialization = Dentist_specialization;
    }

    @Override
    public String toString() {
        return "Dentist{" + "Dentist_id=" + Dentist_id + ", Dentist_Name=" + Dentist_Name + ", Dentist_Birthday=" + Dentist_Birthday + ", Dentist_Email=" + Dentist_Email + ", Dentist_phoneNum=" + Dentist_phoneNum + ", Dentist_Address=" + Dentist_Address + ", Dentist_specialization=" + Dentist_specialization + '}';
    }
        
        

}