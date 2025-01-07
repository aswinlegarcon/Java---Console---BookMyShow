package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Screens {
    private String nameOfScreen;
    private long numberOfSeats;
    private HashMap<Character, ArrayList<String>> seatsAndGrid = new HashMap<>();
    private HashSet<Shows> showsInScreen = new HashSet<>();

    public Screens (String nameOfScreen, long numberOfSeats, HashMap<Character,ArrayList<String>> seatsAndGrid)
    {
        this.nameOfScreen = nameOfScreen;
        this.numberOfSeats = numberOfSeats;
        this.seatsAndGrid = seatsAndGrid;
    }
    public String getNameOfScreen() {
        return nameOfScreen;
    }

    public HashSet<Shows> getShowsInScreen() {
        return showsInScreen;
    }

    public void setNameOfScreen(String nameOfScreen) {
        this.nameOfScreen = nameOfScreen;
    }

    public long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public HashMap<Character, ArrayList<String>> getSeatsAndGrid() {
        return seatsAndGrid;
    }

    public void setSeatsAndGrid(HashMap<Character, ArrayList<String>> seatsAndGrid) {
        this.seatsAndGrid = seatsAndGrid;
    }

}