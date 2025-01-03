package BookMyShow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Movies {
    private String name;
  //  private HashMap<String, ArrayList<Theatres>>  locationAndTheatre = new HashMap<>();
    private LocalDate startDate;
    private LocalDate endDate;
    private long duration;
    private String location;

    public Movies(String name,String location,LocalDate startDate,LocalDate endDate,long duration)
    {
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
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

    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public long getDuration() {
        return duration;
    }
}