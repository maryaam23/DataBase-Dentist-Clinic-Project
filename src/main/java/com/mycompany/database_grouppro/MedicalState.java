/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database_grouppro;
import java.util.Date;


public class MedicalState {
        
    	String Patient_id;
	String Patient_name;
	String M_medical_state;
	Date Last_visited_date;

	public MedicalState() {
		super();
		// TODO Auto-generated constructor stub
	}

    public void setPatient_id(String Patient_id) {
        this.Patient_id = Patient_id;
    }

    public void setPatient_name(String Patient_name) {
        this.Patient_name = Patient_name;
    }

    public void setM_medical_state(String M_medical_state) {
        this.M_medical_state = M_medical_state;
    }

    public void setLast_visited_date(Date Last_visited_date) {
        this.Last_visited_date = Last_visited_date;
    }

    public String getPatient_id() {
        return Patient_id;
    }

    public String getPatient_name() {
        return Patient_name;
    }

    public String getM_medical_state() {
        return M_medical_state;
    }

    public Date getLast_visited_date() {
        return Last_visited_date;
    }

    @Override
    public String toString() {
        return "MedicalState{" + "Patient_id=" + Patient_id + ", Patient_name=" + Patient_name + ", M_medical_state=" + M_medical_state + ", Last_visited_date=" + Last_visited_date + '}';
    }

    public MedicalState(String Patient_id, String Patient_name, String M_medical_state, Date Last_visited_date) {
        this.Patient_id = Patient_id;
        this.Patient_name = Patient_name;
        this.M_medical_state = M_medical_state;
        this.Last_visited_date = Last_visited_date;
    }

    
    
}