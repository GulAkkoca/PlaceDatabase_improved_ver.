/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdconsoleıo2;

import java.io.Serializable;

/**
 *
 * @author Gül
 */
public class Place implements  Serializable  {
     protected String  zipcode;// the  place of zipcode
    private String town;// the town name
    private String state;// the state abbreviation. 
   // private int population;
    //private int males;
//private int females;

  
    /**
     *
     * @param zipcode
     * @param town
     * @param state
    
     */
    // construtcor
    public Place(String zipcode, String town, String state) {
        this.zipcode = zipcode;
        this.town = town;
        this.state = state;
       // this.population= population;
       // this.males= males;
        //this.females= females;
    }
 
    /**
     *
     * @return
     */
    // getter and setter methods
    public  String getZipcode() {
        return zipcode;
    }

    /**
     *
     * @param zipcode
     */
    public void setZipcode( String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     *
     * @return
     */
    public String getTown() {
        return town;
    }

    /**
     *
     * @param town
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
 
    // toString methods
    // returns a string containing the 5 digit zipcode 
    // if you write more 5 digit zipcode returns error
    @Override
    public String toString() {
        if( zipcode.length()!=5){
            System.out.println(" invalid zipcode");}
        
         return "{" + "Zipcode= " + zipcode + ", Town= " + town + ", State= " + state + '}';
     
            
        
            
    }
}
