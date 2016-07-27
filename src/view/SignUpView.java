/**
 *   APPLICATION: LoginSystem
 *         CLASS: SignUpView
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Displays the signup screen to register a new account
 *       PACKAGE: view
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
*/

package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpView{

    public SignUpView() throws IOException{
        Stage signupStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        signupStage.setResizable(false);
        signupStage.setTitle("Signup");
        signupStage.setScene(new Scene(root, 485, 517));
        signupStage.show();
    }
}
