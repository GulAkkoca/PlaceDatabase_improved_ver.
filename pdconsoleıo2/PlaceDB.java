/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pdconsoleıo2;

import java.util.ArrayList;
//import java.util.List;

/**
 *
 * @author Gül
 */
public interface PlaceDB {
    /** Look up a place by zipcode.
     * @param zipFile
   */
 
 
   public void readZipCode();
  public Place lookupByZipcode( String zipcode);


   public void addPlace(Place p);
    public void saveDatabase(String fileName);
   public  void loadDatabase(String filename) ;
    /**
     *
     * @param zipFile
     * @return
     */
   
    /**
     *
     * @return
     */
  public boolean ControlPlaceZip(ArrayList<? extends Place> kist,String zip);
  public void SortbyTownName();
 //public void addPlace(Place newPlace);

  /** List all places whose zipcode start with prefix.
      @return none
   */
  
  
 
  public  void lookupByTownName(String town);
  public void listAllPlaces(String prefix);

    public int getPopulationRank( String townName);
   
 /** Return the distance between two zipcodes
      @param zip1, zip2
      @return distance between zip1 and zip2, -1 if either has no location.
   */
  public double distance(String zip1, String zip2);
 public ArrayList<Place> getPlaces();
}


