# PlaceDatabase_improved_ver.


README
*Project Overview*
This project involves building a Java program that manages a database of places based on information from two CSV files: uszipcodes.csv and ziplocs.csv. The program categorizes zip codes into three types: PopulatedPlace, LocatedPlace, and Place, based on the availability of population and location data. These types inherit from each other forming a hierarchy. The program allows various operations on this database including loading data, sorting by town name, and searching by town name.

*File Descriptions*
uszipcodes.csv: Contains zip code data including town name, state code, population, and demographic information.
ziplocs.csv: Provides latitude and longitude data for zip codes.
Requirements and Functionality
Loading Data: The program initializes by reading data from the CSV files to populate an ArrayList of Place objects. Depending on the availability of population and location data, objects are instantiated as PopulatedPlace, LocatedPlace, or Place.

Saving Data: Upon program exit, the database is serialized into a binary file database.out.

Loading from Saved File: On subsequent runs, if database.out exists, the program loads data directly from this file instead of CSVs.

Menu Operations: The program supports operations such as listing all places, searching for a place by town name using binary search if sorted, sorting the database by town name using bubble sort, and more.

Sorting: Includes a menu option to sort the database by town name using bubble sort.

Searching: Includes a menu option to search for a town by name, providing details such as town name, zipcode, population, index in the sorted list, and population rank.

Implementation Details
Data Representation: Uses inheritance (PopulatedPlace, LocatedPlace, Place) to represent different types of places based on data availability.

Serialization: Utilizes Java serialization to save and load the database efficiently.

Search Algorithms: Implements binary search for efficient town name lookup if the list is sorted; otherwise, uses sequential search.

Sorting Algorithm: Implements bubble sort to sort the database by town name. 
*Instructions for Running the Program*
Ensure Java is installed on your system.
Compile the Java files (Place.java, PopulatedPlace.java, LocatedPlace.java, PlaceDatabase.java, and Main.java).
Run Main.java to start the program.
Follow the menu prompts to perform operations on the place database.
