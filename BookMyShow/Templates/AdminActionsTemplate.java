package BookMyShow.Templates;

import BookMyShow.Admin;

public interface AdminActionsTemplate extends Actions{
    public void showOperations(Admin currentAdmin);
    public void addLocations();
    public void addTheatres();
    public void addMovies();
    public void viewMovies();
    public void viewTheatres();
}
