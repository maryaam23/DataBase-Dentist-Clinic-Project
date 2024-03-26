package com.mycompany.database_grouppro;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PatientController {

    private String URL_GData;
    private String UserName_SqlWork = "root";
    private String Password_SqlWork = "2312003";
    private String URL_SqlWork = "127.0.0.1";
    private String Port_SqlWork = "3306";
    private String Name_SqlWork = "smileclinic";
    private Connection con;

    @FXML
    private Button Add_Button_PatInfo;

    @FXML
    private CheckBox Allergies_Check;

    @FXML
    private TextField Amount_Paid;

    @FXML
    private DatePicker Birthday;

    @FXML
    private CheckBox BloodDisorders_Check;

    @FXML
    private CheckBox Cancer_Check;

    @FXML
    private TextField DESC_Per;

    @FXML
    private TextField Dentist_id1;

    @FXML
    private CheckBox Diabetes_Check;

    @FXML
    private ComboBox Doctor_Menu;

    @FXML
    private RadioButton Female_Rad_But;

    @FXML
    private CheckBox GERD_Check;

    @FXML
    private CheckBox HeartConditions_Check;

    @FXML
    private CheckBox ImmuneSystemDisorders_Check;

    @FXML
    private DatePicker LastVisitDate;

    @FXML
    private CheckBox LiverorKidneyDisease_Check;

    @FXML
    private RadioButton Male_Rad_But;

    @FXML
    private Button PatientRecorgPage;

    @FXML
    private TextField Patient_Email;

    @FXML
    private TextField Patient_Insurance;

    @FXML
    private Label Patient_LastVisit_Date;

    @FXML
    private TextField Patient_PhoneNum;

    @FXML
    private TextField Patient_id;

    @FXML
    private TextField Patient_name;

    @FXML
    private CheckBox Pregnancy_Check;

    @FXML
    private CheckBox Pressure_Check;

    @FXML
    private Button Refresh_Button_PatInfo;

    @FXML
    private CheckBox RespiratoryDiseases_Check;

    @FXML
    private Button ReturnCoverPage;

    @FXML
    private CheckBox SleepApnea_Check;

    @FXML
    private TextField Treatment_Cost;

    @FXML
    private ComboBox Treatment_Type;

    @FXML
    private CheckBox Xerostomia_Check;

    public void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            Alert a = new Alert(AlertType.NONE);
            a.setAlertType(AlertType.CONFIRMATION);
            a.setContentText("OPERATION SUCSSESFULLY ");
            a.show();
            stmt.close();
        } catch (SQLException s) {
            Alert error = new Alert(AlertType.ERROR);
            error.setHeaderText("An Error Occurred!!! ");
            error.showAndWait();
        }
    }

    public void InsertIntoDentalPrescriptions(DentalPrescriptions d)  {
        PreparedStatement pstmt = null;
        try {
            // Assuming connectDB() sets a class member 'con' to be the connection
             connectDB();
            String SQL = "INSERT INTO dentist_treatment_patient (Patient_id, Patient_name, Dentist_id, Dentist_Name, Treatment_Name, Treatment_Cost, The_Amount_Paid, Treatment_Description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, d.getPatient_id()); // Assuming getters are available in DentalPrescriptions class
            pstmt.setString(2, d.getPatient_name());
            pstmt.setString(3, d.getDentist_id());
            pstmt.setString(4, d.getDentist_Name());
            pstmt.setString(5, d.getTreatment_Name());
            pstmt.setDouble(6, d.getTreatment_Cost()); // Assuming getTreatmentCost returns a double
            pstmt.setDouble(7, d.getThe_Amount_Paid()); // Assuming getAmountPaid returns a double
            pstmt.setString(8, d.getTreatment_Description());

            pstmt.executeUpdate();

         } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertIntoPatientRecordController(Patient rc, String gen, LocalDate Birthday) {

        try {
            String SQL;

            connectDB();
            ExecuteStatement("insert into patient (Patient_id,Patient_name,gender,Patient_PhoneNum,Patient_Email,Birthday) values(" + Patient_id.getText() + ",'" + Patient_name.getText() + "','" + Dentist_id1.getText() + ",'" + Doctor_Menu.getValue() + "','" + Treatment_Type.getValue() + "','" + Treatment_Cost.getText() + "','" + Amount_Paid.getText() + "','" + DESC_Per.getText() + "')");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InsertIntoMedicalStates( MedicalState medical,String M_medical_state, Date Last_visited_date) {

        try {
            String SQL;

            connectDB();
            ExecuteStatement("insert into medical_state(Patient_id,Patient_name,M_medical_state,Last_visited_date) values(" + Patient_id.getText() + ",'" + Patient_name.getText() + "','" + M_medical_state + "','" + Last_visited_date + "')");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Add_Button_PatInfo(ActionEvent event) {

    }

    public void InsertIntoPatientRecordController(Patient rc, String gen, Date Birthday, Date LastVisit_Date) {
        PreparedStatement pstmt = null;

        try {
            String SQL;

            connectDB();
            SQL = "INSERT INTO patient (Patient_id, Patient_name,Patient_Gender, Patient_PhoneNum, Patient_Email, Patient_Insurance, Birthday, Patient_LastVisit_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, Patient_id.getText()); // Assuming Patient_id is a TextField or similar component
            pstmt.setString(2, Patient_name.getText()); // Assuming Patient_name is a TextField or similar component
            pstmt.setString(3, gen); // 'gen' is a method parameter of type String for gender
            pstmt.setString(4, Patient_PhoneNum.getText()); // Assuming Patient_PhoneNum is a TextField or similar component
            pstmt.setString(5, Patient_Email.getText()); // Assuming Patient_Email is a TextField or similar component
            pstmt.setString(6, Patient_Insurance.getText()); // Assuming Patient_Insurance is a TextField or similar component
            pstmt.setDate(7, new java.sql.Date(Birthday.getTime())); // 'Birthday' is a method parameter of type java.util.Date
            pstmt.setDate(8, new java.sql.Date(LastVisit_Date.getTime())); // 'LastVisit_Date' is a method parameter of type java.util.Date

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // It's important to close the PreparedStatement to avoid resource leaks
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    void PatientInfoScene(ActionEvent event) {

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
    void Refresh_Button_PatInfo(ActionEvent event) {
         try {
                
                Patient_Email.clear();
                Patient_Insurance.clear();
                Patient_name.clear();
                Patient_id.clear();
                Dentist_id1.clear();
                Patient_PhoneNum.clear();
                Amount_Paid.clear();
                Treatment_Cost.clear();
                DESC_Per.clear();
                LastVisitDate.setValue(null);
                Birthday.setValue(null);
                Treatment_Type.setValue(null);
                Doctor_Menu.setValue(null);
                Female_Rad_But.getToggleGroup().selectToggle(null);
                Male_Rad_But.getToggleGroup().selectToggle(null);
                HeartConditions_Check.setSelected(false);
                Allergies_Check.setSelected(false);
                BloodDisorders_Check.setSelected(false);
                Cancer_Check.setSelected(false);
                Diabetes_Check.setSelected(false);
                GERD_Check.setSelected(false);
                ImmuneSystemDisorders_Check.setSelected(false);
                LiverorKidneyDisease_Check.setSelected(false);
                Pregnancy_Check.setSelected(false);
                Pressure_Check.setSelected(false);
                RespiratoryDiseases_Check.setSelected(false);
                SleepApnea_Check.setSelected(false);
                Xerostomia_Check.setSelected(false);
                
            } catch (Exception e) {
            e.printStackTrace();
        }
            

    }

    @FXML
    void ReturnCoverPage(ActionEvent event) {
        try {

            Parent root_1 = FXMLLoader.load(getClass().getResource("CoverPage.fxml"));
            Scene scene_3 = new Scene(root_1);
            Stage st = (Stage) (((Node) event.getSource()).getScene().getWindow());
            st.setScene(scene_3);
            st.show();

        } catch (Exception e) {

        }
    }

    @FXML
    private void initialize() {
        // Initialize the database connection and populate the Doctor_Menu
        try {
            // Get the list of dentists and add them to the ComboBox
            ObservableList<Dentist> dentistList = getDataDentist();
            for (Dentist dentist : dentistList) {
                Doctor_Menu.getItems().add(dentist.getDentist_Name());
            }

            // Listen for selection changes in the ComboBox
            Doctor_Menu.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                // Find the selected Dentist object by name
                for (Dentist d : dentistList) {
                    if (d.getDentist_Name().equals(newValue)) {
                        Dentist_id1.setText(d.getDentist_id());
                        break; // Exit the loop once the matching dentist is found
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add items to the Treatment_Type ComboBox
        Treatment_Type.getItems().addAll("Preventive Care", "Restorative Treatments", "Cosmetic Procedures", "Orthodontics", "Periodontal Treatments", "Prosthodontics", "Oral Surgery", "Endodontics", "Pediatric Dentistry", "Emergency Dental Care");

        // Set up the gender toggle group
        ToggleGroup toggleGender = new ToggleGroup();
        Male_Rad_But.setToggleGroup(toggleGender);
        Female_Rad_But.setToggleGroup(toggleGender);
        final String[] gen = new String[1];
        // Set actions for the gender radio buttons
        Male_Rad_But.setOnAction(e -> {
            gen[0] = Male_Rad_But.getText();
        });
        Female_Rad_But.setOnAction(e -> {
            gen[0] = Female_Rad_But.getText();
        });

        // Set action for Add_Button_PatInfo
        Add_Button_PatInfo.setOnAction((ActionEvent e) -> {
            try {
                String patientId = Patient_id.getText();
                String patientName = Patient_name.getText();
                String patientPhoneNum = Patient_PhoneNum.getText();
                String patientEmail = Patient_Email.getText();
                String patientInsuranceComp = Patient_Insurance.getText();
                String Dentist_id = Dentist_id1.getText();
                double Cost = Double.parseDouble(Treatment_Cost.getText());
                double Paid = Double.parseDouble(Amount_Paid.getText());
                String Description = DESC_Per.getText();

                java.sql.Date patientBirthDate = null;
                java.sql.Date patientLastVisitDate = null;

                // Null checks for date fields
                if (Birthday.getValue() != null) {
                    patientBirthDate = java.sql.Date.valueOf(Birthday.getValue());
                }
                if (LastVisitDate.getValue() != null) {
                    patientLastVisitDate = java.sql.Date.valueOf(LastVisitDate.getValue());
                }

                // Create a new Patient instance with the gathered data
                Patient toPatient = new Patient(patientId, patientName, patientPhoneNum, gen[0], patientBirthDate, patientEmail, patientLastVisitDate, patientInsuranceComp);
                InsertIntoPatientRecordController(toPatient, gen[0], patientBirthDate, patientLastVisitDate);

                DentalPrescriptions Dental = new DentalPrescriptions(patientId, patientName, Dentist_id, (String) Doctor_Menu.getValue(), (String) Treatment_Type.getValue(), Cost, Paid, Description);
                InsertIntoDentalPrescriptions(Dental);
                
                
                if(Allergies_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Allergies",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Allergies", patientLastVisitDate);

                }
                
                if(HeartConditions_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Heart Conditions",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Heart Conditions", patientLastVisitDate);

                }
                if(Diabetes_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Diabetes",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Diabetes", patientLastVisitDate);

                }
                if(Cancer_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Cancer",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Cancer", patientLastVisitDate);

                }
                if(RespiratoryDiseases_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Respiratory Diseases",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Respiratory Diseases", patientLastVisitDate);

                }
                if(BloodDisorders_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Blood Disorders",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Blood Disorders", patientLastVisitDate);

                }
                if(Pregnancy_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Pregnancy",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Pregnancy", patientLastVisitDate);

                }
                if(LiverorKidneyDisease_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Liver or Kidney Disease",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Liver or Kidney Disease", patientLastVisitDate);

                }
                if(Xerostomia_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Xerostomia (Dry Mouth)",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Xerostomia (Dry Mouth)", patientLastVisitDate);

                }
                if(Pressure_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Pressure",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Pressure", patientLastVisitDate);

                }
                if(ImmuneSystemDisorders_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Immune System Disorders",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Immune System Disorders", patientLastVisitDate);

                }
                if(SleepApnea_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Sleep Apnea",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Sleep Apnea", patientLastVisitDate);

                }
                if(GERD_Check.isSelected()){
                    MedicalState medical;
                    medical=new MedicalState(patientId ,patientName,"Gastroesophageal Reflux Disease (GERD)",patientLastVisitDate);
                    InsertIntoMedicalStates(medical,"Gastroesophageal Reflux Disease (GERD)", patientLastVisitDate);

                }
// Additional logic here as needed
            } catch (IllegalArgumentException ex) {
                // Handle the exception
            }
        });
    }

    // This method represents your method to retrieve a list of doctor names
    private List<String> getDoctorsList() {
        // Replace this with your actual method to get the list of doctors
        // For example, this might involve querying a database
        List<String> doctors = new ArrayList<>();
        // Add doctor names to the list from your data source
        // ...
        return doctors;
    }

    ObservableList<Dentist> Dentist_list = FXCollections.observableArrayList();
    ObservableList<Patient> Patientlist = FXCollections.observableArrayList();
    ObservableList<DentalPrescriptions> Dentallist = FXCollections.observableArrayList();
    ObservableList<MedicalState> Medical_list = FXCollections.observableArrayList();

    public ObservableList<Patient> getDataPatient() throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();

        try {
            Patientlist.clear();
            SQL = "select * from patient";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                Patientlist.add(new Patient(
                        rs.getString("Patient_id"),
                        rs.getString("Patient_name"),
                        rs.getString("Patient_PhoneNum"),
                        rs.getString("Patient_Gender"),
                        rs.getDate("Birthday"),
                        rs.getString("Patient_Email"),
                        rs.getDate("Patient_LastVisit_Date"),
                        rs.getString("Patient_Insurance")
                ));

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return Patientlist;
    }

    public ObservableList<DentalPrescriptions> getDataDentalPrescriptions() throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();

        try {
            Dentallist.clear();
            SQL = "select * from dentist_treatment_patient";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Dentallist.add(new DentalPrescriptions(
                        rs.getString("Patient_id"),
                        rs.getString("Patient_name"),
                        rs.getString("Dentist_id"),
                        rs.getString("Dentist_Name"),
                        rs.getString("Treatment_Name"),
                        rs.getDouble("Treatment_Cost"),
                        rs.getDouble("The_Amount_Paid"),
                        rs.getString("Treatment_Description")
                ));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Dentallist;

    }

    public ObservableList<Dentist> getDataDentist() throws ClassNotFoundException, SQLException {
        ObservableList<Dentist> dentistList = FXCollections.observableArrayList();
        connectDB(); // Make sure this method sets the 'con' variable to a valid Connection object

        try {
            String SQL = "SELECT Dentist_Name, Dentist_id FROM dentist"; // Correct SQL syntax
            Statement Dstat = con.createStatement();
            ResultSet Resdent = Dstat.executeQuery(SQL);
            while (Resdent.next()) {
                dentistList.add(new Dentist(Resdent.getString("Dentist_id"), Resdent.getString("Dentist_Name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close(); // Close the connection after use
            }
        }
        return dentistList;
    }

    public ObservableList<MedicalState> getDataMedical() throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();

        try {
            Medical_list.clear();
            SQL = "select * from medical_state";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Medical_list.add(new MedicalState(rs.getString("Patient_Id"), rs.getString("Patient_name"), rs.getString("M_medical_state"), rs.getDate("Last_visited_date")));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return Medical_list;
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
