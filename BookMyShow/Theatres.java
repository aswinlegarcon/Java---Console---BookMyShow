package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;

public class Theatres {
    private String name;
    private HashMap<String,Screens> screenNameAndObject = new HashMap<>(); // to store screens in theatres
    private String location ;

    public Theatres(String name, HashMap<String,Screens> screenNameAndObject, String location)
    {
        this.name = name;
        this.screenNameAndObject = screenNameAndObject;
        //this.movies = movies;
        this.location = location;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<String,Screens> getScreenNameAndObject() {
        return screenNameAndObject;
    }

    public String getLocation() {
        return location;
    }

}