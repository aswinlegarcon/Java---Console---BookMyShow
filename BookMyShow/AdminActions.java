package BookMyShow;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

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
            System.out.println("Choose your Operations..!! \n 1. Add Location\n 2. Add Theatre \n 3. Add Movies");
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
        var locationsAndMovies = BookMyShow.getLocationAndTheatres();
        if(!locationsAndMovies.containsKey(locationOfTheatre))
        {
            System.out.println("No Locations Found");
        }
        else
        {
            System.out.print("Enter the Theatre name to add : ");
            String theatreName = s.nextLine();
            for(Theatres theatres:locationsAndMovies.get(locationOfTheatre))
            {
                if(theatres.getName().equals(theatreName))
                {
                    System.out.println("Theatre already exists");
                    return;
                }

            }
            System.out.print("Enter No.of.Screens to add: ");
            int noOfScreens = Integer.parseInt(s.nextLine());
            ArrayList<Screens> screens = new ArrayList<>();
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
            locationsAndMovies.get(locationOfTheatre).add(new Theatres(theatreName,screens));
        }
    }

    private static void addMovies(Scanner s)
    {
        System.out.println("Enter the Location where to add the theatre :");
        String locationOfTheatre = s.nextLine();
        if(!BookMyShow.getLocationAndMovies().containsKey(locationOfTheatre))
        {
            System.out.println("This location is not available .. ");
            return;
        }
        System.out.println("The Theatres available in this location..");
        var locationAndTheatres = BookMyShow.getLocationAndTheatres().get(locationOfTheatre);
        for(var theatres:locationAndTheatres)
        {
            System.out.println("* "+theatres.getName());
        }
        entryTheatre:while (true)
        {
            System.out.print("Enter the theatre where to add the movie : ");
            String theatreNameToAddMovie = s.nextLine();
            for (var theatres : locationAndTheatres) {
                if (!theatres.getName().equals(theatreNameToAddMovie)) {
                    System.out.println("The theatre does not exist..Enter correct theatre..");
                    continue entryTheatre;
                }
            }
            System.out.println("List of Screens..");
            for (var theatres : locationAndTheatres) {
                for (var screens : theatres.getScreens()) {
                    System.out.println(screens.getNameOfScreen());
                }
                break entryTheatre;
            }
        }
        entryScreen:while (true)
        {
            System.out.print("Enter the screen to add the movie:");
            String screenName = s.nextLine();
            for (var theatres : locationAndTheatres) {
                for (var screens : theatres.getScreens()) {
                    if(!screens.getNameOfScreen().equals(screenName))
                    {
                        System.out.println("There are no screens as per your input..");
                        continue entryScreen;
                    }
                }
            }
            System.out.print("Enter the Movie to add :");
            String movieToAdd = s.nextLine();
            ArrayList<Movies> movie = new ArrayList<>();
            movie.add(new Movies(movieToAdd));
            BookMyShow.getLocationAndMovies().put(locationOfTheatre,movie);

        }

    }
}






