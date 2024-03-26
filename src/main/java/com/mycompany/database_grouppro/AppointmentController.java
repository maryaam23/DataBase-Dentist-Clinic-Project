package com.mycompany.database_grouppro;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

public class AppointmentController implements Initializable {

    private String URL_GData;
    private String UserName_SqlWork = "root";
    private String Password_SqlWork = "2312003";
    private String URL_SqlWork = "127.0.0.1";
    private String Port_SqlWork = "3306";
    private String Name_SqlWork = "smileclinic";
    private Connection con;

    @FXML
    private TableView<Appointment> AppointmentTable;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add_Button_Appo;

    @FXML
    private TableColumn<Appointment, LocalDate> Appointment_Date;

    @FXML
    private DatePicker Appointment_DateA;

    @FXML
    private TableColumn<Appointment, LocalTime> Appointment_Time;

    @FXML
    private TextField Appointment_TimeA;

    @FXML
    private TableColumn<Appointment, Integer> Appointment_id;

    @FXML
    private TextField Appointment_idA;

    @FXML
    private Button Delete_Button_Appo;

    @FXML
    private TableColumn<Appointment, String> Dentist_Name;

    @FXML
    private TextField Dentist_NameA;

    @FXML
    private TableColumn<Appointment, String> Dentist_id;

    @FXML
    private TextField Dentist_idA;

    @FXML
    private TableColumn<Appointment, String> Patient_id;

    @FXML
    private TextField Patient_idA;

    @FXML
    private TableColumn<Appointment, String> Patient_name;

    @FXML
    private TextField Patient_nameA;

    @FXML
    private Button Refresh_Button;

    @FXML
    private Button ReturnCoverPage;

    @FXML
    private Button Search_Button_Appo;

    @FXML
    private Button Search_Button_Appo1;

    @FXML
    private Button Search_Button_Appo2;

    @FXML
    private Button Search_Button_Appo3;

    @FXML
    private Button Update_Button_Appo;

    @FXML
    private Button DateSort;

    @FXML
    private Button TimeSort;

