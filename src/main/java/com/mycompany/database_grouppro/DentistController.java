package com.mycompany.database_grouppro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.*;
import javafx.collections.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.Period;

import java.net.*;
import static java.nio.file.Files.list;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DentistController implements Initializable {

    private String URL_GData;
    private String UserName_SqlWork = "root";
    private String Password_SqlWork = "2312003";
    private String URL_SqlWork = "127.0.0.1";
    private String Port_SqlWork = "3306";
    private String Name_SqlWork = "smileclinic";
    private Connection con;

    @FXML
    private Button searchBy_name;

    @FXML
    private Button search_BySpec;

    @FXML
    private Button agebuttton;

    @FXML
    private Label agelabel;

    @FXML
    private Button Add_Button_Dentist;

    @FXML
    private TextField addressadd;

    @FXML
    private DatePicker birthadd;

    @FXML
    private TextField emailadd;

    @FXML
    private TextField nameadd;

    @FXML
    private TextField phoneadd;

    @FXML
    private TextField specialadd;

    @FXML
    private Button countDoctorsButton;

    @FXML
    private Label countLabel;

    @FXML
    private Button Refresh_Button;

    @FXML
    private Button DeleteIdDentistList_Button;

    @FXML
    private TextField DentisIdPut;

    @FXML
    private TableColumn<Dentist, String> Dentist_Address;

    @FXML
    private TableColumn<Dentist, Date> Dentist_Birthday;

    @FXML
    private TableColumn<Dentist, String> Dentist_Email;

    @FXML
    private TableColumn<Dentist, String> Dentist_Name;

    @FXML
    private TableColumn<Dentist, String> Dentist_PhoneNum;

    @FXML
    private TableColumn<Dentist, String> Dentist_Specialization;

    @FXML
    private TableColumn<Dentist, String> Dentist_id;

    @FXML
    private Button ReturnCoverPage;

    @FXML
    private Button SearchId_DentistListButton;

    @FXML
    private TableView<Dentist> tableDentistList;

    @FXML
    void Add_Button_Dentist(ActionEvent event) {

        try {
            boolean found = false;
            for (Dentist D : list) {
                if (D.getDentist_id().equals(DentisIdPut.getText())) {
                   nameadd.setText(D.getDentist_Name());
                    found = true;
                    break;

                }
            }
            if (found == true) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(" Error, The dentist ID Is Already Found");
                error.showAndWait();
            } else {
                String SQL = "INSERT INTO dentist (Dentist_id, Dentist_Name,Dentist_Birthday, Dentist_Email,Dentist_phoneNum, Dentist_Address,Dentist_specialization ) VALUES (?, ?, ?,?, ?, ?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(SQL);
                preparedStatement.setString(1, DentisIdPut.getText().trim());
                preparedStatement.setString(2, nameadd.getText().trim());
                preparedStatement.setDate(3, java.sql.Date.valueOf(birthadd.getValue()));
                preparedStatement.setString(4, emailadd.getText().trim());
                preparedStatement.setString(5, phoneadd.getText().trim());
                preparedStatement.setString(6, addressadd.getText().trim());
                preparedStatement.setString(7, specialadd.getText().trim());
                preparedStatement.executeUpdate();
                getDataDentist(1); // Refresh the list
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DeleteIdDentistList_Button(ActionEvent event) {

        try {
            String SQL = "delete FROM dentist WHERE Dentist_id = ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            String dentistId = DentisIdPut.getText(); // Get the text as a string
            pstmt.setString(1, dentistId); // Set the string directly

            pstmt.executeUpdate();
            getDataDentist(1); // Refresh the list
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void countDoctors(ActionEvent event) {
        int numberOfDoctors = list.size();
        countLabel.setText("Is: " + numberOfDoctors);

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
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Dentist_id.setCellValueFactory(new PropertyValueFactory<Dentist, String>("Dentist_id"));
        Dentist_Name.setCellValueFactory(new PropertyValueFactory<Dentist, String>("Dentist_Name"));
        Dentist_Birthday.setCellValueFactory(new PropertyValueFactory<Dentist, Date>("Dentist_Birthday"));
        Dentist_Email.setCellValueFactory(new PropertyValueFactory<Dentist, String>("Dentist_Email"));
        Dentist_PhoneNum.setCellValueFactory(new PropertyValueFactory<Dentist, String>("Dentist_phoneNum"));
        Dentist_Address.setCellValueFactory(new PropertyValueFactory<Dentist, String>("Dentist_Address"));
        Dentist_Specialization.setCellValueFactory(new PropertyValueFactory<Dentist, String>("Dentist_specialization"));

        try {

            list = getDataDentist(1);
            tableDentistList.setItems(list);

            SearchId_DentistListButton.setOnAction((ActionEvent e) -> {
                boolean found = false;
                for (Dentist D : list) {
                    if (D.getDentist_id().equals(DentisIdPut.getText())) {
                        nameadd.setText(D.getDentist_Name());
                        found = true;
                        break;

                    }
                }
                if (found == false) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText(" Error, The dentist ID Not Found");
                    error.showAndWait();
                } else {

                    ObservableList<Dentist> re = FXCollections.observableArrayList();
                    tableDentistList.setItems(re);
                    try {
                        tableDentistList.setItems(getDataDentist(2));
                        int numberOfDoctors1 = Search_Den_By_ID.size();
                        countLabel.setText("Is: " + numberOfDoctors1);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            });
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        Refresh_Button.setOnAction((ActionEvent e) -> {
            try {
                list = getDataDentist(1);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            tableDentistList.setItems(list);
            // Clearing the DentistIdPut TextField
            DentisIdPut.clear();
            nameadd.clear();
            birthadd.setValue(null);
            emailadd.clear();
            addressadd.clear();
            phoneadd.clear();
            specialadd.clear();
            agelabel.setText("Is: ");
            // Resetting the countLabel to display an empty message or default message
            countLabel.setText("Is: "); // Or any default message you prefer
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

        searchBy_name.setOnAction((ActionEvent e) -> {

            boolean found = false;

            for (Dentist D : list) {
                if (D.getDentist_Name().equals(nameadd.getText())) {
                    DentisIdPut.setText(D.getDentist_id());
                    found = true;
                    break;

                }
            }
            if (found == false) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(" Error, The dentist Name Not Found");
                error.showAndWait();
            } else {
                int numberOfDoctors1 = Search_Den_By_ID.size();
                countLabel.setText("Is: " + numberOfDoctors1);
                ObservableList<Dentist> re = FXCollections.observableArrayList();
                tableDentistList.setItems(re);
                try {
                    tableDentistList.setItems(getDataDentist(4));
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        agebuttton.setOnAction((ActionEvent e) -> {
            LocalDate currentDate = LocalDate.now(); // Get the current date

            for (Dentist dentist : Search_Den_By_ID) {
                Date birthday = (Date) dentist.getDentist_Birthday(); // Assuming this returns a java.sql.Date
                LocalDate birthdateLocal = birthday.toLocalDate(); // Convert to LocalDate

                // Calculate age
                int age = Period.between(birthdateLocal, currentDate).getYears();

                // Print out the age or update the dentist object
                agelabel.setText("Is: " + age);

            }
        });

        search_BySpec.setOnAction((ActionEvent e) -> {

            boolean found = false;

            for (Dentist D : list) {
                if (D.getDentist_specialization().equals(specialadd.getText())) {
                    found = true;
                    break;

                }
            }
            if (found == false) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(" Error, The Specialaization  Not Found");
                error.showAndWait();
            } else {

                ObservableList<Dentist> re = FXCollections.observableArrayList();
                tableDentistList.setItems(re);
                try {
                    tableDentistList.setItems(getDataDentist(5));
                    int numberOfDoctors2 = Search_Den_By_spec.size();

                    countLabel.setText("Is: " + numberOfDoctors2);

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

    }

    public void ExecuteStatement(String SQL) throws SQLException {

        try {
            Statement Statment_SQL = con.createStatement();
            Statment_SQL.executeUpdate(SQL);
            Statment_SQL.close();

        } catch (SQLException s) {
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");

        }

    }

    ObservableList<Dentist> list = FXCollections.observableArrayList();
    ObservableList<Dentist> Search_Den_By_ID = FXCollections.observableArrayList();
    ObservableList<Dentist> Search_Den_By_spec = FXCollections.observableArrayList();

    public ObservableList<Dentist> getDataDentist(int i) throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();
        if (i == 1) {
            try {
                list.clear();
                SQL = "select * from dentist";
                Statement Dstat = con.createStatement();
                ResultSet Resdent = Dstat.executeQuery(SQL);
                while (Resdent.next()) {
                    list.add(new Dentist(Resdent.getString("Dentist_id"), Resdent.getString("Dentist_Name"), Resdent.getDate("Dentist_Birthday"), Resdent.getString("Dentist_Email"), Resdent.getString("Dentist_phoneNum"), Resdent.getString("Dentist_Address"), Resdent.getString("Dentist_specialization")));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        } else if (i == 2) {
            Search_Den_By_ID.clear();
            SQL = "SELECT * FROM dentist WHERE Dentist_id = ?";
            try {
                // Assuming 'con' is your active database connection object
                PreparedStatement pstmt = con.prepareStatement(SQL);

                String dentistId = DentisIdPut.getText(); // Dentist_idText should be defined and linked in your FXML
                pstmt.setString(1, dentistId);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    // Add the dentist details to your list here
                    // Make sure the Dentist class has a constructor that matches these parameters
                    Search_Den_By_ID.add(new Dentist(
                            rs.getString("Dentist_id"),
                            rs.getString("Dentist_Name"),
                            rs.getDate("Dentist_Birthday"),
                            rs.getString("Dentist_Email"),
                            rs.getString("Dentist_phoneNum"),
                            rs.getString("Dentist_Address"),
                            rs.getString("Dentist_specialization")
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace(); // Replace with more sophisticated error handling
            }
            return Search_Den_By_ID;
        }
        if (i == 3) {
            SQL = "delete FROM dentist WHERE Dentist_id = ?";
            ExecuteStatement(SQL);
            getDataDentist(1);
            return list;
        }

        if (i == 4) {
            Search_Den_By_ID.clear();
            SQL = "SELECT * FROM dentist WHERE Dentist_Name = ?";
            try {
                // Assuming 'con' is your active database connection object
                PreparedStatement pstmt = con.prepareStatement(SQL);

                String name = nameadd.getText();
                pstmt.setString(1, name);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    // Add the dentist details to your list here
                    // Make sure the Dentist class has a constructor that matches these parameters
                    Search_Den_By_ID.add(new Dentist(
                            rs.getString("Dentist_id"),
                            rs.getString("Dentist_Name"),
                            rs.getDate("Dentist_Birthday"),
                            rs.getString("Dentist_Email"),
                            rs.getString("Dentist_phoneNum"),
                            rs.getString("Dentist_Address"),
                            rs.getString("Dentist_specialization")
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace(); // Replace with more sophisticated error handling
            }
            return Search_Den_By_ID;

        }
        if (i == 5) {
            Search_Den_By_spec.clear();
            SQL = "SELECT * FROM dentist WHERE Dentist_specialization = ?";
            try {
                // Assuming 'con' is your active database connection object
                PreparedStatement pstmt = con.prepareStatement(SQL);

                String name = specialadd.getText();
                pstmt.setString(1, name);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    // Add the dentist details to your list here
                    // Make sure the Dentist class has a constructor that matches these parameters
                    Search_Den_By_spec.add(new Dentist(
                            rs.getString("Dentist_id"),
                            rs.getString("Dentist_Name"),
                            rs.getDate("Dentist_Birthday"),
                            rs.getString("Dentist_Email"),
                            rs.getString("Dentist_phoneNum"),
                            rs.getString("Dentist_Address"),
                            rs.getString("Dentist_specialization")
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace(); // Replace with more sophisticated error handling
            }
            return Search_Den_By_spec;

        }
        return list;
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
