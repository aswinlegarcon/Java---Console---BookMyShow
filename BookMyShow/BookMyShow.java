package BookMyShow;

import java.util.ArrayList;

public class BookMyShow {
    private static ArrayList<User> users= new ArrayList<>();
    private static ArrayList<Admin> admins= new ArrayList<>();
    private static ArrayList<String> location = new ArrayList<>();
    private static  ArrayList<Theatres> theatres = new ArrayList<>();

    public static ArrayList<Admin> getAdmins() {
        return BookMyShow.admins;
    }

    public static ArrayList<User> getUsers() {
        return BookMyShow.users;
    }

    public static ArrayList<String> getLocation() {
        return location;
    }

    public static ArrayList<Theatres> getTheatres() {
        return theatres;
    }
}
