package BookMyShow;

import java.util.ArrayList;

public class Theatres {
    private String name;
    private ArrayList<Screens> screens = new ArrayList<Screens>();
    //  private ArrayList<String> movies = new ArrayList<>();
    //  private String location ;

    public Theatres(String name, ArrayList<Screens> screens)
    {
        this.name = name;
        this.screens = screens;
//        this.movies = movies;
//        this.location = location;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Screens> getScreens() {
        return screens;
    }

    public void setScreens(ArrayList<Screens> screens) {
        this.screens = screens;
    }

    //    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public ArrayList<String> getMovies() {
//        return movies;
//    }
//
//    public void setMovies(ArrayList<String> movies) {
//        this.movies = movies;
//    }
}