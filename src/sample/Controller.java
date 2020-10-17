package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

//    @FXML
//    private ListView<Patient> patientListView;

    final String SQL_URL = "jdbc:mysql://localhost:3306/test";

    private void createTable(String url) {
        try {
            Connection conn = DriverManager.getConnection(url, "root", "password");
            Statement stmt = conn.createStatement();

            stmt.execute("CREATE TABLE test (" +
                    "Name CHAR(25), " +
                    "Age Int(3), " +
                    "Gender CHAR(1), " +
                    "BloodType CHAR(3), " +
                    "PatientID VARCHAR(36) )");

            System.out.println("TABLE CREATED");

            UUID id = UUID.randomUUID();
            String patientID =  id.toString();
            String patientName = "John Doe";
            String  patientAge = "27";
            String gender = "M";
            String bloodType = "O";


            //add data
            String sql = "INSERT INTO test VALUES" +
                    "('" + patientName + "', '" + patientAge + "', '" + gender + "', '" + bloodType + "', '" + patientID + "')";
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
        System.out.println("test");
    }
}
