package BookMyShow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Movies {
    private String name;
    private HashMap<String, ArrayList<Theatres>>  locationAndTheatre = new HashMap<>();
    private LocalDate date;
    private String duration;

    public Movies(String name,LocalDate date,String duration)
    {
        this.name = name;
        this.date = date;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocationAndTheatre(HashMap<String, ArrayList<Theatres>> locationAndTheatre) {
        this.locationAndTheatre = locationAndTheatre;
    }

    public HashMap<String, ArrayList<Theatres>> getLocationAndTheatre() {
        return locationAndTheatre;
    }

    public LocalDate getDate() {
        return date;
    }
    public String getDuration() {
        return duration;
    }
}