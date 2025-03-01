package BookMyShow;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow {
    private static ArrayList<User> users= new ArrayList<>(); // list to store all the users
    private static ArrayList<Admin> admins= new ArrayList<>(); // list to store all the admins
    private static ArrayList<String> locations = new ArrayList<>(); // list to store all the locations
    private static HashMap<String,Theatres> theatreNameAndTheatre = new HashMap<>(); // to store theatre name against theatre objects
    private static HashMap<String,ArrayList<Movies>> movieNameAndMovies= new HashMap<>(); // to store movie name against movie arraylist
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Date formatter for all operations with date
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // Time formatter for all operation with time

    public static ArrayList<Admin> getAdmins() {
        return BookMyShow.admins;
    }




    public static HashMap<String, ArrayList<Movies>> getMovieNameAndMovies() {
        return movieNameAndMovies;
    }

    public static HashMap<String, Theatres> getTheatreNameAndTheatre() {
        return theatreNameAndTheatre;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }
    public static DateTimeFormatter getTimeFormatter() {
        return timeFormatter;
    }

    public static ArrayList<User> getUsers() {
        return BookMyShow.users;
    }

    public static ArrayList<String> getLocations() {
        return locations;
    }

}