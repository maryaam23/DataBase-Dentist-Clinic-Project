package com.mycompany.database_grouppro;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class MedicalStateController implements Initializable {

    private String URL_GData;
    private String UserName_SqlWork = "root";
    private String Password_SqlWork = "2312003";
    private String URL_SqlWork = "127.0.0.1";
    private String Port_SqlWork = "3306";
    private String Name_SqlWork = "smileclinic";
    private Connection con;

    @FXML
    private Button ListOfMedical;
    @FXML
    private TextField Medicallist;
    @FXML
    private Button Delete_Button_Medical;

    @FXML
    private TableColumn<MedicalState, String> M_medical_state;

    @FXML
    private TableColumn<MedicalState, Date> Last_visited_date;

    @FXML
    private TableColumn<MedicalState, String> Patient_id;

    @FXML
    private TableColumn<MedicalState, String> Patient_name;

    @FXML
    private TextField Patient_id1;

    @FXML
    private Button Refresh_Button;

    @FXML
    private Button ReturnCoverPage;

    @FXML
    private Button Search_Button_Medical;

    @FXML
    private TableView<MedicalState> tableMedicalist;

    @FXML
    private Button countMedSButton;

    @FXML
    private Label countLabel;
    @FXML
    private Button Delete_Both;

    @FXML
    void countMedSButton(ActionEvent event) {

        int numberOfM = Search_List_Medical.size();
        countLabel.setText("Is: " + numberOfM);
    }

    @FXML
    void Delete_Button_Medical(ActionEvent event) {

    }

    public void ExecuteStatement(String SQL) throws SQLException {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();
        } catch (SQLException s) {

        }
    }

    @FXML
    void Refresh_Button(ActionEvent event) {

        showAlert("Refreshed", "Medical state data refreshed successfully.", AlertType.INFORMATION);
    }

    @FXML
    void ReturnCoverPage(ActionEvent event) {

    }

    @FXML
    void Delete_Both(ActionEvent event) {

    }

    @FXML
    void Search_Button_Medical(ActionEvent event) {
        // Implement your search logic here
    }

    private void showAlert(String title, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {

        Patient_id.setCellValueFactory(new PropertyValueFactory<MedicalState, String>("Patient_id"));
        Patient_name.setCellValueFactory(new PropertyValueFactory<MedicalState, String>("Patient_name"));
        M_medical_state.setCellValueFactory(new PropertyValueFactory<MedicalState, String>("M_medical_state"));
        Last_visited_date.setCellValueFactory(new PropertyValueFactory<MedicalState, Date>("Last_visited_date"));

        try {

            Medical_list = getDataMedical(1);
            tableMedicalist.setItems(Medical_list);

            // connectDB();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Search_Button_Medical.setOnAction((ActionEvent e1) -> {
            ObservableList<MedicalState> search = FXCollections.observableArrayList();
            boolean found = false;

            for (MedicalState D : Medical_list) {
                if (D.getPatient_id().equals(Patient_id1.getText())) {

                    found = true;
                    search.add(D); // Add the found MedicalState to the search list
                    break;
                }
            }
            if (!found) { // Using !found instead of found == false for clarity
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Error, The patient ID Not Found");
                error.showAndWait();
            } else {
                tableMedicalist.setItems(search); // Set the items to the search list
                // The following try-catch block seems redundant if you've already found the data
                // If it's necessary for other reasons, keep it, otherwise, you can remove it.
                try {
                    tableMedicalist.setItems(getDataMedical(2));
                } catch (ClassNotFoundException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        Refresh_Button.setOnAction((ActionEvent e) -> {

            try {

                Medical_list = getDataMedical(1);
                tableMedicalist.setItems(Medical_list);

                // connectDB();
            } catch (ClassNotFoundException | SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Patient_id1.clear();
            countLabel.setText("Is: ");
            Medicallist.clear();

            Medicallist.clear();

        });

        Delete_Button_Medical.setOnAction((ActionEvent e) -> {
            boolean found = false;

            for (MedicalState D : Medical_list) {
                if (D.getPatient_id().equals(Patient_id1.getText())) {
                    found = true;
                    break; // Exit the loop once the patient is found
                }
            }

            if (!found) {
                // Alert if the patient ID is not found
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Error, The patient ID Not Found");
                error.showAndWait();
            } else {
                // If found, perform the following actions
                try {
                    tableMedicalist.setItems(getDataMedical(3));

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
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

        ListOfMedical.setOnAction((ActionEvent e1) -> {
            boolean found = false;
            ObservableList<MedicalState> search = FXCollections.observableArrayList();

            for (MedicalState D : Medical_list) {
                if (D.getM_medical_state().equals(Medicallist.getText())) {
                    found = true;
                    search.add(D); // Add the found MedicalState to the search list
                    break;
                }
            }

            if (!found) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Error, The Medical State Not Found");
                error.showAndWait();
            } else {
                // Set the items to the search list only if found
                tableMedicalist.setItems(search);
            }
        });

        Delete_Both.setOnAction((ActionEvent e) -> {
            boolean found = false;

            for (MedicalState D : Medical_list) {
                if (D.getPatient_id().equals(Patient_id1.getText())) {
                    if (D.getM_medical_state().equals(Medicallist.getText())) {
                        found = true;
                        break; // Exit the loop once the patient is found
                    }
                }
            }

            if (!found) {
                // Alert if the patient ID is not found
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Error, The patient ID with this state Not Found");
                error.showAndWait();
            } else {
                // If found, perform the following actions
                try {
                    tableMedicalist.setItems(getDataMedical(4));

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    ObservableList<MedicalState> Medical_list = FXCollections.observableArrayList();
    ObservableList<MedicalState> Search_List_Medical = FXCollections.observableArrayList();

    public ObservableList<MedicalState> getDataMedical(int i) throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();
        if (i == 1) {
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
        if (i == 2) {
            Search_List_Medical.clear();

            try {
                SQL = "select * from medical_state where Patient_id= ?";

                PreparedStatement pstmt = con.prepareStatement(SQL);

                String Patient_idd = Patient_id1.getText(); // Dentist_idText should be defined and linked in your FXML
                pstmt.setString(1, Patient_idd);

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Search_List_Medical.add(new MedicalState(rs.getString("Patient_Id"), rs.getString("Patient_name"), rs.getString("M_medical_state"), rs.getDate("Last_visited_date")));
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
            return Search_List_Medical;
        }

        if (i == 3) {
            SQL = "DELETE FROM medical_state WHERE Patient_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            String Patient_idd = Patient_id1.getText();
            pstmt.setString(1, Patient_idd);
            int rowsAffected = pstmt.executeUpdate(); // Execute the delete operation
            if (rowsAffected > 0) {
                System.out.println("Deleted " + rowsAffected + " rows.");
            } else {
                System.out.println("No rows deleted.");
            }
            getDataMedical(1); // Refresh data
            return Medical_list;
        }

        if (i == 4) {
            SQL = "DELETE FROM medical_state WHERE Patient_id = ? AND M_medical_state = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = con.prepareStatement(SQL);
                String Patient_idd = Patient_id1.getText();
                String medic = Medicallist.getText();
                pstmt.setString(1, Patient_idd);
                pstmt.setString(2, medic);
                int affectedRows = pstmt.executeUpdate(); // This will execute the delete operation with the parameters

                // If you want to check if the delete was successful
                if (affectedRows > 0) {
                    System.out.println("Record deleted successfully.");
                } else {
                    System.out.println("No records to delete or delete failed.");
                }

                // Assuming getDataMedical(1) is refreshing the data
                getDataMedical(1);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            // Assuming Medical_list is defined elsewhere and needs to be returned
            return Medical_list;
        }

        if (i == 5) {
            Search_List_Medical.clear();

            try {
                SQL = "select * from medical_state where M_medical_state= ?";

                PreparedStatement pstmt = con.prepareStatement(SQL);

                String Pat = Medicallist.getText(); // Dentist_idText should be defined and linked in your FXML
                pstmt.setString(1, Pat);

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Search_List_Medical.add(new MedicalState(rs.getString("Patient_Id"), rs.getString("Patient_name"), rs.getString("M_medical_state"), rs.getDate("Last_visited_date")));
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
            return Search_List_Medical;
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
