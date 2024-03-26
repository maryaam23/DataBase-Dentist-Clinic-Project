package com.mycompany.database_grouppro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MainCoverPageController {
    
    private String URL_GData;
    private String UserName_SqlWork = "root";
    private String Password_SqlWork = "2312003";
    private String URL_SqlWork = "127.0.0.1";
    private String Port_SqlWork = "3306";
    private String Name_SqlWork = "smileclinic";
    private Connection con;

    @FXML
    public void Appointments_Button(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentPage.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			System.err.println("Error loading AppointmentPage.fxml: " + e.getMessage());
			e.printStackTrace();
		}
	}

    @FXML
    public void Dental_Medication_Prescriptions_Button(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DentalPrescriptions.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			System.err.println("Error loading DentalPrescriptions.fxml: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	void Dentist_Button(ActionEvent event) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("DentistList.fxml"));
                        
			Scene scene = new Scene(root);
			Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
                        st.setScene(scene);
                        st.show();
		} catch (IOException e) {
			System.err.println("Error loading DentistList.fxml: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	void EquipmentList_Button(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EquipmentsList.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace(); // Handle the exception appropriately
		}
	}

	@FXML
	void MedicalState_Button(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MedicalStPage.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace(); // Handle the exception appropriately
		}
	}

	@FXML
	void Patients_Button(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientRecord.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace(); // Handle the exception appropriately
		}
	}

	@FXML
	void TOEND_Button(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FinalePage.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace(); // Handle the exception appropriately
		}
	}


	private Parent loadFXML(String fxml) throws IOException {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource(fxml));
	    return loader.load();
	}


	private void connectDB() throws ClassNotFoundException, SQLException {
        URL_GData = "jdbc:mysql://" + URL_SqlWork + ":" + Port_SqlWork + "/" + Name_SqlWork + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", UserName_SqlWork);
        p.setProperty("password", Password_SqlWork);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(URL_GData, p);
    }

        
}
