package BookMyShow;

import java.util.ArrayList;

public class User extends Account{

    private String name;
    private String location;
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public User(String userName, String password, String name, String location)
    {
        super(userName,password);
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}