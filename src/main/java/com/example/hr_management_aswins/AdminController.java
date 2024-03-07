package com.example.hr_management_aswins;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public TextField AnoId;
    public TextField AnoUser;
    public TextField AnoEmail;
    public TextField AnoSalary;

    @FXML
    private TableView<Admin> admin;
    @FXML
    private TableColumn<Admin,Integer > adminid;
    @FXML
    private TableColumn<Admin, String> username;
    @FXML
    private TableColumn<Admin,String> email;
    @FXML
    private TableColumn<Admin,Integer> salary;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<Admin> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminid.setCellValueFactory(new
                PropertyValueFactory<Admin,Integer>("adminid"));
        username.setCellValueFactory(new
                PropertyValueFactory<Admin,String>("username"));
        email.setCellValueFactory(new
                PropertyValueFactory<Admin,String>("email"));
        salary.setCellValueFactory(new
                PropertyValueFactory<Admin,Integer>("salary"));

        admin.setItems(list);
    }

    @FXML
    protected void ViewTable(){populateTable();}
    public void populateTable() {

        list.clear();

        String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `admin`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


// Populate the table with data from the database
            while (resultSet.next()) {
                int AdminID = resultSet.getInt("AdminID");
                String Username = resultSet.getString("Username");
                String Email = resultSet.getString("Email");
                int Salary = resultSet.getInt("Salary");
                admin.getItems().add(new Admin(AdminID, Username, Email,Salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertTable(ActionEvent actionEvent) {

        String User =AnoUser.getText();
        String Email =AnoEmail.getText();
        int Salary =Integer.parseInt(AnoSalary.getText());


        InsertData(User, Email, Salary);
    }

    public void InsertData(String User, String Email, int Salary){
        String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `admin`( `Username`, `Email`, `Salary`) VALUES ('"+User+"','"+Email+"','"+Salary+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LoadTable(ActionEvent actionEvent) {

        int Id=Integer.parseInt(AnoId.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `admin` WHERE AdminID='"+Id+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int AdminID = resultSet.getInt("AdminID");
                String Username = resultSet.getString("Username");
                String Email = resultSet.getString("Email");
                int Salary = resultSet.getInt("Salary");

                AnoEmail.setText(Email);
                AnoSalary.setText(String.valueOf(Salary));
                AnoUser.setText(Username);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void UpdateTable(ActionEvent actionEvent) {

        int Id = Integer.parseInt(AnoId.getText());
        String User =AnoUser.getText();
        String Email =AnoEmail.getText();
        int Salary =Integer.parseInt(AnoSalary.getText());

        UpdateData(Id, User, Email, Salary);
    }

    public void UpdateData(int Id, String User, String Email, int Salary){

        String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `admin` SET `Username`='"+User+"',`Email`='"+Email+"',`Salary`='"+Salary+"' WHERE `AdminID`='"+Id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();
// Populate the table with data from the database



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void DeleteTable(ActionEvent actionEvent) {

        int Id = Integer.parseInt(AnoId.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE  FROM `admin` WHERE AdminID='"+Id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();
// Populate the table with data from the database



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void BacktoDashboard(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        root = loader.load();

        DashboardController DashController = loader.getController();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}