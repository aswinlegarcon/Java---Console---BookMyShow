package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;

public class Movies {
    private String name;
    private HashMap<String, ArrayList<Theatres>>  locationAndTheatre = new HashMap<>();

    public Movies(String name)
    {
        this.name = name;
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
}
