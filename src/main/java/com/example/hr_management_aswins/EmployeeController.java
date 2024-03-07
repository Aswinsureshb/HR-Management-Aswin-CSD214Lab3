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

public class EmployeeController implements Initializable {

    public TextField AnoId;
    public TextField AnoName;
    public TextField AnoEmail;
    public TextField AnoAddress;
    public TextField AnoSalary;

    @FXML
    private TableView<Employee> employee;
    @FXML
    private TableColumn<Employee,Integer > empid;
    @FXML
    private TableColumn<Employee, String> empname;
    @FXML
    private TableColumn<Employee,String> empemail;
    @FXML
    private TableColumn<Employee,String> empaddress;
    @FXML
    private TableColumn<Employee,Integer> empsalary;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<Employee> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        empid.setCellValueFactory(new
                PropertyValueFactory<Employee,Integer>("empid"));
        empname.setCellValueFactory(new
                PropertyValueFactory<Employee,String>("empname"));
        empemail.setCellValueFactory(new
                PropertyValueFactory<Employee,String>("empemail"));
        empaddress.setCellValueFactory(new
                PropertyValueFactory<Employee,String>("empaddress"));
        empsalary.setCellValueFactory(new
                PropertyValueFactory<Employee,Integer>("empsalary"));

        employee.setItems(list);
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
            String query = "SELECT * FROM `employee`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


// Populate the table with data from the database
            while (resultSet.next()) {
                int Emp_ID = resultSet.getInt("Emp_ID");
                String Emp_Name = resultSet.getString("Emp_Name");
                String Emp_Email = resultSet.getString("Emp_Email");
                String Emp_Address = resultSet.getString("Emp_Address");
                int Emp_Salary = resultSet.getInt("Emp_Salary");
                employee.getItems().add(new Employee(Emp_ID, Emp_Name, Emp_Email, Emp_Address, Emp_Salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertTable(ActionEvent actionEvent) {

        String EName = AnoName.getText();
        String EEmail = AnoEmail.getText();
        String EAddress = AnoAddress.getText();
        int ESalary = Integer.parseInt(AnoSalary.getText());

        InsertData(EName, EEmail, EAddress, ESalary);
    }

    public void InsertData(String EName, String EEmail, String EAddress, int ESalary){

        String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `employee`( `Emp_Name`, `Emp_Email`, `Emp_Address`, `Emp_Salary`) VALUES ('"+EName+"','"+EEmail+"','"+EAddress+"','"+ESalary+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void LoadTable(ActionEvent actionEvent) {

        int EId=Integer.parseInt(AnoId.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `employee` WHERE Emp_ID='"+EId+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int Emp_ID = resultSet.getInt("Emp_ID");
                String Emp_Name = resultSet.getString("Emp_Name");
                String Emp_Email = resultSet.getString("Emp_Email");
                String Emp_Address = resultSet.getString("Emp_Address");
                int Emp_Salary = resultSet.getInt("Emp_Salary");




                AnoName.setText(Emp_Name);
                AnoEmail.setText(Emp_Email);
                AnoAddress.setText(Emp_Address);
                AnoSalary.setText(String.valueOf(Emp_Salary));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateTable(ActionEvent actionEvent) {

        int EId=Integer.parseInt(AnoId.getText());
        String EName = AnoName.getText();
        String EEmail = AnoEmail.getText();
        String EAddress = AnoAddress.getText();
        int ESalary = Integer.parseInt(AnoSalary.getText());

        UpdateData(EId, EName, EEmail, EAddress, ESalary);
    }

    public void UpdateData(int EId, String EName, String EEmail, String EAddress, int ESalary){

        String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `employee` SET `Emp_Name`='"+EName+"',`Emp_Email`='"+EEmail+"',`Emp_Address`='"+EAddress+"',`Emp_Salary`='"+ESalary+"' WHERE `Emp_ID`='"+EId+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void DeleteTable(ActionEvent actionEvent) {

        int EId=Integer.parseInt(AnoId.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `employee` WHERE Emp_ID = '"+EId+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();

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