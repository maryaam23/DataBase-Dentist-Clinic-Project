package com.mycompany.database_grouppro;

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
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PatientRecordController implements Initializable {

    private String URL_GData;
    private String UserName_SqlWork = "root";
    private String Password_SqlWork = "2312003";
    private String URL_SqlWork = "127.0.0.1";
    private String Port_SqlWork = "3306";
    private String Name_SqlWork = "smileclinic";
    private Connection con;

    @FXML
    private TableView<Patient> PaientRecordTable;

    @FXML
    private TableColumn<Patient, Date> Birthday;

    @FXML
    private Button DeleteIdPutSmileFiles;

    @FXML
    private Button Ins_Company;

    @FXML
    private Button ListGender;

    @FXML
    private Label List_gender;

    @FXML
    private Button PatienInfo;

    @FXML
    private TextField PatientIdPut;

    @FXML
    private TextField InsComp;

    @FXML
    private TextField Gender;

    @FXML
    private TableColumn<Patient, String> Patient_Email;

    @FXML
    private TableColumn<Patient, String> Patient_Gender;

    @FXML
    private TableColumn<Patient, String> Patient_Insurance;

    @FXML
    private TableColumn<Patient, Date> Patient_LastVisit_Date;

    @FXML
    private TableColumn<Patient, String> Patient_PhoneNum;

    @FXML
    private TableColumn<Patient, String> Patient_id;

    @FXML
    private TableColumn<Patient, String> Patient_name;

    @FXML
    private Button ReturnCoverPage;

    @FXML
    private Button SearchIdPutSmileFiles;

    @FXML
    private Button SortDate;

    @FXML
    private Button Refresh;

    @FXML
    void DeleteIdPutSmileFiles(ActionEvent event) {
        try {
            String SQL = "DELETE FROM patient WHERE Patient_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(SQL);
            preparedStatement.setString(1, PatienInfo.getText());
            preparedStatement.executeUpdate();
            getPaientRecord(1); // Refresh the list
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void Ins_Company(ActionEvent event) {

    }

    @FXML
    void ListGender(ActionEvent event) {

    }

    @FXML
    void PatientInfoScene(ActionEvent event) {

    }

    @FXML
    void ReturnCoverPage(ActionEvent event) {

    }

    @FXML
    void SearchIdPutSmileFiles(ActionEvent event) {

    }

    @FXML
    void SortDate(ActionEvent event) {
        PaientRecordTable.getSortOrder().add(Patient_LastVisit_Date);
        Patient_LastVisit_Date.setSortType(TableColumn.SortType.DESCENDING);
        PaientRecordTable.sort();

    }
    ObservableList<Patient> Rec = FXCollections.observableArrayList();
    ObservableList<Patient> search = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Patient_id.setCellValueFactory(new PropertyValueFactory<Patient, String>("Patient_id"));
        Patient_name.setCellValueFactory(new PropertyValueFactory<Patient, String>("Patient_name"));
        Patient_PhoneNum.setCellValueFactory(new PropertyValueFactory<Patient, String>("Patient_PhoneNum"));
        Patient_Gender.setCellValueFactory(new PropertyValueFactory<Patient, String>("Patient_Gender"));
        Birthday.setCellValueFactory(new PropertyValueFactory<Patient, Date>("Birthday"));
        Patient_Email.setCellValueFactory(new PropertyValueFactory<Patient, String>("Patient_Email"));
        Patient_LastVisit_Date.setCellValueFactory(new PropertyValueFactory<Patient, Date>("Patient_LastVisit_Date"));
        Patient_Insurance.setCellValueFactory(new PropertyValueFactory<Patient, String>("Patient_Insurance"));

        try {
            Rec = getPaientRecord(1);
            PaientRecordTable.setItems(Rec);

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SearchIdPutSmileFiles.setOnAction((ActionEvent e) -> {//search by id

            boolean found = false;
            for (Patient p : Rec) {
                if (p.getPatient_id().equals(PatientIdPut.getText())) {

                    found = true;
                    break;

                }
            }
            if (found == false) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(" Error, The Dentist ID Not Found");
                error.showAndWait();
            } else {

                PaientRecordTable.setItems(search);
                try {
                    if (PatientIdPut.getText() != null) {
                        PaientRecordTable.setItems(getPaientRecord(2));
                    }

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        Ins_Company.setOnAction((ActionEvent e) -> {//search by id

            boolean found = false;
            for (Patient p : Rec) {
                if (p.getPatient_Insurance().equals(InsComp.getText())) {

                    found = true;
                    break;

                }
            }
            if (found == false) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(" Error, This Company Not Found");
                error.showAndWait();
            } else {

                PaientRecordTable.setItems(search);
                try {
                    if (InsComp.getText() != null) {
                        PaientRecordTable.setItems(getPaientRecord(4));
                    }

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        ListGender.setOnAction((ActionEvent e) -> {//search by id

            boolean found = false;
            for (Patient p : Rec) {
                if (p.getPatient_Gender().equals(Gender.getText())) {

                    found = true;
                    break;

                }
            }
            if (found == false) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(" Error, This Gender Not Found Please enter Male or Female only");
                error.showAndWait();
            } else {

                PaientRecordTable.setItems(search);
                try {
                    if (Gender.getText() != null) {
                        PaientRecordTable.setItems(getPaientRecord(5));
                    }

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        DeleteIdPutSmileFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                boolean found = false;
                for (Patient p : Rec) {
                    if (p.getPatient_id().equals(PatientIdPut.getText())) {

                        found = true;
                        break;

                    }
                }
                if (found == false) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText(" Error, The Patient ID Not Found");
                    error.showAndWait();
                } else {

                    try {

                        PaientRecordTable.setItems(getPaientRecord(3));

                        PatientIdPut.clear();
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        PatienInfo.setOnAction((ActionEvent e) -> {

            try {

                Parent root_1 = FXMLLoader.load(getClass().getResource("PatientPage.fxml"));
                Scene scene_3 = new Scene(root_1);
                Stage st = (Stage) (((Node) e.getSource()).getScene().getWindow());
                st.setScene(scene_3);
                st.show();

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
        Refresh.setOnAction((ActionEvent e) -> {
            try {
                Rec = getPaientRecord(1);
                Gender.clear();
                InsComp.clear();
                PatientIdPut.clear();
                List_gender.setText("");
                PaientRecordTable.getSortOrder().clear();
                PaientRecordTable.refresh();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            PaientRecordTable.setItems(Rec);
        });
        ReturnCoverPage.setOnAction((ActionEvent e) -> {

            try {

                Parent root_1 = FXMLLoader.load(getClass().getResource("CoverPage.fxml"));
                Scene scene_3 = new Scene(root_1);
                Stage st = (Stage) (((Node) e.getSource()).getScene().getWindow());
                st.setScene(scene_3);
                st.show();

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

    }

    public ObservableList<Patient> getPaientRecord(int i) throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();
        if (i == 1) {
            try {
                Rec.clear();
                SQL = "select * from patient";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    Rec.add(new Patient(
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
            return Rec;
        }
        if (i == 2) {
            try {
                search.clear();
                SQL = "select * from patient where Patient_id=" + PatientIdPut.getText();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    search.add(new Patient(
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
            return search;
        }
        if (i == 3) {
            SQL = "DELETE FROM patient WHERE Patient_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            String Patient_idd = PatientIdPut.getText();
            pstmt.setString(1, Patient_idd);
            int rowsAffected = pstmt.executeUpdate(); // Execute the delete operation
            if (rowsAffected > 0) {
                System.out.println("Deleted " + rowsAffected + " rows.");
            } else {
                System.out.println("No rows deleted.");
            }
            getPaientRecord(1); // Refresh data
            return Rec;
        }
        if (i == 4) {
            try {
                search.clear();
                SQL = "select * from patient where Patient_Insurance='" + InsComp.getText()+ "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    search.add(new Patient(
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
            return search;
        }

        if (i == 5) {
            // Variable to store the sum of The_Amount_Paid
            try {
                search.clear();
                // Query to select all records
                SQL = "select * from patient where Patient_Gender='" + Gender.getText() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    search.add(new Patient(
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
                int sumGender = search.size();
                List_gender.setText(String.format("%d", sumGender));

            } catch (Exception e) {
                e.printStackTrace();

            }

            return search; // Return the list with prescription details
        }
        return Rec;
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