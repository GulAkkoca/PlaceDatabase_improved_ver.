/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdconsoleıo2;

/**
 *
 * @author Gül
 */
public class LocatedPlace extends Place {
     double latitude;//latidute information 
   double longitude;

    /**
     *
     * @param latitude
     * @param longitude
     * @param zipcode
     * @param town
     * @param state
     */
    // latitude ve longitude parametreleri, yerin coğrafi konumunu temsil eder.
    //constructor
    public LocatedPlace( String zipcode, String town, String state,double latitude, double longitude) {
        super(zipcode, town, state);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    // getter and setter methods
    public double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    // toString method
    @Override
    public String toString() {
        return "{" + super.toString()+" Latitude= " + latitude + ", Longitude= " + longitude +'}';
    }
}
