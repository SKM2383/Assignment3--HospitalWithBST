/**
 *   APPLICATION: LoginSystem
 *         CLASS: HospitalSearchControl
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Designated to be the controller for the hospital.fxml screen. It allows an Excel file of Hospital
 *                properties to be loaded and turned into Hospital objects. At that point this class provides methods
 *                to search the table according to different Hospital properties, as well as displaying row entries
 *                to the user.
 *       PACKAGE: controller
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Hospital;
import model.HospitalStorage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import util.tree.BinarySearchTree;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class HospitalSearchController implements Initializable{

    @FXML
    private TextField txtfldName;
    @FXML
    private TextField txtfldPhone;
    @FXML
    private TextField txtfldLatitude;
    @FXML
    private TextField txtfldLongitude;
    @FXML
    private Label lblError;
    @FXML
    private TableView<Hospital> tableHospitals;
    @FXML
    private Label lblHospitalName;
    @FXML
    private Label lblHospitalAddress;
    @FXML
    private Label lblHospitalPhone;
    @FXML
    private Label lblHospitalLocation;
    @FXML
    private Hyperlink hyperImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create the Hospital BST (sorts by latitude and longitude)
        BinarySearchTree<Hospital> hospitalTree = new BinarySearchTree<>();

        try (FileInputStream fileStream = new FileInputStream("HospitalList.xls")) {

            // Extract the first workbook sheet from the excel file
            HSSFSheet excelSheet = new HSSFWorkbook(fileStream).getSheetAt(0);

            // Variables to hold the data for each hospital
            String hospitalName;
            String hospitalAddress;
            Double hospitalLat;
            Double hospitalLon;
            String hospitalPhone;
            String hospitalImage;

            // Create a DataFormatter to turn each cell into its appropriate data representation
            final DataFormatter formatter = new DataFormatter();

            Iterator rowIterator = excelSheet.rowIterator();
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();

                // Extract the data from each cell into the appropriate variable
                hospitalName = formatter.formatCellValue(row.getCell(0));

                // The hasNext method also returns the blank row at the end of the Excel, so test is the
                // hospitalName is empty, indicating the last blank row. If this isn't stopped a NullPointerException
                // will be thrown when the Numeric values try to be extracted
                if (hospitalName.equals("")) {
                    break;
                }

                hospitalAddress = formatter.formatCellValue(row.getCell(1));
                hospitalLat = row.getCell(2).getNumericCellValue();
                hospitalLon = row.getCell(3).getNumericCellValue();
                hospitalPhone = formatter.formatCellValue(row.getCell(4));
                hospitalImage = formatter.formatCellValue(row.getCell(5));

                // Create a new Hospital object
                Hospital newHospital = new Hospital(hospitalName,
                        hospitalAddress,
                        hospitalLat,
                        hospitalLon,
                        hospitalPhone,
                        hospitalImage);

                // Add this hospital to the Hospital BST
                hospitalTree.add(newHospital);
            }

            // Iterate through the hospitalTree and add each Hospital to the TableView
            hospitalTree.reset(hospitalTree.INORDER);
            for (int i = 0; i < hospitalTree.size(); i++) {
                Hospital currentHospital = hospitalTree.getNext(hospitalTree.INORDER);

                tableHospitals.getItems().add(currentHospital);
            }

            // Now set the HospitalStorage database to our hospital BST to be used elsewhere
            HospitalStorage.setHospitalDatabase(hospitalTree);
        }
        catch(IOException ioe){
            lblError.setText("The database could not be opened");
            lblError.setVisible(true);
        }
    }

    @FXML
    public void searchDatabase(){
        lblError.setVisible(false);

        // Extract the original database and create a new BST to hold the results as the database gets filtered
        BinarySearchTree<Hospital> originalDatabase = HospitalStorage.getHospitalDatabase();
        BinarySearchTree<Hospital> filteredDatabase = new BinarySearchTree<>();

        try {
            originalDatabase.reset(originalDatabase.INORDER);
            for (int i = 0; i < originalDatabase.size(); i++) {
                Hospital nextHospital = originalDatabase.getNext(originalDatabase.INORDER);

                int comparisonResult = 0;

                /* In order to filter the entries, each field must first be checked if it was filled out, if it
                *  wasn't that means the user isn't using that field to search for an entry so it can be skipped.*/
                if (txtfldName != null && !txtfldName.getText().trim().equals("")) {
                    comparisonResult = (txtfldName.getText().compareTo(nextHospital.getName()));
                }

                /* At this point comparisonResult is checked if its still 0, meaning the nextHospital still matches
                *  our search parameters, if it is then the next field can be compared. Otherwise nextHospital doesn't
                *  match our search parameters, so we can skip back to the beginning of the loop*/
                if (txtfldPhone != null && !txtfldPhone.getText().trim().equals("")) {
                    if (comparisonResult == 0)
                        comparisonResult = (txtfldPhone.getText().compareTo(nextHospital.getPhoneNumber()));
                    else
                        continue;
                }

                if (txtfldLatitude != null && !txtfldLatitude.getText().trim().equals("")) {
                    if (comparisonResult == 0) {
                        double enteredValue = Double.parseDouble(txtfldLatitude.getText());
                        comparisonResult = Double.compare(enteredValue, nextHospital.getLatitude());
                    } else
                        continue;
                }

                if (txtfldLongitude != null && !txtfldLongitude.getText().trim().equals("")) {
                    if (comparisonResult == 0) {
                        double enteredValue = Double.parseDouble(txtfldLongitude.getText());
                        comparisonResult = Double.compare(enteredValue, nextHospital.getLongitude());
                    } else
                        continue;
                }

                // If comparisonResult is still zero then the nextHospital matched all criteria, so add it
                if (comparisonResult == 0) {
                    filteredDatabase.add(nextHospital);
                }
            }
        }
        catch(NumberFormatException nfe){
            lblError.setText("The latitude and longitude filters must be decimals");
            lblError.setVisible(true);
        }

        /* This if-else checks the size of the filteredDatabase, if it is greater than 0 (meaning some
           entries did match the search parameters) then display the filteredDatabase to the user. Otherwise
           it means a match wasn't found, so display a message and display the whole database back to the user
         */
        if(filteredDatabase.size() > 0){
            tableHospitals.getItems().clear();

            filteredDatabase.reset(filteredDatabase.INORDER);
            for(int i = 0; i < filteredDatabase.size(); i++){
                tableHospitals.getItems().add(filteredDatabase.getNext(filteredDatabase.INORDER));
            }
        }
        else{
            lblError.setText("There wasn't a match in the database");
            lblError.setVisible(true);

            originalDatabase.reset(originalDatabase.INORDER);
            for(int i = 0; i < originalDatabase.size(); i++){
                Hospital currentHospital = originalDatabase.getNext(originalDatabase.INORDER);

                tableHospitals.getItems().add(currentHospital);
            }
        }
    }

    public void showRowInformation(){
        try {
            lblError.setVisible(false);

            // Get the Hospital object from the row the user clicked on
            Hospital selectedHospital = tableHospitals.getSelectionModel().getSelectedItem();

            // Now set each of the information labels to the Hospital properties
            lblHospitalName.setText(selectedHospital.getName());
            lblHospitalName.setVisible(true);

            lblHospitalAddress.setText(selectedHospital.getAddress());
            lblHospitalAddress.setVisible(true);

            lblHospitalPhone.setText(selectedHospital.getPhoneNumber());
            lblHospitalPhone.setVisible(true);

            lblHospitalLocation.setText(selectedHospital.getLatitude() + "," + selectedHospital.getLongitude());
            lblHospitalLocation.setVisible(true);

            // Set the hyperlink text to a substring in case the link is to big for the Scene
            hyperImage.setText(selectedHospital.getImage().substring(0, 15));
            // Allow the user to click on the hyperlink to visit the image
            hyperImage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // Check to see if the Desktop class is supported, if so use it to open the hyperlink
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(new URI(selectedHospital.getImage()));
                        } catch (URISyntaxException | IOException e) {
                            lblError.setText("The link could not be opened");
                            lblError.setVisible(true);
                        }
                    }
                }
            });
            hyperImage.setVisible(true);
        }
        catch(NullPointerException np){}
    }
}
