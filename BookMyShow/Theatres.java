package BookMyShow;

import java.util.ArrayList;

public class Theatres {
    private String name;
    private ArrayList<String> movies = new ArrayList<>();
    private String location ;

    public Theatres(String name, String location,ArrayList<String> movies)
    {
        this.name = name;
        this.movies = movies;
        this.location = location;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<String> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<String> movies) {
        this.movies = movies;
    }
}
