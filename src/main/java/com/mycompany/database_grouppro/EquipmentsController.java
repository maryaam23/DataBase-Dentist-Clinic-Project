/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database_grouppro;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EquipmentsController implements Initializable {

    private String UserName_SqlWork = "root";
    private String Password_SqlWork = "2312003";
    private String URL_SqlWork = "jdbc:mysql://127.0.0.1:3306/smileclinic";
    private Connection con;

    @FXML
    private Button Add_Button_Equip;

    @FXML
    private Button Delete_Button_Equip;

    @FXML
    private TableColumn<Equipments, String> Equipment_Description;

    @FXML
    private TextField Equipment_DescriptionT;

    @FXML
    private TableColumn<Equipments, String> Equipment_Name;

    @FXML
    private TextField Equipment_NameT;

    @FXML
    private TableColumn<Equipments, Integer> Equipment_id;

    @FXML
    private TableView<Equipments> TableEquip;

    @FXML
    private TextField Equipment_idT;

    @FXML
    private Button Refresh_Button;

    @FXML
    private Button ReturnCoverPage;

    @FXML
    private Button Search_Button_Equi;

    @FXML
    private Button countEquipmentButton;

    @FXML
    private Label countLabel;

    private void initializeDB() {
        try {
            con = DriverManager.getConnection(URL_SqlWork, UserName_SqlWork, Password_SqlWork);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SORT(ActionEvent event) {
        TableEquip.getSortOrder().add(Equipment_id);
        Equipment_id.setSortType(TableColumn.SortType.DESCENDING);
        TableEquip.sort();
    }

    @FXML
    void SORTnameasc(ActionEvent event) {

        Equipment_Name.setSortType(TableColumn.SortType.ASCENDING);
        TableEquip.getSortOrder().setAll(Equipment_Name); // Clears previous sort orders and sets new one
        TableEquip.sort();
    }

    @FXML
    void SORTnamedec(ActionEvent event) {
        Equipment_Name.setSortType(TableColumn.SortType.DESCENDING);
        TableEquip.getSortOrder().setAll(Equipment_Name); // Clears previous sort orders and sets new one
        TableEquip.sort();
    }

    @FXML
    void Add_Button_Equip(ActionEvent event) {
        boolean found = false;

        try {
            String equipmentName = Equipment_NameT.getText(); // Get the name from the text field
            // Loop through the list to check if the equipment name already exists
            for (Equipments D : EQUIlist) {
                // Assuming getEquipment_Name() returns a String
                if (D.getEquipment_Name().equals(equipmentName)) {
                    
                    found = true;
                    break;
                }
            }

            if (found) {
                // If found, show an alert and do not add a new entry
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Error, The Equipment Name already exists");
                error.showAndWait();
            } else {
                // If not found, add the new equipment
                String SQL = "INSERT INTO Equipments (Equipment_id, Equipment_Name, Equipment_Description) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(SQL);
                preparedStatement.setInt(1, Integer.parseInt(Equipment_idT.getText()));
                preparedStatement.setString(2, equipmentName);
                preparedStatement.setString(3, Equipment_DescriptionT.getText());
                preparedStatement.executeUpdate();

                // Refresh the list
                getDataEquipments(1);
            }
        } catch (NumberFormatException e) {
            // Handle number format exception if the text is not a valid integer
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Error, The Equipment ID must be a number");
            error.showAndWait();
        } catch (SQLException | ClassNotFoundException e) {
            // Handle SQL and ClassNotFound exceptions
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Error executing the insert operation");
            error.showAndWait();
        }
    }

    @FXML

    void Delete_Button_Equip(ActionEvent event) {
        boolean found = false;
        int equipmentId = 0;
        try {
            equipmentId = Integer.parseInt(Equipment_idT.getText()); // Parse once and use the variable
            for (Equipments D : EQUIlist) {
                // Assuming getEquipment_id() returns an integer. If it returns a string, compare using .equals()
                if (D.getEquipment_id() == equipmentId) {
                    Equipment_NameT.setText(D.getEquipment_Name());
                    Equipment_DescriptionT.setText(D.getEquipment_Description());
                    found = true;
                    break;
                }
            }

            if (!found) {
                // If not found, show alert and do not delete
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Error, The Equipment ID Not Found");
                error.showAndWait();
            } else {
                // If found, delete the equipment
                String SQL = "DELETE FROM Equipments WHERE Equipment_id = ?";
                PreparedStatement preparedStatement = con.prepareStatement(SQL);
                preparedStatement.setInt(1, equipmentId);
                preparedStatement.executeUpdate();

                // Refresh the list
                getDataEquipments(1);
            }
        } catch (NumberFormatException e) {
            // Handle number format exception if the text is not a valid integer
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Error, The Equipment ID must be a number");
            error.showAndWait();
        } catch (SQLException | ClassNotFoundException e) {
            // Handle SQL and ClassNotFound exceptions
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Error executing the delete operation");
            error.showAndWait();
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
            e.printStackTrace();
        }
    }

    @FXML
    void countEqui(ActionEvent event) {

        int numberOfEq = EQUIlist.size();
        countLabel.setText("Is: " + numberOfEq);
    }

    @FXML
    void Refresh_Button(ActionEvent event) {

    }

    @FXML
    void Search_Button_Equi(ActionEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeDB();

        Equipment_id.setCellValueFactory(new PropertyValueFactory<Equipments, Integer>("Equipment_id"));
        Equipment_Name.setCellValueFactory(new PropertyValueFactory<Equipments, String>("Equipment_Name"));
        Equipment_Description.setCellValueFactory(new PropertyValueFactory<Equipments, String>("Equipment_Description"));

        try {

            EQUIlist = getDataEquipments(1);
            TableEquip.setItems(EQUIlist);

            // connectDB();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Refresh_Button.setOnAction((ActionEvent e) -> {

            try {

                EQUIlist = getDataEquipments(1);
                TableEquip.setItems(EQUIlist);

                // connectDB();
            } catch (ClassNotFoundException | SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Equipment_DescriptionT.clear();
            Equipment_idT.clear();
            Equipment_NameT.clear();

            // Resetting the countLabel to display an empty message or default message
            countLabel.setText("Is: "); // Or any default message you prefer
            TableEquip.getSortOrder().clear();
            TableEquip.refresh();

        });

        Search_Button_Equi.setOnAction((ActionEvent e1) -> {

            boolean found = false;
            for (Equipments E : EQUIlist) {
                if (E.getEquipment_id() == Integer.parseInt(Equipment_idT.getText())) {
                    Equipment_NameT.setText(E.getEquipment_Name());
                    Equipment_DescriptionT.setText(E.getEquipment_Description());
                    found = true;
                    break;

                }
            }
            if (found == false) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(" Error, The Equipment ID Not Found");
                error.showAndWait();
            } else {
                ObservableList<Equipments> search = FXCollections.observableArrayList();
                TableEquip.setItems(search);
                try {
                    TableEquip.setItems(getDataEquipments(2));
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    ObservableList<Equipments> EQUIlist = FXCollections.observableArrayList();
    ObservableList<Equipments> searchEqui_ID = FXCollections.observableArrayList();

    public ObservableList<Equipments> getDataEquipments(int i) throws ClassNotFoundException, SQLException {
        String SQL;
        initializeDB();
        if (i == 1) {
            try {
                EQUIlist.clear();
                SQL = "select * from Equipments";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    EQUIlist.add(new Equipments(rs.getInt("Equipment_id"), rs.getString("Equipment_Name"), rs.getString("Equipment_Description")));
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
            return EQUIlist;
        }

        if (i == 2) {
            searchEqui_ID.clear();

            try {
                SQL = "select * from Equipments  where Equipment_id=" + Integer.parseInt(Equipment_idT.getText()) + ";";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    searchEqui_ID.add(new Equipments(rs.getInt("Equipment_id"), rs.getString("Equipment_Name"), rs.getString("Equipment_Description")));
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }

            return searchEqui_ID;
        }

        if (i == 3) {
            SQL = "delete FROM Equipments WHERE Equipment_id=";
            ExecuteStatement(SQL);
            getDataEquipments(1);
        }
        return EQUIlist;

    }
}
