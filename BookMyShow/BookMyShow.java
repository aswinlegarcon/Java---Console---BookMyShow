package BookMyShow;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class BookMyShow {
    private static ArrayList<User> users= new ArrayList<>();
    private static ArrayList<Admin> admins= new ArrayList<>();
    private static ArrayList<String> locations = new ArrayList<>();
    // private static HashMap<String,ArrayList<Theatres>> locationAndTheatres = new HashMap<String,ArrayList<Theatres>>();
    // private static HashMap<String,ArrayList<Movies>> locationAndMovies = new HashMap<String,ArrayList<Movies>>();
    private static HashMap<String,Theatres> theatreNameAndTheatre = new HashMap<>();
    private static HashMap<String,ArrayList<Movies>> movieNameAndMovies= new HashMap<>();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

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

//    public static HashMap<String, ArrayList<Theatres>> getLocationAndTheatres() {
//        return locationAndTheatres;
//    }
//
//    public static HashMap<String, ArrayList<Movies>> getLocationAndMovies() {
//        return locationAndMovies;
//    }
}