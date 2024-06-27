/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdconsoleıo2;

import java.io.*;
import java.util.*;

/**
 *
 * @author Gül
 */
public class MyPlaceDatabase implements PlaceDB{

    ArrayList<Place> PlaceList ;
    private int place;
  //  ArrayList<Place> ziplist = new ArrayList<>();
   

    /**
     *
     */
    
    public MyPlaceDatabase(){
        PlaceList = new ArrayList<>();
        this.place=0;
    }

    /**
     *
     * @param fileName
     */
    public void readZipCode() {
            
        ArrayList<Place> zipList = new ArrayList<>();
        ArrayList<LocatedPlace> locList = new ArrayList<>();

        String zipFile = "uszipcodes.csv";
        String locFile = "ziplocs.csv";
        try {
            File file = new File(zipFile);

            if (!file.exists()) {

                throw new FileNotFoundException("Dosya bulunamadı: " + zipFile);

            }
            Scanner zipscanner = new Scanner(new File(zipFile));
            zipscanner.nextLine();
            int lineCount = 1;
            while (zipscanner.hasNextLine()) {
                String line = zipscanner.nextLine();
                String[] parts = line.split(",", -1);
                String zipcode = (parts[0]);
                String townName = parts[1];
                String state = (parts[2]);
                int population = parts[3].isEmpty() ? 0 : Integer.parseInt(parts[3]);
                int males = parts[4].isEmpty() ? 0 : Integer.parseInt(parts[4]);
                int females = parts[5].isEmpty() ? 0 : Integer.parseInt(parts[5]);

                if (population > 0) {
                    zipList.add(new PopulatedPlace(zipcode, townName, state, 0.0,0.0 , population , males,females));
                }
                else{
                    zipList.add( new Place(zipcode,townName,state));
                }
                lineCount++;
            }

        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
        }

        try {
            File file = new File(locFile);
            //System.out.println(zipFile);
            if (!file.exists()) {
                //  System.out.println();
                throw new FileNotFoundException("Dosya bulunamadı: " + locFile);
            }
            Scanner locscanner = new Scanner(new File(locFile));
            locscanner.nextLine();
            int lineCount = 1;
            while (locscanner.hasNextLine()) {
                String line = locscanner.nextLine();
               
                String[] parts = line.split(",", -1);

                String zip = (parts[0].replaceAll("^\"|\"$", ""));
                double latitude = parts[5].isEmpty() ? 0.0 : Double.parseDouble(parts[5]);

                double longitude = parts[6].isEmpty() ? 0.0 : Double.parseDouble(parts[6]);

                locList.add(new LocatedPlace(zip, " ", "", latitude, longitude));
                lineCount++;
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } boolean matchFound = false;
           for (Place place : zipList) {
           for (LocatedPlace loc : locList) {
                if (place.getZipcode().equals(loc.getZipcode())) {
                    matchFound = true;
                    if (place instanceof PopulatedPlace ) {
                        PlaceList.add(new PopulatedPlace(place.getZipcode(), place.getTown(), place.getState(),
                                loc.getLatitude(), loc.longitude, ((PopulatedPlace) place).getPopulation(), ((PopulatedPlace) place).getMales(), ((PopulatedPlace) place).getFemales()));
                    } else  {
                        PlaceList.add(new LocatedPlace(place.getZipcode(), place.getTown(), place.getState(),
                                loc.getLatitude(), loc.longitude));
                    }
                    break;
                }}
            
            if (!matchFound) {
                PlaceList.add(place);
            }
        }

       
        

        //Birleşik listeyi yazdır
    /*  for (Place place : PlaceList) {
            String output = "Zipcode: " + place.getZipcode() +
                            ", Town: " + place.getTown() +
                            ", State: " + place.getState();
            if (place instanceof PopulatedPlace &&place instanceof LocatedPlace) {
                PopulatedPlace populatedPlace = (PopulatedPlace) place;
                output += ", Latitude: " + populatedPlace.getLatitude() +
                          ", Longitude: " + populatedPlace.getLongitude()+
                          ", Population: " + populatedPlace.getPopulation();
                         
            } else if (place instanceof LocatedPlace) {
                LocatedPlace locatedPlace = (LocatedPlace) place;
                output += ", Latitude: " + locatedPlace.getLatitude() +
                          ", Longitude: " + locatedPlace.getLongitude();
            }
            System.out.println(output);
        }*/
    }
   
    /**
     *
     * @param list
     * @param zip
     * @return
     */
    // zip kodu liste de var mı onu kontrol ediyoruz
    @Override
    public boolean ControlPlaceZip(ArrayList<? extends Place> list, String zip) {// birden fazla liste kontrol edebilmek için <?extends> yaptım
  // Liste içindeki her bir Place nesnesi için döngü
        for (Place pp : list) {
            // Eğer Place nesnesinin zip kodu verilen zip koduna eşitse
            if (pp.zipcode.equals(zip)) {
                 // True döndüruyor
                return true;
            }
        }
        // eşleşme yoksa false 
        return false;
    }    

    /**
     *
     * @param p
     */
    public void addPlace(Place p){
         if (lookupByZipcode(p.getZipcode()) != null) {
            System.out.println("Zipcode already exists in the database.");
           return;
        }
         
          if (PlaceList.size() < place) {
            PlaceList.add(p);
            System.out.println("Place added successfully.");
        } else {
            System.out.println("Database is full. Cannot add new place.");
        }
    }   
    
    /**
     *
     * @param zipcode
     * @return
     */
    // zip koduna göre arama yapılan method
    @Override
     public Place lookupByZipcode(String zipcode) {
         //PlaceListteki place nesnesi kadar döüyor
        for (Place place : PlaceList) {
            if (place.getZipcode().equals(zipcode)) {
             // eşleşme varsa return yapıyoruz
                return place;
            }
        }
        // yoksa null
        return null;
    }
   /* public Place lookupByZipcode(String zipcode) {
      for( int i=0; i<PlaceList.size();i++){
          if( PlaceList.get(i).getZipcode().equals(zipcode)){
              return PlaceList.get(i);
          }
        }
        return null;
    }*/

    /**
     *
     * @param prefix
     */
    @Override
    public void listAllPlaces(String prefix) {
       for(int i=0; i<PlaceList.size();i++){
           if( PlaceList.get(i).getZipcode().startsWith(prefix)){
               System.out.println(PlaceList.get(i));
           
            }
        }

    }

    
    // veritabanını binary şeklinde kaydetme

    /**
     *
     * @param fileName
     */

    public void saveDatabase(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(PlaceList);

        } catch (IOException e) {
            System.out.println(" Errorrr" + e.getMessage());

        }

    }

