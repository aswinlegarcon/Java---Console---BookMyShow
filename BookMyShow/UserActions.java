package BookMyShow;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserActions {
//   Method to login the user
    public static User login(Scanner s)
    {
        ArrayList<User> users= BookMyShow.getUsers(); // get the users list
        System.out.print("Enter the Username : "); // get the username
        String userName = s.nextLine();
        System.out.print("Enter the Password : "); // get the password
        String password = s.nextLine();
        for(User userToCheck:users) // loop over all the user objects
        {
            if(userToCheck.getUserName().equals(userName) && userToCheck.getPassword().equals(password)) // check both username and password are correct
            {
                System.out.println("Login Success...!!");
                return userToCheck;
            }
            else if(userToCheck.getUserName().equals(userName) && !userToCheck.getPassword().equals(password)) // check username exists but password is wrong
            {
                System.out.println("Invalid Username or Password....Try Again");
                return new User(null,null,null,null);
            }

        }
        return null; // return null if no users found
    }


//    Function to register the user if no user available
    public static void register(Scanner s)
    {
        System.out.println("Give Your Details to Register..");
        System.out.print("Enter your Name :"); // get the name
        String name = s.nextLine();
        System.out.print("Enter a username :"); // get the username
        String userName = s.nextLine();
        System.out.print("Enter a password :"); // get the password
        String password = s.nextLine();
        System.out.print("Enter your Location :"); // get the location
        String location = s.nextLine();
        BookMyShow.getUsers().add(new User(userName,password,name,location)); // add new Object into users list
        System.out.println("User Registered Successfully..Now Try Login");

    }

//    Operations that can be done by users
    public static void doOperations(User currentUser,ArrayList<Movies> movies)
    {
        Scanner s = new Scanner(System.in);
        LocalDate currentDate = LocalDate.now();
        System.out.println("The movie availability is as below : ");
        System.out.println("Current date : " + currentDate.format(BookMyShow.getFormatter())); // print the current date
        HashMap<String,HashSet<Shows>> theatreAgainstShows = new HashMap<>(); // A hashmap to store theatre name - Shows in that theatre
        for(Movies movie : movies) // loop over all the movie objects of movie user selected
        {
            if(theatreAgainstShows.containsKey(movie.getTheatre().getName())) // if the hashmap contains theatre name already
            {
                theatreAgainstShows.get(movie.getTheatre().getName()).add(movie.getShow()); // Then add the show object of current movie that is running in that theatre
            }
            else
            {
                HashSet<Shows> shows = new HashSet<>(); // create new hashset to put into hashmap
                shows.add(movie.getShow()); // add the show into hashset
                theatreAgainstShows.put(movie.getTheatre().getName(), shows); // put the theatre name and shows inside the hashmap
            }
        }
       for(String theatreName : theatreAgainstShows.keySet()) // loop over hashmap to take keys
       {
           System.out.println("Theatre : "+theatreName); // print theatre name
           System.out.println("Shows : "+theatreAgainstShows.get(theatreName).toString()); // print all the show start time in the theatre by overriding toString
       }
        while (true)
        {
            System.out.println("\nEnter the theatre name to book movie : "); // get the theatre name
            String theatreName = s.nextLine();
            System.out.println("Enter the show time : "); // get the showtime
            LocalTime time = LocalTime.parse(s.nextLine(),BookMyShow.getTimeFormatter());
            var shows = theatreAgainstShows.get(theatreName); // get the Hashset Shows of theatre
            if(shows == null)
            {
                System.out.println("Enter correct theatre...");
            }
            Shows currentShow = null; // initially assign show object to null for future usage
            for(Shows show : shows) // loop over all the show object
            {
                if(show.getStartTime().equals(time)) // get the current show object that user entered by checking the start time
                {
                    currentShow = show; // reassign the loop object to currentshow
                }
            }
            if(currentShow==null) // if null after all these then
            {
                System.out.println("Enter the correct time of show...");
            }
            else // or do this
            {
                System.out.println("-------------- "+currentShow.getScreens().getNameOfScreen()+" ------------------"); // print the screen name
                System.out.println("------- Total Seats : "+currentShow.getScreens().getNumberOfSeats()+" ------------------"); // print the total seats
                var seatsAndGrids = currentShow.getScreens().getSeatsAndGrid(); // get the seats and grids hashmap and store it
                System.out.println("The seats are as follows..");// display that seats
                        for(var seats:seatsAndGrids.entrySet()) // loop over hashmap as key-value
                        {
                            System.out.println(seats.getKey()+" "+seats.getValue()); // print the key and the value
                        }
            }
//            --------------------
//            Add other operations here
//            ------------------
            break;
        }



    }

// Method to show all the movies
    public static ArrayList<Movies> showMovies(User currentUser) {
        Scanner s = new Scanner(System.in);
        HashMap<String,ArrayList<Movies>> moviesForUser = new HashMap<>();
        ArrayList<Movies> movies = new ArrayList<>();
        HashSet<String> moviesInUserArea = new HashSet<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate userDate = currentDate;
        while (true) {
            System.out.println("Current date : " + currentDate.format(BookMyShow.getFormatter()));
            System.out.println("Selected date : " + userDate.format(BookMyShow.getFormatter()));
            System.out.println("Current location : "+ currentUser.getLocation());

            boolean status = displayMovies(currentUser, userDate , moviesInUserArea);
            if (!status)
            {
                System.out.println("No movies available in this location..in this date..");
                LocalDate tempDate = UserActions.changePreferences(currentUser,currentDate);
                if(tempDate!=null)
                {
                    userDate = tempDate;
                }
            }
            else if(status)
            {
                System.out.println("Enter the movie name to book (enter 'change' to change your preferences): ");
                String movieToBook = s.nextLine();
                if(movieToBook.equals("change"))
                {
                    LocalDate tempDate = UserActions.changePreferences(currentUser,currentDate);
                    if(tempDate!=null)
                    {
                        userDate = tempDate;
                    }

                }
                else if(moviesInUserArea.contains(movieToBook))
                {
                        for(var movieObj : BookMyShow.getMovieNameAndMovies().get(movieToBook))
                        {
                            if(movieObj.getLocation().equals(currentUser.getLocation()) && movieObj.getDate().isEqual(userDate))
                            {
                               movies.add(movieObj);
                            }
                        }
                    break;
                }
            }

        }

        return movies;
    }


    private static boolean displayMovies(User currentUser, LocalDate dateOfUser , HashSet<String> moviesInUserArea)
    {
        boolean mainCheck = false;
        for(var movies : BookMyShow.getMovieNameAndMovies().keySet())
        {
            boolean check = false;
            for(var movieObj : BookMyShow.getMovieNameAndMovies().get(movies))
            {
                if(movieObj.getLocation().equals(currentUser.getLocation()) && movieObj.getDate().isEqual(dateOfUser))
                {
                    check = true;
                }
            }
            if(check)
            {
                mainCheck = true;
                System.out.println("*"+movies);
                moviesInUserArea.add(movies);
            }

        }
        return mainCheck;
    }

    private static LocalDate changePreferences(User currentUser, LocalDate currentDate)
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Do you want to change your date/location (y/n) type 'exit' to logout : ");
        String wishOfUser = s.nextLine();
        switch (wishOfUser) {
            case "y":
                mainLoop:while (true) {
                    System.out.print("Enter 'date' to change date | Enter 'location' to change location | Enter 'exit' to quit : ");
                    String changeOption = s.nextLine();
                    switch (changeOption) {
                        case "date":
                            System.out.print("Enter the date when to look for movies : ");
                            LocalDate dateOfUser = LocalDate.parse(s.nextLine(), BookMyShow.getFormatter());
                            if (dateOfUser.isAfter(currentDate) || dateOfUser.isEqual(currentDate)) {
                                return dateOfUser;
                            }
                            System.out.println("Enter upcoming dates not the past days..");
                            break;

                        case "location":
                            for (String availableLocations : BookMyShow.getLocations()) {
                                System.out.println("* " + availableLocations);
                            }
                            System.out.print("Enter the location where to look for movies : ");
                            String locationToLook = s.nextLine();
                            if (BookMyShow.getLocations().contains(locationToLook)) {
                                currentUser.setLocation(locationToLook);
                                return null;
                            }
                            System.out.println("That location is not available..");
                            break;
                        case "exit":
                            break mainLoop;

                        default:
                            System.out.println("Enter correct input ");
                            break;

                    }
                }
            case "n":
                System.out.println("Refreshing...!!");
                break;
            case "exit":
                System.out.println("Logging out");
                break;
            default:
                System.out.println("Enter correct input..!!");

        }
        return null;
    }


}