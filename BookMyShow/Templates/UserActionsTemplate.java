package BookMyShow.Templates;

import BookMyShow.Movies;
import BookMyShow.User;

import java.time.LocalDate;
import java.util.ArrayList;

public interface UserActionsTemplate extends Actions {
    public void register();
    public void viewTickets(User currentUser);
    public void showMovies(User currentUser);
    public void doOperations(User currentUser, ArrayList<Movies> movies, LocalDate currentDate);
}
