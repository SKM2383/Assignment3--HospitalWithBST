/**
 *   APPLICATION: LoginSystem
 *         CLASS: Hospital
 *        AUTHOR: Samuel Myles
 *   JDK VERSION: 1.8.0_73
 *   JRE VERSION: 1.8.0_73
 *   APP PURPOSE: Prototype login system that supports a mock user database. Users are given the ability
 *                to create a new account and login from that point forward.
 * CLASS PURPOSE: Defines the data model for a Hospital. Includes properties such as name, address, etc. Also provides
 *                getters and setters as well as a compareTo method that determines the order of this Hospital and another
 *                based on latitude and longitude respectively
 *       PACKAGE: controller
 *     PROFESSOR: Tanes Kanchanawanchai [CSC 202-061N]
 */

package model;

public class Hospital implements Comparable<Hospital>{
   private String name;
   private String address;
   private String phoneNumber;
   private String image;
   private double latitude;
   private double longitude;
   
   public Hospital(String name, String address, double lat, double lon, String phone, String imageUrl){
      this.name = name;
      this.address = address;
      this.latitude = lat;
      this.longitude = lon;
      this.phoneNumber = phone;
      this.image = imageUrl;
   }
   
   public String getName(){ return this.name; }
   public void setName(String newName){ this.name = newName; }
   
   public String getAddress(){ return this.address; }
   public void setAddress(String newAddress){ this.address = newAddress; }
   
   public String getPhoneNumber(){ return this.phoneNumber; }
   public void setPhoneNumber(String phone){ this.phoneNumber = phone; }

   public double getLatitude(){ return this.latitude; }
   public void setLatitude(double newLat){ this.latitude = newLat; }

   public double getLongitude(){ return this.longitude; }
    public void setLongitude(double newLon){ this.longitude = newLon; }
   
   public String getImage(){ return this.image; }
   public void setImage(String imageLink){ this.image = imageLink; }
   
   @Override
   public int compareTo(Hospital otherHospital){
      int compareResult = Double.compare(this.getLatitude(), otherHospital.getLatitude());
      if(compareResult != 0){ return compareResult; }
      
      compareResult = Double.compare(this.getLongitude(), otherHospital.getLongitude());
      if(compareResult != 0){ return compareResult; }
      
      return 0;
   }

   @Override
   public String toString(){
       return ("[" + name + "," +
               address + "," +
               latitude + "::" +
               longitude + "," +
               phoneNumber + "," +
               image + "]");
   }
}