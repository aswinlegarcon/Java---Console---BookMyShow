package BookMyShow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Movies {
    private String name;
  //  private HashMap<String, ArrayList<Theatres>>  locationAndTheatre = new HashMap<>();
    private LocalDate date;
    private long duration;
    private String location;
    private Theatres theatre;
    private Screens screen;
    private Shows show;

    public Movies(String name,String location,LocalDate date,long duration,Theatres theatre,Screens screen,Shows show)
    {
        this.name = name;
        this.location = location;
        this.date = date;
        this.duration = duration;
        this.theatre = theatre;
        this.screen = screen;
        this.show = show;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation()
    {
        return location;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setLocationAndTheatre(HashMap<String, ArrayList<Theatres>> locationAndTheatre) {
//        this.locationAndTheatre = locationAndTheatre;
//    }
//
//    public HashMap<String, ArrayList<Theatres>> getLocationAndTheatre() {
//        return locationAndTheatre;
//    }


    public Screens getScreen() {
        return screen;
    }

    public Theatres getTheatre() {
        return theatre;
    }

    public Shows getShow() {
        return show;
    }

    public long getDuration() {
        return duration;
    }
}