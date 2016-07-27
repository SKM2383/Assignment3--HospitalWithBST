/**
 *   APPLICATION: LoginSystem
 *         CLASS: HospitalView
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Used to display the hospital.fxml Stage
 *       PACKAGE: controller
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HospitalView{

    public HospitalView() throws IOException {
        Stage hospitalStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hospital.fxml"));
        hospitalStage.setResizable(false);
        hospitalStage.setTitle("Hospital Search");
        hospitalStage.setScene(new Scene(root, 895, 450));
        hospitalStage.show();
    }

}
