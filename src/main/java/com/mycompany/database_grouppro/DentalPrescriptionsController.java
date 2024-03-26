package com.mycompany.database_grouppro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DentalPrescriptionsController implements Initializable {

    private String URL_GData;
    private String UserName_SqlWork = "root";
    private String Password_SqlWork = "2312003";
    private String URL_SqlWork = "127.0.0.1";
    private String Port_SqlWork = "3306";
    private String Name_SqlWork = "smileclinic";
    private Connection con;

    @FXML
    private TableView<DentalPrescriptions> DentalPrescriptionsTable;
    @FXML
    private Button Add_Button_Treat;

    @FXML
    private Button Delete_Button_MPres;

    @FXML
    private TableColumn<DentalPrescriptions, String> Dentist_Name;

    @FXML
    private TableColumn<DentalPrescriptions, String> Dentist_id;

    @FXML
    private TextField Dentist_id1;

    @FXML
    private TextField Patient_id1;

    @FXML
    private TableColumn<DentalPrescriptions, String> Patient_id;

    @FXML
    private TableColumn<DentalPrescriptions, String> Patient_name;

    @FXML
    private Button Refresh_Button;

    @FXML
    private Button ReturnCoverPage;

    @FXML
    private Button Search_Button_MPres;

    @FXML
    private Label DebtPateint;

    @FXML
    private Button Search_Button_MPres1;

    @FXML
    private Button Search_Button_MPres2;

    @FXML
    private Button Search_Button_MPres21;

    @FXML
    private Button Search_Button_MPres22;

    @FXML
    private Label AmountEqual;

    @FXML
    private Label Debt;

    @FXML
    private Label TotalCost;

    @FXML
    private TableColumn<DentalPrescriptions, Double> The_Amount_Paid;

    @FXML
    private TextField ABCD;

    @FXML
    private TableColumn<DentalPrescriptions, Double> Treatment_Cost;

    @FXML
    private TextField Treatment_Cost1;

    @FXML
    private TableColumn<DentalPrescriptions, String> Treatment_Description;

    @FXML
    private TextField Treatment_Description1;

    @FXML
    private TableColumn<DentalPrescriptions, String> Treatment_Name;

    @FXML
    private TextField Treatment_Name1;

    @FXML
    void Add_Button_Treat(ActionEvent event) {
        boolean found = false;
        for (DentalPrescriptions D : PM) {
            if (D.getPatient_id().equals(Patient_id1.getText())) {
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
                String SQL = "UPDATE dentist_treatment_patient SET Treatment_Name=?, Treatment_Cost=? , The_Amount_Paid =? ,Treatment_Description=? WHERE Patient_id=? AND Dentist_id=? ";
                PreparedStatement preparedStatement = con.prepareStatement(SQL);
                preparedStatement.setString(1, Treatment_Name1.getText().trim());
                preparedStatement.setDouble(2, Double.parseDouble(Treatment_Cost1.getText().trim()));
                preparedStatement.setDouble(3, Double.parseDouble(ABCD.getText().trim()));
                preparedStatement.setString(4, Treatment_Description1.getText().trim());
                preparedStatement.setString(5, Patient_id1.getText().trim());
                preparedStatement.setString(6, Dentist_id1.getText().trim());
                preparedStatement.executeUpdate();
                getDataDentalPrescriptions(1);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void Delete_Button_MPres(ActionEvent event) {
        try {
            String SQL = "DELETE FROM medical_state WHERE Patient_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(SQL);
            preparedStatement.setString(1,Patient_id1.getText());
            preparedStatement.executeUpdate();
            getDataDentalPrescriptions(1); // Refresh the list
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Refresh_Button(ActionEvent event) {

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
    void Search_Button_MPres(ActionEvent event) {

    }

    ObservableList<DentalPrescriptions> PM = FXCollections.observableArrayList();
    ObservableList<DentalPrescriptions> search = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Patient_id.setCellValueFactory(new PropertyValueFactory<DentalPrescriptions, String>("Patient_id"));
        Patient_name.setCellValueFactory(new PropertyValueFactory<DentalPrescriptions, String>("Patient_name"));
        Dentist_id.setCellValueFactory(new PropertyValueFactory<DentalPrescriptions, String>("Dentist_id"));
        Dentist_Name.setCellValueFactory(new PropertyValueFactory<DentalPrescriptions, String>("Dentist_Name"));
        Treatment_Name.setCellValueFactory(new PropertyValueFactory<DentalPrescriptions, String>("Treatment_Name"));
        Treatment_Cost.setCellValueFactory(new PropertyValueFactory<DentalPrescriptions, Double>("Treatment_Cost"));
        The_Amount_Paid.setCellValueFactory(new PropertyValueFactory<DentalPrescriptions, Double>("The_Amount_Paid"));
        Treatment_Description.setCellValueFactory(new PropertyValueFactory<DentalPrescriptions, String>("Treatment_Description"));
        try {
            PM = getDataDentalPrescriptions(1);
            DentalPrescriptionsTable.setItems(PM);

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Search_Button_MPres.setOnAction((ActionEvent e) -> {
            DentalPrescriptionsTable.setItems(search);
            boolean found = false;
            for (DentalPrescriptions D : PM) {
                if (D.getPatient_id().equals(Patient_id1.getText())) {

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
                    getDataDentalPrescriptions(2);
                } catch (ClassNotFoundException e1) {
                    throw new RuntimeException(e1);
                } catch (SQLException e2) {
                    throw new RuntimeException(e2);
                }
            }
        });
        Search_Button_MPres1.setOnAction((ActionEvent e) -> {
            DentalPrescriptionsTable.setItems(search);
            boolean found = false;
            for (DentalPrescriptions D : PM) {
                if (D.getDentist_id().equals(Dentist_id1.getText())) {

                    found = true;
                    break;

                }
            }
            if (found == false) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(" Error, The Dentist ID Not Found");
                error.showAndWait();
            } else {

                try {
                    getDataDentalPrescriptions(4);
                } catch (ClassNotFoundException e1) {
                    throw new RuntimeException(e1);
                } catch (SQLException e2) {
                    throw new RuntimeException(e2);
                }
            }
        });
        Search_Button_MPres2.setOnAction((ActionEvent e) -> {
            try {
                getDataDentalPrescriptions(5);
            } catch (ClassNotFoundException e1) {
                throw new RuntimeException(e1);
            } catch (SQLException e2) {
                throw new RuntimeException(e2);
            }
        });
        Search_Button_MPres21.setOnAction((ActionEvent e) -> {
            try {
                getDataDentalPrescriptions(6);
            } catch (ClassNotFoundException e1) {
                throw new RuntimeException(e1);
            } catch (SQLException e2) {
                throw new RuntimeException(e2);
            }
        });

        Search_Button_MPres22.setOnAction((ActionEvent e) -> {

            result = totalCost - totalAmountPaid;
            Debt.setText(String.format("%.2f", result)); // Set the text of the label

        });

        Delete_Button_MPres.setOnAction((ActionEvent e) -> {
            boolean found = false;
            for (DentalPrescriptions D : PM) {
                if (D.getPatient_id().equals(Patient_id1.getText())) {

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

                    DentalPrescriptionsTable.setItems(getDataDentalPrescriptions(3));
                    Patient_id1.clear();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Refresh_Button.setOnAction((ActionEvent e) -> {
            try {
                PM = getDataDentalPrescriptions(1);
                Patient_id1.clear();
                Dentist_id1.clear();
                Treatment_Name1.clear();
                Treatment_Cost1.clear();
                ABCD.clear();
                Treatment_Description1.clear();
                AmountEqual.setText("");
                TotalCost.setText("");
                Debt.setText("");
                DebtPateint.setText("");
                totalAmountPaid = 0;
                totalCost = 0;
                result = 0;
                totalPateintDebt = 0;
                patientcost = 0;
                pateintamount = 0;
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            DentalPrescriptionsTable.setItems(PM);

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
    double totalPateintDebt = 0;
    double patientcost = 0;
    double pateintamount = 0;
    double result = 0;
    double totalAmountPaid = 0;
    double totalCost = 0;

    public ObservableList<DentalPrescriptions> getDataDentalPrescriptions(int i) throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();
        if (i == 1) {
            try {
                PM.clear();
                SQL = "select * from dentist_treatment_patient";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    PM.add(new DentalPrescriptions(
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
            return PM;
        }
        if (i == 2) {
            try {
                search.clear();
                SQL = "select * from dentist_treatment_patient where Patient_id=" + Patient_id1.getText();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    search.add(new DentalPrescriptions(
                            rs.getString("Patient_id"),
                            rs.getString("Patient_name"),
                            rs.getString("Dentist_id"),
                            rs.getString("Dentist_Name"),
                            rs.getString("Treatment_Name"),
                            rs.getDouble("Treatment_Cost"),
                            rs.getDouble("The_Amount_Paid"),
                            rs.getString("Treatment_Description")
                    ));
                    //  the sum of The_Amount_Paid
                    patientcost += rs.getDouble("Treatment_Cost");
                    //  the sum of The_Amount_Paid
                    pateintamount += rs.getDouble("The_Amount_Paid");

                    totalPateintDebt = patientcost - pateintamount;
                }
                // AmountEqual is the fx:id of your label
                DebtPateint.setText(String.format("%.2f", totalPateintDebt)); // Set the text of the label

            } catch (Exception e) {
                e.printStackTrace();
            }
            return search;
        }
        if (i == 3) {
            SQL = "DELETE FROM dentist_treatment_patient WHERE Patient_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            String Patient_idd = Patient_id1.getText();
            pstmt.setString(1, Patient_idd);
            int rowsAffected = pstmt.executeUpdate(); // Execute the delete operation
            if (rowsAffected > 0) {
                System.out.println("Deleted " + rowsAffected + " rows.");
            } else {
                System.out.println("No rows deleted.");
            }
            getDataDentalPrescriptions(1); // Refresh data
            return PM;
        }
        if (i == 4) {
            try {
                search.clear();
                SQL = "select * from dentist_treatment_patient where Dentist_id=" + Dentist_id1.getText();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    search.add(new DentalPrescriptions(
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
            return search;
        }

        if (i == 5) {
            // Variable to store the sum of The_Amount_Paid
            try {
                PM.clear();
                // Query to select all records
                SQL = "SELECT * FROM dentist_treatment_patient";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    PM.add(new DentalPrescriptions(
                            rs.getString("Patient_id"),
                            rs.getString("Patient_name"),
                            rs.getString("Dentist_id"),
                            rs.getString("Dentist_Name"),
                            rs.getString("Treatment_Name"),
                            rs.getDouble("Treatment_Cost"),
                            rs.getDouble("The_Amount_Paid"),
                            rs.getString("Treatment_Description")
                    ));
                    //  the sum of The_Amount_Paid
                    totalAmountPaid += rs.getDouble("The_Amount_Paid");
                }

                // AmountEqal is the fx:id of your label
                AmountEqual.setText(String.format("%.2f", totalAmountPaid)); // Set the text of the label

            } catch (Exception e) {
                e.printStackTrace();
                AmountEqual.setText("Error"); // Set error message in the label if exception occurs
            }

            return PM; // Return the list with prescription details
        }

        if (i == 6) {
            // Variable to store the sum of The_Amount_Paid
            try {
                PM.clear();
                // Query to select all records
                SQL = "SELECT * FROM dentist_treatment_patient";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    PM.add(new DentalPrescriptions(
                            rs.getString("Patient_id"),
                            rs.getString("Patient_name"),
                            rs.getString("Dentist_id"),
                            rs.getString("Dentist_Name"),
                            rs.getString("Treatment_Name"),
                            rs.getDouble("Treatment_Cost"),
                            rs.getDouble("The_Amount_Paid"),
                            rs.getString("Treatment_Description")
                    ));
                    //  the sum of The_Amount_Paid
                    totalCost += rs.getDouble("Treatment_Cost");
                }

                // AmountEqual is the fx:id of your label
                TotalCost.setText(String.format("%.2f", totalCost)); // Set the text of the label

            } catch (Exception e) {
                e.printStackTrace();
                TotalCost.setText("Error"); // Set error message in the label if exception occurs
            }
        }
        return PM;
    }

    @FXML

    public void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();

        } catch (SQLException s) {
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");

        }

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
