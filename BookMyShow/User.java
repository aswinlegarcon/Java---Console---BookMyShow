package BookMyShow;

public class User {
    private String userName;
    private String password;
    private String name;
    private String location;

    public User(String userName, String password, String name, String location)
    {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.location = location;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setLocation(String location) {
        this.location = location;
    }
}