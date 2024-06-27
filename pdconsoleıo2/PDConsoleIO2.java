package pdconsoleıo2;

import java.io.File;

import java.util.Scanner;

/**
 *
 * @author Gül
 */
public class PDConsoleIO2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PDConsoleIO2 ui = new PDConsoleIO2();
        PlaceDB pd = new MyPlaceDatabase();
        System.out.println("Please first enter 0 to uploading files");
        ui.processCommands(pd);
       
    }

    private PlaceDB theDatabase = null;
    private Scanner scIn = null;

    public PDConsoleIO2() {
        scIn = new Scanner(System.in);
    }

    public void processCommands(PlaceDB thePlaceDatabase) {
        String[] commands = {
            
            "Add Place",
            "Look Up by Zipcode",
            "List All Places by Zipcode Prefix",
            "Distance Between Zipcodes",
            "Sort by Town Name",
            "Lookup by Town Name",
            "Exit"
        };

        theDatabase = thePlaceDatabase;
        int choice;
        do {
            for (int i = 0; i < commands.length; i++) {
                System.out.println("Select " + i + ": " + commands[i]);
            }

            choice = scIn.nextInt();
            scIn.nextLine();
            switch (choice) {
                case 0:
                    doAddPlace();
                    break;
                case 1:
                    doLookupByZipcode();
                    break;
                case 2:
                    doListAllPlaces();
                    break;
                case 3:
                    doDistance();
                    break;
                case 4:
                    doSortByTownName();
                    break;
                case 5:
                    doLookupByTownName();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("*** Invalid choice " + choice + " - try again!");
            }
        } while (choice != commands.length - 1);
        System.exit(0);
    }

    private void doAddPlace() {
        System.out.println(" Waiting  for to be uploaded files...this may take some time.");
        File binaryFile = new File("database.out");
        if (binaryFile.exists()) { // if file is  exist use loadDatabase
            theDatabase.loadDatabase("database.out");
            System.out.println("Loaded database from file.");
        } else {
            // use readZipcode and read csv files
            theDatabase.readZipCode();

            System.out.println("Loaded database from CSV files.");
            theDatabase.saveDatabase("database.out");//  create Database.out file
            System.out.println("Saved database to file.");

      
            System.out.println("The process is over");

           
        }
    }

    private void doLookupByZipcode() {
        System.out.println("Enter zipcode");
        System.out.println("Enter a only 5 digit number");
        String theZip = scIn.nextLine();
        if (theZip.equals("")) {
            return;
        }
        Place p = theDatabase.lookupByZipcode(theZip); // invoke lookupByZipcode method
        if (p != null) {
            System.out.println(p.toString());
        } else {
            System.out.println("No such zipcode");
        }
    }

    private void doListAllPlaces() {
        System.out.println("Enter zipcode prefix");
        String prefix = scIn.nextLine();
        if (prefix.equals("")) {
            return;
        }
        theDatabase.listAllPlaces(prefix); // list All similar prefix
    }

    private void doDistance() {
        System.out.println("Enter two zipcodes");
        String zip1 = scIn.nextLine();
        String zip2 = scIn.nextLine();
        if (zip1.equals(" ") && zip2.equals(" ")) {
            return;
        }
        double distance = theDatabase.distance(zip1, zip2);
        System.out.println("Distance between " + zip1 + " and " + zip2 + " is: " + distance);
        if (distance == -1) {
            System.out.println("There is no longitude or latitude information.");
        }
    }

    private void doSortByTownName() {
        System.out.println("Database is sorting...");
        theDatabase.SortbyTownName();
        System.out.println("Database sorted by town name.");
    }

    private void doLookupByTownName() {
        System.out.println("Enter town name \nFor city name please start with a capital letter. ");
        String townName = scIn.nextLine();
        theDatabase.lookupByTownName(townName);
        
        
        
    
        }

    
    }