    // İkili dosyadan veritabanını yükleme metodu

    /**
     *
     * @param zipFile
     * @return
     */
    @SuppressWarnings("unchecked")
     public void loadDatabase(String filename) {
         try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            PlaceList = (ArrayList<Place>) in.readObject();
            in.close();
        }
        
             catch (IOException | ClassNotFoundException e) {
            System.out.println(" Error " + e.getMessage());
        
        }
         }
 /*   public static MyPlaceDatabase loadDatabase(String zipFile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(zipFile))) {
            return MyPlaceDatabase in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Error " + e.getMessage());
            return new MyPlaceDatabase();
        }

    }*/

    // Kasaba adına göre balon sıralama metodu

    /**
     *
     */
     // bubblesort
    @Override
    public void SortbyTownName() {

        int n = PlaceList.size();
       
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (PlaceList.get(j).getTown().compareTo(PlaceList.get(j + 1).getTown()) > 0) {
                    // places[j] ve places[j+1]'i yer değiştir
                    Place temp = PlaceList.get(j);
                    PlaceList.set(j, PlaceList.get(j + 1));
                    PlaceList.set(j + 1, temp);
                }
            }
        }
        System.out.println("Used to bubblesort(sortbyTownName)");
    }

    // Kasaba adına göre ikili arama metodu

    /**
     *
     * @param town
     * @param low
     * @param high
     * @return
     */
    public int binarySearchByTownName(String town, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;// orta noktayı bulma
            if (PlaceList.get(mid).getTown().equals(town)) {
                // orta noktanın PlaceListeki indeksini return et
                return mid;
            }
          //  aranan town indeksi mid'in indeksinden küçükse
            if (PlaceList.get(mid).getTown().compareTo(town) > 0) {
              // sol tarafta ara
                return binarySearchByTownName(town, low, mid - 1);
            } else {
                // değilse sağ tarafta ara
                return binarySearchByTownName(town, mid + 1, high);
            }
        }
        // yoksa -1 döndür
        return -1;
    }

    // Kasaba adına göre sıralama yapılmamışsa sequential search metodu

    /**
     *
     * @param town
     * @return
     */
   

    // Kasaba adına göre arama metodu

    
        public int sequentialSearchByTownName(String town) {
            for (int i = 0; i < PlaceList.size(); i++) {
                if (PlaceList.get(i).getTown().equals(town)) {
                    return i;
                }
            }
            return -1;
        }
    
        // Kasaba adına göre arama metodu
    @Override
        public void lookupByTownName(String town) {
            int index;
            if (isSortedByTownName()) { //  if list is sorted
                index = binarySearchByTownName(town, 0, PlaceList.size() - 1);
                System.out.println("binarysearch was used");
            } else {
                index = sequentialSearchByTownName(town); // eğer liste sortlanmadıysa sequential sort yapılıyor
                System.out.println("sequentialSearch was used");
            }
   

    if (index >= 0) {
        Place place = PlaceList.get(index); // place verileri getir
        System.out.println("Town Name: " + place.getTown());// şehir ismini yazdır
        System.out.println("Zipcode: " + place.zipcode);// zipcode verisini yazdır

        if (place instanceof PopulatedPlace) {// place değeri PopulatedPlace içindeyse eğer
            PopulatedPlace populatedPlace = (PopulatedPlace) place; // class casting
            System.out.println("Population: " + populatedPlace.getPopulation());
            System.out.println("Rank in Population: " + getPopulationRank(town));
        }
        System.out.println("Index in List: " + index);
    } else {
        System.out.println("Town not found.");
    }
}

        
    

  
    // Kasaba adına göre listenin sıralı olup olmadığını kontrol eden yardımcı metot

    /**
     *
     * @return
     */
    public boolean isSortedByTownName() {
        for (int i = 0; i < PlaceList.size() - 1; i++) {
              // Eğer bir town name  indeksi bir sonrakinden büyükse
            if (PlaceList.get(i).getTown().compareTo(PlaceList.get(i + 1).getTown()) > 0) {
                // PlaceList sıralanmamış
                System.out.println("Town name is sorted ");
                return false;
            }
        }
        return true;
    }
    public int getPopulationRank(String townName) {
        Place targetPlace = null;

// PlaceList listesinde place birlikte dögüde gezinti
        for (Place place : PlaceList) {
            if (place.getTown().equalsIgnoreCase(townName)) {
                targetPlace = place; // aranan isim bulunup place ile eşleşince döngüyü kırar.
                break;
            }
        }

        if (targetPlace == null) {// aranan yer ismi yok, null ise
            System.out.println("Town name not found."); //yazdır
            return -1; // -1 döndür.
        }

        if (!(targetPlace instanceof PopulatedPlace)) {
            System.out.println("The place is not a populated place.");
            return -1; //  userın girdiği isim populatedPlace de yok ise -1 yazdır
        }

        int targetPopi = ((PopulatedPlace) targetPlace).getPopulation();
        int rank = 1;

        //  popülasyona göre rank'ı hesaplama  
        for (Place place : PlaceList) { 
            if (place instanceof PopulatedPlace) {// place nesnesi PopulatedPlace'de varsa 
                int currentPopi= ((PopulatedPlace) place).getPopulation();//casting
                if (currentPopi > targetPopi) {
                    rank++;
                }
            }
        }

        return rank;
    }


    /*@Override
   
    public int getPopulationRank(int population) {
            int rank = 1;
            for (Place place : PlaceList) {
                if (place instanceof PopulatedPlace && ((PopulatedPlace) place).population > population) {
                    rank++;
                }
            }
            return rank;
        }*/
    // Nüfus sıralamasını almak için yardımcı metot

    /**
     *
     * @param townName
     * @return
     */
    // Nüfus sıralamasını alıyor
  
   /* public int getPopulationRank(String townName) {
 // Nüfuslu yerlerin listesi
        ArrayList<PopulatedPlace> populatedPlaces = new ArrayList<>();
        
        for (Place place : PlaceList) {
            if (place instanceof PopulatedPlace) {
                ////  place  PopulatedPlace classında varsa  listeye ekle
                populatedPlaces.add((PopulatedPlace) place);
            }
        }
        // population göre sıralama
        populatedPlaces.sort((p1, p2) -> Integer.compare(p2.population, p1.population));
        for (int i = 0; i < populatedPlaces.size(); i++) {
            if (populatedPlaces.get(i).getTown().equalsIgnoreCase(townName)) {
                return i + 1; // Sıralama 1'den başlar
            }
        }
        return -1; // TownName bulunamadığında -1 döner
    }*/

    
 
    @Override
    public double distance(String zip1, String zip2) {
        Place place1 = lookupByZipcode(zip1);
        Place place2 = lookupByZipcode(zip2);
        if (place1 instanceof LocatedPlace && place2 instanceof LocatedPlace) {
            LocatedPlace locatedPlace2 = (LocatedPlace) place2;
            LocatedPlace locatedPlace1 = (LocatedPlace) place1;

            // calculate distance
            double distance = Math.sqrt(Math.pow(locatedPlace1.getLatitude() - locatedPlace2.getLatitude(), 2) + Math.pow(locatedPlace1.getLongitude() - locatedPlace2.getLongitude(), 2));

            return distance;
        } else {
            System.out.println(" Error: ");

        }
        return -1;
    }

    /**
     *
     * @return
     */
    // PlaceList listesini main class çağırmak için yazdım.
    public ArrayList<Place> getPlaces() {
        return PlaceList;
    }

   

   
   
}


