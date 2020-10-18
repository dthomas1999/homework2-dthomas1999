package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;


public class Controller {
    @FXML
    private Button createB;

    @FXML
    private Button populateB;

    @FXML
    private Button deleteB;

    @FXML
    private Button loadB;

    @FXML
    private ListView<Patient> patientListView;

    final String SQL_URL = "jdbc:mysql://localhost:3306/test";

    private void createTable(String url) {
        try {
            Connection conn = DriverManager.getConnection(url, "root", "password");
            Statement stmt = conn.createStatement();
            try {
                stmt.execute("CREATE TABLE Patients (" +
                        "Name CHAR(25), " +
                        "Age Int(5), " +
                        "Gender CHAR(5), " +
                        "BloodType CHAR(5), " +
                        "Weight Int(5), " +
                        "Height CHAR(5), " +
                        "PatientID VARCHAR(36) )" );

                System.out.println("TABLE CREATED");
            } catch (Exception ex) {
                String msg = ex.getMessage();
                System.out.println(msg);
            }

            stmt.close();
            conn.close();

        } catch (Exception ex) {
            String msg = ex.getMessage();
            System.out.println(msg);
        }
    }


    private void populateTable (String url) {
        try {
            Connection conn = DriverManager.getConnection(url, "root", "password");
            Statement stmt = conn.createStatement();

            for (int i = 0; i < 9; i++) {
                UUID id = UUID.randomUUID();
                String patientID = id.toString();
                String patientName = "John Doe";
                String patientAge = "27";
                String gender = "M";
                String bloodType = "O";
                String weight = "180";
                String height = "5";


                String sql = "INSERT INTO Patients VALUES" +
                        "('" + patientName + "', '" + patientAge + "', '" + gender + "', '" + bloodType + "', '" + weight + "', '" + height + "', '" + patientID + "')";

                stmt.executeUpdate(sql);

            }
            System.out.println("TABLE FILLED");

            stmt.close();
            conn.close();

        } catch (Exception ex) {
            String msg = ex.getMessage();
            System.out.println(msg);
        }
    }


    private void deleteTable(String url) {
        try {
            Connection conn = DriverManager.getConnection(url, "root", "password");
            Statement stmt = conn.createStatement();

            stmt.execute("DROP TABLE Patients");


            stmt.close();
            conn.close();

            System.out.println("TABLE DELETED");


        } catch (Exception ex) {
            String msg = ex.getMessage();
            System.out.println(msg);
        }



    }

    private  void loadTable (String url) {
        try {
            Connection conn = DriverManager.getConnection(url, "root", "password");
            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM Patients";
            ResultSet result = stmt.executeQuery(sqlStatement);
            ObservableList<Patient> dbPatients = FXCollections.observableArrayList();
            while (result.next()) {

                Patient patient = new Patient();
                patient.setPatientName(result.getString("Name"));
                patient.setPatientAge(result.getInt("Age"));
                patient.setGender(result.getString("Gender"));
                patient.setBloodType(result.getString("BloodType"));
                patient.setWeight(result.getInt("Weight"));
                patient.setHeight(result.getString("Height"));
                patient.patientID = UUID.fromString(result.getString("PatientID"));
                dbPatients.add(patient);
            }

            patientListView.setItems(dbPatients);

            System.out.println("DATA LOADED");

            stmt.close();
            conn.close();

    } catch (Exception ex) {
        String msg = ex.getMessage();
        System.out.println(msg);
        }
    }

    @FXML private void handleCreateAction(ActionEvent event) {
        createTable(SQL_URL);
    }

    @FXML private void handleDeleteAction(ActionEvent event) {
        deleteTable(SQL_URL);
    }

    @FXML private void handleLoadAction(ActionEvent event) {
        loadTable(SQL_URL);

    }

    @FXML private void handlePopulateAction(ActionEvent event) {
        populateTable(SQL_URL);
    }
}
