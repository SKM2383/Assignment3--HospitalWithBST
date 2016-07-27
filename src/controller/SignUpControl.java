/**
 *   APPLICATION: LoginSystem
 *         CLASS: SignUpControl
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Designated to be the controller for the signup.fxml screen. It checks the fields that are used
 *                for account creation, and if certain fields are filled in and formatted correctly a new
 *                User is created. If another User that is already in the database has the same username
 *                as the one that was entered, this class prevents another User from being added.
 *       PACKAGE: controller
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
*/

package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;
import model.UserIO;
import model.UserStorage;
import util.list.ArrayOrderedList;
import util.list.DuplicateElementException;
import view.LoginView;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class SignUpControl {
    @FXML
    private TextField txtfldFirstName;
    @FXML
    private TextField txtfldLastName;
    @FXML
    private DatePicker datepickBirthday;
    @FXML
    private TextField txtfldSocial;
    @FXML
    private ToggleGroup genderToggleGroup;
    @FXML
    private TextField txtfldPhone;
    @FXML
    private TextField txtfldEmail;
    @FXML
    private TextField txtfldPhoto;
    @FXML
    private TextField txtfldUsername;
    @FXML
    private PasswordField psfPassword;
    @FXML
    private PasswordField psfConfirmedPassword;
    @FXML
    private Label lblSignUpStatus;

    @FXML
    public void browseForPhoto(){
        // Create a file dialog and set its title
        FileChooser photoPathChooser = new FileChooser();
        photoPathChooser.setTitle("Choose Profile Picture");

        // Set extension filters to only allow images to be chosen
        photoPathChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG Image", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG Image", "*.png"),
                new FileChooser.ExtensionFilter("BMP Image", "*.bmp"),
                new FileChooser.ExtensionFilter("TIFF Image", "*.tiff"),
                new FileChooser.ExtensionFilter("SVG Image", "*.svg")
        );

        /* Get the sign up window to pass to the .showOpenDialog()
         * method so the window blocks until the file chooser is closed
         * or a file is selected. This window is obtained by finding the
         * window that one of the sign up controls is on */
        Stage parentWindow = (Stage) txtfldPhoto.getScene().getWindow();
        File chosenFile = photoPathChooser.showOpenDialog(parentWindow);

        // Check and make sure a file was chosen, if so then set the photo
        // text field to the file path
        if(chosenFile != null){
            txtfldPhoto.setText(chosenFile.getAbsolutePath());
        }
    }

    /* Private helper method to check if a TextField or a subclass [PasswordField]
     * is null or empty. */
    private boolean fieldIsEmpty(TextField textField){
        if(textField.getText() == null || textField.getText().trim().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    /* Private helper method to add the newly created User to the database
     * and saving the database. This was made to reduce repeated code in the
     * conditional statements when verifying the email field */
    private void finishCreatingUser(User newUser){
        // Then write the database back to the file
        try{
            // After checking all the fields, we can now add the user to the database
            UserStorage.getUserDatabase().add(newUser);
            UserIO.writeUsers(UserStorage.getUserDatabase());
        }
        catch(DuplicateElementException de){
            lblSignUpStatus.setText("User is already in the database");
        }
        catch(IOException e){
            lblSignUpStatus.setText("Database Error: Could not add user.");
            lblSignUpStatus.setVisible(true);
        }

        lblSignUpStatus.setText("Account Created Successfully");
        lblSignUpStatus.setVisible(true);
    }

    @FXML
    public void register(){
        // First the sign in status label [lblSignUpStatus] must be hidden in case previous
        // sign up attempts didn't work, otherwise it may show a previous error message
        lblSignUpStatus.setVisible(false);

        // Check that the required fields aren't empty
        // This is done by trimming the contents and calling isEmpty(), however an
        // exception will occur if the value was null, so that must also be checked */
        if(fieldIsEmpty(txtfldFirstName) || fieldIsEmpty(txtfldLastName) ||
           fieldIsEmpty(txtfldUsername)  || fieldIsEmpty(psfPassword) ||
           fieldIsEmpty(psfConfirmedPassword) ||
           datepickBirthday.getValue() == null || datepickBirthday.getValue().toString().equals(""))
        {
            lblSignUpStatus.setText("One or more required fields haven't been filled in");
            lblSignUpStatus.setVisible(true);
        }
        else
        {
            String inputUsername = txtfldUsername.getText();

            ArrayOrderedList<User> database = UserStorage.getUserDatabase();

            // Check if the username is in the database by iterating through it and setting
            // notDuplicateUsername to false if it is a duplicate
            boolean notDuplicateUsername = true;

            database.reset();
            User nextUser = database.getNext();

            while(nextUser != null){
                if(nextUser.getUsername().equals(inputUsername)){
                    notDuplicateUsername = false;
                    break;
                }

                nextUser = database.getNext();
            }

            if (notDuplicateUsername) {

                // Make sure that the psfPassword and psfConfirmedPassword fields match
                if (psfPassword.getText().equals(psfConfirmedPassword.getText())) {

                    // Use a regular expression to check the format of the password field
                    //     Regular Expression: \A(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{0,}\z
                    //                   Test: Checks for a special character, lower case, upper case and a digit
                    //                         .{0,} accepts any string length, this shouldn't be deleted otherwise
                    //                         a match will never occur
                    Pattern correctFormat = Pattern.compile("\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=*])\\S{0,}\\z");
                    boolean correctPassFormat = correctFormat.matcher(psfPassword.getText()).matches();

                    if(correctPassFormat){
                        // All required fields are present, so we can create a basic User and set optional
                        // fields that were filled in using the setter methods of User. Some fields like the
                        // gender have to be extracted first however
                        RadioButton selectedGender = (RadioButton) genderToggleGroup.getSelectedToggle();
                        String genderString = selectedGender.getText();

                        User newUser = new User(txtfldFirstName.getText(),
                                                txtfldLastName.getText(),
                                                genderString,
                                                datepickBirthday.getValue().toString(),
                                                txtfldUsername.getText(),
                                                psfPassword.getText());

                        // Now check the other optional fields, if they are filled in then
                        // add the value to the appropriate newUser field
                        if(fieldIsEmpty(txtfldSocial)) {
                            newUser.setSocialSecurityNumber("N/A");
                        }
                        else {
                            newUser.setSocialSecurityNumber(txtfldSocial.getText());
                        }

                        if(fieldIsEmpty(txtfldPhone)) {
                            newUser.setNewPhoneNumber("N/A");
                        }
                        else {
                            newUser.setNewPhoneNumber(txtfldPhoto.getText());
                        }


                        if(fieldIsEmpty(txtfldPhoto)) {
                            newUser.setPhotoPath("N/A");
                        }
                        else {
                            newUser.setPhotoPath(txtfldPhoto.getText());
                        }


                        // Unlike the other optional fields, the email field has to be formatted
                        // correctly if one was entered
                        if(fieldIsEmpty(txtfldEmail)){
                            // User didn't fill in an email, so set a default
                            newUser.setEmail("N/A");

                            finishCreatingUser(newUser);
                        }
                        else{
                            //Extract the email and check that it has the @ character followed by the . character
                            String enteredEmail = txtfldEmail.getText();
                            if(enteredEmail.contains("@")) {

                                // If it has the @ character extract the rest of the string behind the @
                                // and check if it contains the . character
                                String substring = enteredEmail.substring(enteredEmail.indexOf('@'));

                                if(substring.contains(".")){
                                    newUser.setEmail(enteredEmail);
                                    finishCreatingUser(newUser);
                                }
                                else{
                                    lblSignUpStatus.setText("Email must contain \'@mail.\' format");
                                    lblSignUpStatus.setVisible(true);
                                }
                            }
                            else{
                                // The given email has an incorrect format, notify
                                // the user and cease making an account so they can
                                // enter a correct email
                                lblSignUpStatus.setText("Email must contain \'@mail.\' format");
                                lblSignUpStatus.setVisible(true);
                            }
                        }
                    }
                    else {
                        // Password wasn't in the correct format, show status label
                        lblSignUpStatus.setText("The entered password must meet the criteria listed");
                        lblSignUpStatus.setVisible(true);
                    }
                }
                else {
                    // psfPassword and psfConfirmedPassword don't match, so set the status label
                    lblSignUpStatus.setText("The entered passwords don't match. Try typing them again");
                    lblSignUpStatus.setVisible(true);
                }
            }
            else {
                // The username was already in the database
                lblSignUpStatus.setText("The username is already in use");
                lblSignUpStatus.setVisible(true);
            }
        }
    } // End of method [register]

    @FXML
    public void showLoginView(Event e) throws Exception{
        LoginView loginView = new LoginView();

        // Get the current sign up window and close it
        Stage previousStage = (Stage) txtfldUsername.getScene().getWindow();
        previousStage.close();
    }
}
