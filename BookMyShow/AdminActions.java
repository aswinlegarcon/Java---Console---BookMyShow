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
            System.out.println("Choose your Operations..!! \n 1. Add Location\n 2. Add Theatre \n 3. Add Movies\n 4. View All Movies \n 5. Logout");
            System.out.print("Enter your Choice :");
            int adminChoice = Integer.parseInt(s.nextLine());
            switch (adminChoice)
            {
                case 1:
                    AdminActions.addLocations(s);
                    break;
                case 2:
                    //  AdminActions.addTheatres(s);
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
        if(!BookMyShow.getLocations().contains(locationToAdd))
        {
            BookMyShow.getLocations().add(locationToAdd);
            System.out.println("Location added Successfully..");
            return;
        }
        System.out.println("This location already exists.. ");
    }

//    private static void addTheatres(Scanner s)
//    {
//        System.out.print("Enter the Location where the theatre is located :");
//        String locationOfTheatre = s.nextLine();
//        var locationsAndTheatres = BookMyShow.getLocationAndTheatres();
//        if(!locationsAndTheatres.containsKey(locationOfTheatre))
//        {
//            System.out.println("No Locations Found");
//        }
//        else
//        {
//            System.out.print("Enter the Theatre name to add : ");
//            String theatreName = s.nextLine();
//            for(Theatres theatres:locationsAndTheatres.get(locationOfTheatre))
//            {
//                if(theatres.getName().equals(theatreName))
//                {
//                    System.out.println("Theatre already exists");
//                    return;
//                }
//
//            }
//            System.out.print("Enter No.of.Screens to add: ");
//            int noOfScreens = Integer.parseInt(s.nextLine());
//            ArrayList<Screens> screens =  new ArrayList<>();
//            for(int i = 1;i<=noOfScreens;i++)
//            {
//                System.out.print("Enter the Name of Screen "+ i +": ");
//                String screenName = s.nextLine();
//                System.out.print("Enter the number of seats :");
//                long numberOfSeats = Long.parseLong(s.nextLine());
//                System.out.print("Enter the Grid (How the seats have to be eg:2*8*2) : ");
//                String grid = s.nextLine();
//                var seatsAndGrids = Utilities.generateGrids(numberOfSeats,grid);
//                System.out.println("The seats are as follows..");
//                for(var seats:seatsAndGrids.entrySet())
//                {
//                    System.out.println(seats.getKey()+" "+seats.getValue());
//                }
//                screens.add(new Screens(screenName,numberOfSeats,seatsAndGrids));
//            }
//            locationsAndTheatres.get(locationOfTheatre).add(new Theatres(theatreName,screens));
//        }
//    }

    private static void addMovies(Scanner s)
    {
        System.out.println("Available locations to add movie... ");
        for(String locations:BookMyShow.getLocations())
        {
            System.out.println("* "+locations);
        }
        location:while (true)
        {
            System.out.print("Enter the location where to add the movie :");
            String locationOfMovie = s.nextLine();
            if(BookMyShow.getLocations().contains(locationOfMovie))
            {
                System.out.print("Enter the movie name to add : ");
                String movieName = s.nextLine();
                for(Movies movies:BookMyShow.getMovies())
                {
                    if(movies.getName().equals(movieName) && movies.getLocation().equals(locationOfMovie))
                    {
                        System.out.println("Movie already available..");
                        break location;
                    }
                }
                System.out.print("Enter the start date of the movie : ");
                LocalDate startDate = LocalDate.parse(s.nextLine(),BookMyShow.getFormatter());
                System.out.print("Enter the end date of the movie : ");
                LocalDate endDate = LocalDate.parse(s.nextLine(),BookMyShow.getFormatter());
                System.out.print("Enter the duration of the movie : ");
                String duration = s.nextLine();
                BookMyShow.getMovies().add(new Movies(movieName,locationOfMovie,startDate,endDate,duration));
                System.out.println("Movie added successfully..");
                return;
            }
            System.out.println("Enter the correct location...");
        }

    }
}



