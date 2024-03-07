package com.example.hr_management_aswins;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    public Label message;
    public TextField email;
    public PasswordField password;

    public void OnLoginButtonClick() {

        message.setText("");

        String Email_Address = email.getText();
        String GivenPassword = password.getText();

        if(Email_Address.equals("")||GivenPassword.equals("")){

            message.setText("Please Give Email or Password");

        }else{



// Establish a database connection
            String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management";
            String dbUser = "root";
            String dbPassword = "";
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                    dbPassword)) {
// Execute a SQL query to retrieve data from the database
                String query = "SELECT * FROM `user_login` WHERE Email='"+Email_Address+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database

                if(resultSet.next()){
                    int UserID = resultSet.getInt("UserID");
                    String Name = resultSet.getString("Name");
                    String Email = resultSet.getString("Email");
                    String Password = resultSet.getString("Password");

                    if(Password.equals(GivenPassword)){

                        message.setText("Success");



                        try {
                            // Load the FXML file for the second scene
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                            Parent secondScene = loader.load();

                            // Access the controller of the second scene
                            DashboardController DashController = loader.getController();

                            // Set the data in the controller of the second scene
                            DashController.username("Welcome: "+Name);

                            // Create a new stage for the second scene
                            Stage secondStage = new Stage();
                            secondStage.setTitle("Employee Scene");
                            secondStage.setScene(new Scene(secondScene));

                            Stage firstSceneStage = (Stage) password.getScene().getWindow();
                            firstSceneStage.close();

                            // Show the second stage
                            secondStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }






                    }else{
                        message.setText("Invalid email or password");
                    }








                }else{
                    message.setText("Invalid email or password");
                }



            } catch (SQLException e) {
                e.printStackTrace();


            }






        }
    }
}