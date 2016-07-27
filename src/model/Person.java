/**
 *   APPLICATION: LoginSystem
 *         CLASS: Person
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Parent class to the User class. Holds information that is unique and unchangeable about
 *                a person such as their gender and date of birth.
 *       PACKAGE: model
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
*/

package model;

import java.io.Serializable;

public class Person implements Serializable{
    private String firstName;
    private String lastName;
    private String gender;
    private String ssn;
    private final String DATE_OF_BIRTH;

    private static final long serialVersionUID = 4921159732745190324L;

    public Person(String fName, String lName, String gen, String dob){
        this.firstName = fName;
        this.lastName = lName;
        this.gender = gen;
        this.DATE_OF_BIRTH = dob;
        this.ssn = "N/A";
    }

    public Person(String fName, String lName, String gen, String dob, String ssn){
        this.firstName = fName;
        this.lastName = lName;
        this.gender = gen;
        this.DATE_OF_BIRTH = dob;
        this.ssn = ssn;
    }

    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String newName){
        this.firstName = newName;
    }

    public String getLastName() { return this.lastName; }
    public void setLastName(String newName){
        this.lastName = newName;
    }

    public String getGender(){
        return this.gender;
    }
    public void setGender(String gen){ this.gender = gen; }

    public String getSocialSecurityNumber() { return this.ssn; }
    public void setSocialSecurityNumber(String newSSN) {this.ssn = newSSN;}

    public String getBirthday() {
        return this.DATE_OF_BIRTH;
    }

    @Override
    public String toString(){
        return ("[Name: " + this.firstName + " " + this.lastName + "] " +
                "[SSN: " + this.ssn + "] " +
                "[Gender: " + this.gender + "] " +
                "[Birthday: " + this.DATE_OF_BIRTH + "] ");
    }
}
