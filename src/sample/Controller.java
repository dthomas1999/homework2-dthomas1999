package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.UUID;


public class Controller {
    @FXML
    private Button createB;

    @FXML
    private Button deleteB;

    @FXML
    private Button loadB;

    @FXML
    private ListView<Patient> patientsListView;

//    @FXML
//    private ListView<Patient> patientListView;

    final String SQL_URL = "jdbc:mysql://localhost:3306/test";

    private void createTable(String url) {
        try {
            Connection conn = DriverManager.getConnection(url, "root", "password");
            Statement stmt = conn.createStatement();
            try {
                stmt.execute("CREATE TABLE test (" +
                        "Name CHAR(25), " +
                        "Age Int(3), " +
                        "Gender CHAR(1), " +
                        "BloodType CHAR(3), " +
                        "Weight CHAR(5), " +
                        "Height CHAR(4), " +
                        "PatientID VARCHAR(36) )" );

                System.out.println("TABLE CREATED");
            } catch (Exception ex) {
                String msg = ex.getMessage();
                System.out.println(msg);
            }

            UUID id = UUID.randomUUID();
            String patientID = id.toString();
            String patientName = "John Doe";
            String patientAge = "27";
            String gender = "M";
            String bloodType = "O";
            String weight = "180";
            String height = "5";


            String sql = "INSERT INTO test VALUES" +
                    "('" + patientName + "', '" + patientAge + "', '" + gender + "', '" + bloodType + "', '" + weight + "', '" + height + "', '" + patientID + "')";
            stmt.executeUpdate(sql);
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

            stmt.execute("DROP TABLE test");

            System.out.println("TABLE DELETED");

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
//        ObservableList<Patient> items = patientsListView.getItems();
//        UUID id = UUID.randomUUID();
//        String idString = id.toString();
//        Patient Patient1 = new Patient(idString, "Jane Doe", 31, "F", "A", 156, "5:9" );
//        Patient Patient2 = new Patient(idString, "Jhon Doe", 27, "M", "O", 180, "5:11");
//        Patient Patient3 = new Patient(idString, "Kurt Ambrose", 51, "M", "B", 185, "6:1");
//
//        items.add(Patient1);
//        items.add(Patient2);
//        items.add(Patient3);
    }
}
