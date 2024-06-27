/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdconsoleıo2;

/**
 *
 * @author Gül
 */
// PopulatedPlace sınıfı, bir yerin (place) nüfus bilgilerini içeren bir alt sınıftır.
class PopulatedPlace extends LocatedPlace {
    int population; //  nüfusu
    int males;      //  erkek nüfusu
    int females;    // kadın  nüfusu
    
   
    
   
   
    public PopulatedPlace(String zipcode, String town, String state, double latitude, double longitude, int population, int males, int females) {
     
        super(zipcode, town, state,latitude,longitude);
        
       
        this.population = population;
        this.males = males;
        this.females = females;
    }

   
    
    // Population bilgisini döndüren bir getter metodu
    public int getPopulation() {
        return population;
    }
    
    // Males bilgisini döndüren bir getter metodu
    public int getMales() {
        return males;
    }
    
    // Females bilgisini döndüren bir getter metodu
    public int getFemales() {
        return females;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setMales(int males) {
        this.males = males;
    }

    public void setFemales(int females) {
        this.females = females;
    }

    @Override
    public String toString() {
        return  super.toString()+ " population=" + population + ", males=" + males + ", females=" + females + '}';
    }
    
}
