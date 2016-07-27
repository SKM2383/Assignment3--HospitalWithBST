/**
 *   APPLICATION: LoginSystem
 *         CLASS: AppLauncher
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Acts as the main entry for the application
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
*/

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import model.UserIO;
import model.UserStorage;
import util.list.ArrayOrderedList;
import view.LoginView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AppLauncher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void loadUserDatabase(){
        try{
            // Try loading the database, if that doesn't work display an alert message box
            UserStorage.setUserDatabase((ArrayOrderedList<User>) UserIO.readUsers());
        }
        catch(FileNotFoundException e){}
        catch(IOException e){
            e.printStackTrace();
            Alert alertMessage = new Alert(Alert.AlertType.ERROR);
            alertMessage.setTitle("Error");
            alertMessage.setContentText("An error occurred while loading the database into RAM");
            alertMessage.showAndWait();
        }
        catch(ClassNotFoundException e){
            Alert alertMessage = new Alert(Alert.AlertType.ERROR);
            alertMessage.setTitle("Error");
            alertMessage.setContentText("The specified class could not be found");
            alertMessage.showAndWait();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        loadUserDatabase();
        LoginView loginPage = new LoginView();
    }
}
