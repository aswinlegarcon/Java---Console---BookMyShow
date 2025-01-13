package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Screens {
    private String nameOfScreen;
    private long numberOfSeats;
    private String grid;
    private HashMap<Character, ArrayList<String>> seatsAndGrid = new HashMap<>(); // to store seats pattern
    private HashSet<Shows> showsInScreen = new HashSet<>(); // to store shows running in the screen

    public Screens (String nameOfScreen, long numberOfSeats,String grid, HashMap<Character,ArrayList<String>> seatsAndGrid)
    {
        this.nameOfScreen = nameOfScreen;
        this.numberOfSeats = numberOfSeats;
        this.grid = grid;
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

    public String getGrid() {
        return grid;
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