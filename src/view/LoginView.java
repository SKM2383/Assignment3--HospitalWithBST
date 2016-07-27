/**
 *   APPLICATION: LoginSystem
 *         CLASS: LoginView
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Act as the main class to launch the application by showing the login screen
 *       PACKAGE: view
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
*/

package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class LoginView{

    public LoginView() throws Exception{
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        loginStage.setResizable(false);
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root, 373, 182));
        loginStage.show();
    }
}
