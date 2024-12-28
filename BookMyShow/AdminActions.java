package BookMyShow;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AdminActions {

    public static Admin login(Scanner s)
    {
        ArrayList<Admin> admins= BookMyShow.getAdmins();
        System.out.print("Enter the Username : ");
        String userName = s.nextLine();
        System.out.print("Enter the Password : ");
        String password = s.nextLine();
        for(Admin adminToCheck:admins)
        {
            if(adminToCheck.getUserName().equals(userName) && adminToCheck.getPassword().equals(password))
            {
                System.out.println("Login Success...!!");
                return adminToCheck;
            }
            else
            {
                System.out.println("Invalid Username or Password....Try Again");
                return new Admin(null,null);
            }

        }
        return null;
    }

    public static void showOperations(Scanner s,Admin currentAdmin)
    {
        while (true)
        {
            System.out.println("Choose your Operations..!! \n 1. Add Location\n 2. Add Theatre \n 3. Add Movies\n 4. Logout");
            System.out.print("Enter your Choice :");
            int adminChoice = Integer.parseInt(s.nextLine());
            switch (adminChoice)
            {
                case 1:
                    AdminActions.addLocations(s);
                    break;
                case 2:
                    AdminActions.addTheatres(s);
                    break;
                case 3:
                    AdminActions.addMovies(s);
                    break;
                case 4:
                    System.out.println("Logged out");
                    return;



            }
        }


    }

    private static void addLocations(Scanner s)
    {
        System.out.print("Enter the Location name to add :");
        String locationToAdd = s.nextLine();
        if(!BookMyShow.getLocationAndMovies().containsKey(locationToAdd))
        {
            BookMyShow.getLocationAndTheatres().put(locationToAdd,new ArrayList<Theatres>());
            System.out.println("Location added Successfully..");
            return;
        }
        System.out.println("This location already exists.. ");
    }

    private static void addTheatres(Scanner s)
    {
        System.out.print("Enter the Location where the theatre is located :");
        String locationOfTheatre = s.nextLine();
        var locationsAndTheatres = BookMyShow.getLocationAndTheatres();
        if(!locationsAndTheatres.containsKey(locationOfTheatre))
        {
            System.out.println("No Locations Found");
        }
        else
        {
            System.out.print("Enter the Theatre name to add : ");
            String theatreName = s.nextLine();
            for(Theatres theatres:locationsAndTheatres.get(locationOfTheatre))
            {
                if(theatres.getName().equals(theatreName))
                {
                    System.out.println("Theatre already exists");
                    return;
                }

            }
            System.out.print("Enter No.of.Screens to add: ");
            int noOfScreens = Integer.parseInt(s.nextLine());
            ArrayList<Screens> screens =  new ArrayList<>();
            for(int i = 1;i<=noOfScreens;i++)
            {
                System.out.print("Enter the Name of Screen "+ i +": ");
                String screenName = s.nextLine();
                System.out.print("Enter the number of seats :");
                long numberOfSeats = Long.parseLong(s.nextLine());
                System.out.print("Enter the Grid (How the seats have to be eg:2*8*2) : ");
                String grid = s.nextLine();
                var seatsAndGrids = Utilities.generateGrids(numberOfSeats,grid);
                System.out.println("The seats are as follows..");
                for(var seats:seatsAndGrids.entrySet())
                {
                    System.out.println(seats.getKey()+" "+seats.getValue());
                }
                screens.add(new Screens(screenName,numberOfSeats,seatsAndGrids));
            }
            locationsAndTheatres.get(locationOfTheatre).add(new Theatres(theatreName,screens));
        }
    }

    private static void addMovies(Scanner s)
    {

        System.out.print("Enter the Location where to add the movies :");
        String locationOfTheatre = s.nextLine();
        if(!BookMyShow.getLocationAndTheatres().containsKey(locationOfTheatre))
        {
            System.out.println("This location is not available .. ");
            return;
        }
        ArrayList<Movies> movie = BookMyShow.getLocationAndMovies().get(locationOfTheatre);
        if (movie == null) {
            movie = new ArrayList<>();
            BookMyShow.getLocationAndMovies().put(locationOfTheatre, movie);
        }
        System.out.println("The Theatres available in this location..");
        var locationAndTheatres = BookMyShow.getLocationAndTheatres().get(locationOfTheatre);
        for(var theatres:locationAndTheatres)
        {
            System.out.println("* "+theatres.getName());
        }
        String theatreNameToAddMovie = "";
        entryTheatre:while (true)
        {
            System.out.print("Enter the theatre where to add the movie : ");
            theatreNameToAddMovie = s.nextLine();
            for (var theatres : locationAndTheatres) {
                if (theatres.getName().equals(theatreNameToAddMovie)) {
                    System.out.println("List of Screens..");
                    for (var screens : theatres.getScreens()) {
                        System.out.println(screens.getNameOfScreen());
                    }
                    break entryTheatre;
                }
                }
            System.out.println("The theatre does not exist..Enter correct theatre..");
        }
        entryScreen:while (true)
        {
            System.out.print("Enter the screen to add the movie:");
            String screenName = s.nextLine();
            for (var theatres : locationAndTheatres)
            {
                if(theatres.getName().equals(theatreNameToAddMovie))
                {
                    for (var screens : theatres.getScreens()) {
                        if(screens.getNameOfScreen().equals(screenName)) {
                            System.out.print("Enter the Movie to add : ");
                            String movieToAdd = s.nextLine();
                            System.out.print("Enter the date of the movie (dd-MM-yyyy) : ");
                            LocalDate dateOfMovie = LocalDate.parse(s.nextLine(),BookMyShow.getFormatter());
                            System.out.print("Enter the duration of the movie :");
                            String duration = s.nextLine();
                            movie.add(new Movies(movieToAdd,dateOfMovie,duration));
                            System.out.println("Movie added successfully..");
                            break entryScreen;
                        }
                    }
                }
            }
            System.out.println("There are no screens as per your input..");
        }
        BookMyShow.getLocationAndMovies().put(locationOfTheatre, movie); // inserting into hashmap



    }
}





