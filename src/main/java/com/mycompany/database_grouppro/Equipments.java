package com.mycompany.database_grouppro;


public class Equipments {
	
	private int Equipment_id;
	private String  Equipment_Name;
	private String  Equipment_Description;

    public Equipments(int Equipment_id, String Equipment_Name, String Equipment_Description) {
        this.Equipment_id = Equipment_id;
        this.Equipment_Name = Equipment_Name;
        this.Equipment_Description = Equipment_Description;
    }

    public int getEquipment_id() {
        return Equipment_id;
    }

    public String getEquipment_Name() {
        return Equipment_Name;
    }

    public String getEquipment_Description() {
        return Equipment_Description;
    }

    public void setEquipment_id(int Equipment_id) {
        this.Equipment_id = Equipment_id;
    }

    public void setEquipment_Name(String Equipment_Name) {
        this.Equipment_Name = Equipment_Name;
    }

    public void setEquipment_Description(String Equipment_Description) {
        this.Equipment_Description = Equipment_Description;
    }

    @Override
    public String toString() {
        return "Equipments{" + "Equipment_id=" + Equipment_id + ", Equipment_Name=" + Equipment_Name + ", Equipment_Description=" + Equipment_Description + '}';
    }
	
	
	
	

}