    private void initializeDB() {
        try {
            con = DriverManager.getConnection(URL_SqlWork, UserName_SqlWork, Password_SqlWork);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Add_Button_Appo(ActionEvent event) {
        for (Appointment a : list) {
                    if (a.getPatient_id().equals(Patient_idA.getText())) {
                        Patient_nameA.setText(a.getPatient_name());
                    }if (a.getDentist_id().equals(Dentist_idA.getText())) {
                        Dentist_NameA.setText(a.getDentist_Name());
                    }
                }
        try {
            boolean found = false;
            for (Appointment a : list) {
                int appointmentId = Integer.parseInt(Appointment_idA.getText());
                if (a.getAppointment_id() == appointmentId) {
                    Dentist_NameA.setText(a.getDentist_Name());
                    Patient_nameA.setText(a.getPatient_name());
                    found = true;
                    break;
                }
            }
           
            if (found == true) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(" Error, The Appointment ID Is Already Exist");
                error.showAndWait();
            } else {
                String SQL = "INSERT INTO dentist_patient_appointment (Appointment_id, Appointment_Date,Appointment_Time, Patient_id,Patient_name, Dentist_id,Dentist_Name ) VALUES (?, ?, ?,?, ?, ?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(SQL);
                preparedStatement.setInt(1, Integer.parseInt(Appointment_idA.getText()));
                preparedStatement.setDate(2, java.sql.Date.valueOf(Appointment_DateA.getValue()));
                preparedStatement.setTime(3, java.sql.Time.valueOf(LocalTime.parse(Appointment_TimeA.getText().trim())));
                preparedStatement.setString(4, Patient_idA.getText().trim());
                preparedStatement.setString(5, Patient_nameA.getText().trim());
                preparedStatement.setString(6, Dentist_idA.getText().trim());
                preparedStatement.setString(7, Dentist_NameA.getText().trim());
                preparedStatement.executeUpdate();
                getDataAppointment(1); // Refresh the list
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void Delete_Button_Appo(ActionEvent event) {

    }

    @FXML
    void Refresh_Button(ActionEvent event) {

    }

    @FXML
    void ReturnCoverPage(ActionEvent event) {

    }

    @FXML
    void Search_Button_Appo(ActionEvent event) {

    }

    @FXML
    void Search_Button_Appo1(ActionEvent event) {

    }

    @FXML
    void Search_Button_Appo2(ActionEvent event) {

    }

    @FXML
    void Search_Button_Appo3(ActionEvent event) {

    }

    @FXML
    void Update_Button_Appo(ActionEvent event) {

    }

    @FXML
    void TimeSort(ActionEvent event) {
        AppointmentTable.getSortOrder().add(Appointment_Time);
        Appointment_Time.setSortType(TableColumn.SortType.DESCENDING);
        AppointmentTable.sort();

    }

    @FXML
    void DateSort(ActionEvent event) {
        AppointmentTable.getSortOrder().add(Appointment_Date);
        Appointment_Date.setSortType(TableColumn.SortType.DESCENDING);
        AppointmentTable.sort();

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeDB();
        Appointment_id.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("Appointment_id"));
        Appointment_Date.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("Appointment_Date"));
        Appointment_Time.setCellValueFactory(new PropertyValueFactory<Appointment, LocalTime>("Appointment_Time"));
        Patient_id.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Patient_id"));
        Patient_name.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Patient_name"));
        Dentist_id.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Dentist_id"));
        Dentist_Name.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Dentist_Name"));

        try {
            list = getDataAppointment(1);
            AppointmentTable.setItems(list);

            final LocalDate[] date = new LocalDate[1];
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    // get the date picker value
                    date[0] = Appointment_DateA.getValue();

                    // get the selected date
                    System.out.println(date[0]);
                }
            };
            Appointment_DateA.setOnAction(event);

            Search_Button_Appo.setOnAction((ActionEvent e) -> {//search by id
                for (Appointment a : list) {
                    if (a.getPatient_id().equals(Patient_idA.getText())) {
                        Patient_nameA.setText(a.getPatient_name());
                    }
                }
                ObservableList<Appointment> re = FXCollections.observableArrayList();
                AppointmentTable.setItems(re);
                try {
                    if (Appointment_idA.getText() != null) {
                        AppointmentTable.setItems(getDataAppointment(2));
                    }
                    if (java.sql.Date.valueOf(Appointment_DateA.getValue()) != null) {
                        AppointmentTable.setItems(getDataAppointment(3));
                    }

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            });

            Search_Button_Appo1.setOnAction((ActionEvent e) -> {//search by id
                for (Appointment a : list) {
                    if (a.getDentist_id().equals(Dentist_idA.getText())) {
                        Dentist_NameA.setText(a.getDentist_Name());
                    }
                }
                boolean found = false;
                for (Appointment a : list) {
                    if (a.getDentist_id().equals(Dentist_idA.getText())) {

                        found = true;
                        break;

                    }
                }
                if (found == false) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText(" Error, The Dentist ID Not Found");
                    error.showAndWait();
                } else {

                    ObservableList<Appointment> re = FXCollections.observableArrayList();
                    AppointmentTable.setItems(re);
                    try {
                        if (Dentist_idA.getText() != null) {
                            AppointmentTable.setItems(getDataAppointment(6));
                        }

                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            });
            Search_Button_Appo2.setOnAction((ActionEvent e) -> {//search by id

                for (Appointment a : list) {
                    if (a.getPatient_id().equals(Patient_idA.getText())) {
                        Patient_nameA.setText(a.getPatient_name());
                    }
                }
                boolean found = false;
                for (Appointment a : list) {
                    if (a.getPatient_id().equals(Patient_idA.getText())) {

                        found = true;
                        break;

                    }
                }
                if (found == false) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText(" Error, The Patient ID Not Found");
                    error.showAndWait();
                } else {
                    ObservableList<Appointment> re = FXCollections.observableArrayList();
                    AppointmentTable.setItems(re);
                    try {
                        if (Patient_idA.getText() != null) {
                            AppointmentTable.setItems(getDataAppointment(7));
                        }

                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            });

            Search_Button_Appo3.setOnAction((ActionEvent e) -> {//search by id
                for (Appointment a : list) {
                    if (a.getDentist_id().equals(Dentist_idA.getText())) {
                        Dentist_NameA.setText(a.getDentist_Name());
                    }
                }
                boolean found = false;
                for (Appointment a : list) {
                    if (a.getDentist_id().equals(Dentist_idA.getText())) {

                        found = true;
                        break;

                    }
                }
                if (found == false) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText(" Error, The Dentist ID Not Found");
                    error.showAndWait();
                } else {
                    ObservableList<Appointment> re = FXCollections.observableArrayList();
                    AppointmentTable.setItems(re);
                    try {
                        if (Dentist_idA.getText() != null && (java.sql.Date.valueOf(Appointment_DateA.getValue()) != null)) {
                            AppointmentTable.setItems(getDataAppointment(8));
                        }

                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            });

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Update_Button_Appo.setOnAction((ActionEvent e) -> { //Patient Id & Appointement Id
            for (Appointment a : list) {
                if (a.getPatient_id().equals(Patient_idA.getText())) {
                    Patient_nameA.setText(a.getPatient_name());
                }
            }
            boolean found = false;
            for (Appointment a : list) {
                if (a.getPatient_id().equals(Patient_idA.getText())) {

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
                    list = getDataAppointment(1);
                    AppointmentTable.setItems(getDataAppointment(4));
                    Appointment_idA.clear();
                    Appointment_TimeA.clear();
                    Patient_nameA.clear();
                    Patient_idA.clear();
                    Dentist_NameA.clear();
                    Dentist_idA.clear();
                    Appointment_DateA.setValue(null);

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                AppointmentTable.setItems(list);
            }
        });
        Delete_Button_Appo.setOnAction((ActionEvent e) -> {
            for (Appointment a : list) {
                if (a.getPatient_id().equals(Patient_idA.getText())) {
                    Patient_nameA.setText(a.getPatient_name());
                }
            }

            try {
                AppointmentTable.setItems(getDataAppointment(5));
                Appointment_idA.clear();
                Appointment_TimeA.clear();
                Patient_nameA.clear();
                Patient_idA.clear();
                Dentist_NameA.clear();
                Dentist_idA.clear();
                Appointment_DateA.setValue(null);

            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
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
        Refresh_Button.setOnAction((ActionEvent e) -> {
            try {
                list = getDataAppointment(1);
                Appointment_idA.clear();
                Appointment_TimeA.clear();
                Patient_nameA.clear();
                Patient_idA.clear();
                Dentist_NameA.clear();
                Dentist_idA.clear();
                Appointment_DateA.setValue(null);
                AppointmentTable.getSortOrder().clear();
                AppointmentTable.refresh();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            AppointmentTable.setItems(list);
        });

    }

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

    ObservableList<Appointment> list = FXCollections.observableArrayList();
    ObservableList<Appointment> searchList = FXCollections.observableArrayList();
    ObservableList<Appointment> searchListByDate = FXCollections.observableArrayList();

    public ObservableList<Appointment> getDataAppointment(int i) throws ClassNotFoundException, SQLException {
        String SQL;
        connectDB();

        if (i == 1) {
            try {
                list.clear(); // Clear the existing list
                SQL = "SELECT * FROM dentist_patient_appointment"; // SQL query to select all appointments
                Statement stmt = con.createStatement(); // Create a statement
                ResultSet rs = stmt.executeQuery(SQL); // Execute the query

                while (rs.next()) {
                    // Extract data for each column from the ResultSet
                    // Replace the method calls like 'rs.getString()' with the appropriate ones for your data types
                    list.add(new Appointment(
                            rs.getInt("Appointment_id"),
                            rs.getDate("Appointment_Date").toLocalDate(), // Converts SQL Date to LocalDate
                            rs.getTime("Appointment_Time").toLocalTime(), // Converts SQL Time to LocalTime
                            rs.getString("Patient_id"), // Assuming the patientID is a String
                            rs.getString("Patient_name"), // Assuming the patientName is a String
                            rs.getString("Dentist_id"), // Assuming the doctorID is a String
                            rs.getString("Dentist_Name") // Assuming the doctorName is a String

                    ));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;

        }
        if (i == 2) {
            searchList.clear();
            try {
                SQL = "select * from dentist_patient_appointment where Appointment_id =" + Integer.valueOf(Appointment_idA.getText());
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    searchList.add(new Appointment(
                            rs.getInt("Appointment_id"),
                            rs.getDate("Appointment_Date").toLocalDate(), // Converts SQL Date to LocalDate
                            rs.getTime("Appointment_Time").toLocalTime(), // Converts SQL Time to LocalTime
                            rs.getString("Patient_id"), // Assuming the patientID is a String
                            rs.getString("Patient_name"), // Assuming the patientName is a String
                            rs.getString("Dentist_id"), // Assuming the doctorID is a String
                            rs.getString("Dentist_Name")
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return searchList;
        }

        if (i == 3) {
            searchListByDate.clear();
            try {
                SQL = "select * from dentist_patient_appointment where Appointment_Date = '" + Appointment_DateA.getValue() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    searchListByDate.add(new Appointment(
                            rs.getInt("Appointment_id"),
                            rs.getDate("Appointment_Date").toLocalDate(), // Converts SQL Date to LocalDate
                            rs.getTime("Appointment_Time").toLocalTime(), // Converts SQL Time to LocalTime
                            rs.getString("Patient_id"), // Assuming the patientID is a String
                            rs.getString("Patient_name"), // Assuming the patientName is a String
                            rs.getString("Dentist_id"), // Assuming the doctorID is a String
                            rs.getString("Dentist_Name")
                    ));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return searchListByDate;
        }
        if (i == 4) {
            if (Appointment_TimeA.getText() != null && !Appointment_TimeA.getText().trim().isEmpty()) {
                String sql = "UPDATE dentist_patient_appointment SET Appointment_Time=?, Appointment_Date=? WHERE Patient_id=? AND Appointment_id=?";
                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                    pstmt.setTime(1, Time.valueOf(LocalTime.parse(Appointment_TimeA.getText().trim())));
                    pstmt.setDate(2, java.sql.Date.valueOf(Appointment_DateA.getValue()));
                    pstmt.setString(3, Patient_idA.getText());
                    pstmt.setInt(4, Integer.parseInt(Appointment_idA.getText().trim()));
                    pstmt.executeUpdate();
                    // Refresh your list here to reflect the change
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return list;

        }

        if (i == 5) {//delete by date and PID
            String sql = "DELETE FROM dentist_patient_appointment WHERE Patient_id=? AND Appointment_Date=?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, Patient_idA.getText());
                LocalDate localDate = Appointment_DateA.getValue();
                if (localDate != null) {
                    pstmt.setDate(2, java.sql.Date.valueOf(localDate));
                    pstmt.executeUpdate();
                    return list;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (i == 6) {
            searchListByDate.clear();
            try {
                SQL = "select * from dentist_patient_appointment where Dentist_id = '" + Dentist_idA.getText() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    searchListByDate.add(new Appointment(
                            rs.getInt("Appointment_id"),
                            rs.getDate("Appointment_Date").toLocalDate(), // Converts SQL Date to LocalDate
                            rs.getTime("Appointment_Time").toLocalTime(), // Converts SQL Time to LocalTime
                            rs.getString("Patient_id"), // Assuming the patientID is a String
                            rs.getString("Patient_name"), // Assuming the patientName is a String
                            rs.getString("Dentist_id"), // Assuming the doctorID is a String
                            rs.getString("Dentist_Name")
                    ));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return searchListByDate;
        }

        if (i == 7) {
            searchListByDate.clear();
            try {
                SQL = "select * from dentist_patient_appointment where Patient_id = '" + Patient_idA.getText() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    searchListByDate.add(new Appointment(
                            rs.getInt("Appointment_id"),
                            rs.getDate("Appointment_Date").toLocalDate(), // Converts SQL Date to LocalDate
                            rs.getTime("Appointment_Time").toLocalTime(), // Converts SQL Time to LocalTime
                            rs.getString("Patient_id"), // Assuming the patientID is a String
                            rs.getString("Patient_name"), // Assuming the patientName is a String
                            rs.getString("Dentist_id"), // Assuming the doctorID is a String
                            rs.getString("Dentist_Name")
                    ));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return searchListByDate;
        }
        if (i == 8) {
            searchListByDate.clear();
            try {
                SQL = "SELECT * FROM dentist_patient_appointment WHERE Dentist_id = '" + Dentist_idA.getText() + "' AND Appointment_Date = '" + Appointment_DateA.getValue() + "'";

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()) {
                    searchListByDate.add(new Appointment(
                            rs.getInt("Appointment_id"),
                            rs.getDate("Appointment_Date").toLocalDate(), // Converts SQL Date to LocalDate
                            rs.getTime("Appointment_Time").toLocalTime(), // Converts SQL Time to LocalTime
                            rs.getString("Patient_id"), // Assuming the patientID is a String
                            rs.getString("Patient_name"), // Assuming the patientName is a String
                            rs.getString("Dentist_id"), // Assuming the doctorID is a String
                            rs.getString("Dentist_Name")
                    ));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return searchListByDate;
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
// 
