package BookMyShow;

import ATM.ATMMachine;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AdminActions {
//function to check admin credentials and return object accordingly
    public static Admin login(Scanner s)
    {
        ArrayList<Admin> admins= BookMyShow.getAdmins(); // getting and storing admin arraylist
        System.out.print("Enter the Username : "); // get the username
        String userName = s.nextLine();
        System.out.print("Enter the Password : "); // get the password
        String password = s.nextLine();
        for(Admin adminToCheck:admins) // loop over the admin arraylist to get admin objects
        {
            if(adminToCheck.getUserName().equals(userName) && adminToCheck.getPassword().equals(password)) // if both username and password is same then return admin object
            {
                System.out.println("Login Success...!!");
                return adminToCheck;
            }
            else if(adminToCheck.getUserName().equals(userName) && !adminToCheck.getPassword().equals(password))// if password alone wrong then return username null
            {
                System.out.println("Invalid Username or Password....Try Again");
                return new Admin(null,null);
            }

        }
        return null; // if no admin in arraylist then return null
    }


    public static void showOperations(Scanner s,Admin currentAdmin) // admin operations
    {
        while (true) // infinite loop until logout
        {
            System.out.println("Choose your Operations..!! \n 1. Add Location\n 2. Add Theatre \n 3. Add Movies\n 4. View All Movies \n 5. View All Theatres \n 6. Logout");// asking what to do
            System.out.print("Enter your Choice :");
            int adminChoice = Integer.parseInt(s.nextLine());
            switch (adminChoice)
            {
//                If option 1 then add the location
                case 1:
                    AdminActions.addLocations();
                    break;
//                  If option 2 then add the theatres
                case 2:
                    AdminActions.addTheatres();
                    break;
//                    If option 3 then add the movies
                case 3:
                    AdminActions.addMovies();
                    break;
//                    If option 4 then view the movies
                case 4:
                    AdminActions.viewMovies();
                    break;
//                    If option 5 then view the theatres
                case 5:
                    AdminActions.viewTheatres();
                    break;
//                    If option 6 then logout
                case 6:
                    System.out.println("Logged out");
                    return;



            }
        }


    }

//    function to add the locations
    private static void addLocations()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Location name to add :"); // get location name
        String locationToAdd = s.nextLine();
        if(!BookMyShow.getLocations().contains(locationToAdd)) // if that location not already in arraylist then add the location
        {
            BookMyShow.getLocations().add(locationToAdd); // adding location in arraylist
            System.out.println("Location added Successfully..");
            return;
        }
        System.out.println("This location already exists.. ");//or already exists
    }

//    function to add the theatres
    private static void addTheatres()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Available locations to add theatres... ");// show available locations
        for(String locations:BookMyShow.getLocations())
        {
            System.out.println("* "+locations);
        }
        while (true)// infinite loop to ask user to enter correct location
        {
            System.out.print("Enter the Location where the theatre is located :");// ask location where to add the theatre
            String locationOfTheatre = s.nextLine();
            if(!BookMyShow.getLocations().contains(locationOfTheatre)) // if that location not found in location arraylist
            {
                System.out.println("No Locations Found");
            }
            else // if that location is there then ask the theatre name
            {
                System.out.print("Enter the Theatre name to add : ");// get theatre name
                String theatreName = s.nextLine();
                for(Theatres theatres:BookMyShow.getTheatres())// loop over available theatres
                {
                    if(theatres.getName().equals(theatreName))//If the name already exists then return
                    {
                        System.out.println("Theatre already exists");
                        return;
                    }

                }
//                Or get other needed details
                ArrayList<Theatres> theatres = new ArrayList<>();
                System.out.print("Enter No.of.Screens to add: ");// get the no of screens in theatre
                int noOfScreens = Integer.parseInt(s.nextLine());
                for(int i = 1;i<=noOfScreens;i++) // loop as many screens need to add (EG: if 2 screens then 2 times)
                {
                    enterNameOfScreen:while (true)
                    {
                        System.out.print("Enter the Name of Screen "+ i +": ");//get the name of screen i
                        String screenName = s.nextLine();
                        var checkScreenAlreadyAvailable = BookMyShow.getTheatreNameAndTheatres().get(theatreName);
                        System.out.print("Enter the number of seats :");// get the number of seats in that screen
                        long numberOfSeats = Long.parseLong(s.nextLine());
                        System.out.print("Enter the Grid (How the seats have to be eg:2*8*2) : "); // get the grid how the seats have to be displayed
                        String grid = s.nextLine();

                        var seatsAndGrids = Utilities.generateGrids(numberOfSeats,grid); // storing seats and grids as HASHMAP by calling a helper function from utilities
                        System.out.println("The seats are as follows..");// display that seats
                        for(var seats:seatsAndGrids.entrySet())
                        {
                            System.out.println(seats.getKey()+" "+seats.getValue());
                        }
                        Screens screenObj = new Screens(screenName,numberOfSeats,seatsAndGrids);
                        HashMap<String,Screens> screensHashMap = new HashMap<>();
                        screensHashMap.put(screenName,screenObj);
                        for(Theatres theatreToCheck:checkScreenAlreadyAvailable)
                        {
                            if(theatreToCheck.getScreenNameAndObject().get(screenName).equals(screenName))
                            {
                                System.out.println("Screen already exists...");
                                continue enterNameOfScreen;
                            }
                        }
                        theatres.add(new Theatres(theatreName,screensHashMap,locationOfTheatre));
                        BookMyShow.getTheatreNameAndTheatres().put(theatreName,theatres);
                    }

                }
//                after all the for loop iteration
                System.out.println("Theatre Added Successfully");
                break;
            }
        }

        }

//  Function to add the movies
    private static void addMovies()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Available locations to add movie... ");// show available locations
        String locationOfMovie = "";
        for(String locations:BookMyShow.getLocations())
        {
            System.out.println("* "+locations);
        }
        while (true)// infinite loop to ask user to enter correct location
        {
            System.out.print("Enter the location where to add the movie :");// ask location where to add the theatre
            locationOfMovie = s.nextLine();
            if (!BookMyShow.getLocations().contains(locationOfMovie))// if that location not found in location arraylist
            {
                System.out.println("No location found...");
            }
            else// if that location is there then break the loop
            {
                break;
            }

        }
//        After the loop
                System.out.print("Enter the movie name to add : ");// get the movie name
                String movieName = s.nextLine();
                var movieList = BookMyShow.getMovieNameAndMovies();

                if(movieList==null)
                {

                }
                for(Movies movies:BookMyShow.getMovies())// loop over all the movies available
                {
                    if(movies.getName().equals(movieName) && movies.getLocation().equals(locationOfMovie))// check if the same movie already available in that location
                    {
                        System.out.println("Movie already available..");
                        return;
                    }
                }
//                if movie not available then get further details
                System.out.print("Enter the date of showing of the movie : "); // get start date of movie
                LocalDate dateOfMovie = LocalDate.parse(s.nextLine(),BookMyShow.getFormatter());
                System.out.print("Enter the duration of the movie : "); // get the duration of the movie
                long duration = Long.parseLong(s.nextLine());
                System.out.println("Available Theatres..."); // print all the available theatres
                for(Theatres theatres:BookMyShow.getTheatres()) // loop over all the available theatres
                {
                    if(theatres.getLocation().equals(locationOfMovie)) // print theatres in that location
                    {
                        System.out.println("* " + theatres.getName());
                    }
                }
                System.out.println("--------------------------");

                    System.out.println("Enter the theatre to add the movie : ");// ask the theatre where to add the movie
                    String movieToAddInTheatre = s.nextLine();
                    Theatres currentTheatre = null; // initialising a Theatre reference to null for below usage

                    for(Theatres theatres:BookMyShow.getTheatres())// loop over all the theatres
                    {
                        if (theatres.getLocation().equals(locationOfMovie))// go into theatres in that location
                        {
                            if (movieToAddInTheatre.equals(theatres.getName())) // if theatre entered matches the theatre object's name
                            {
                                currentTheatre = theatres; // re-assign current theatre with the theatre object
                                break;
                            }
                        }
                    }

                    if(currentTheatre==null) // after the for-loop if still the object is null then return
                    {
                        System.out.println("You entered wrong theatre");
                        return;
                    }
//                    if theatre object found then
                    System.out.println("Available screens.."); // print available screens
                    for(Screens screens:currentTheatre.getScreens())// loop over all the screens in theatre
                    {
                        System.out.println("* "+screens.getNameOfScreen());
                    }


                    while (true)// infinite loop to ask date
                    {
                            System.out.println("Movie is available in the following dates..."); // print all the available dates
                            for(LocalDate date = startDate; date.isBefore(endDate) || date.isEqual(endDate); date = date.plusDays(1)) // loop from start date to end date
                            {
                                System.out.println("* "+date.format(BookMyShow.getFormatter()));
                            }

                                System.out.println("Enter the date when to set the show : "); // get the date when to set the show
                                LocalDate dateChoice = LocalDate.parse(s.nextLine(),BookMyShow.getFormatter());
                                if((dateChoice.isAfter(startDate) && dateChoice.isBefore(endDate)) || (dateChoice.isEqual(startDate) || dateChoice.isEqual(endDate)))// if entered date is in the available dates
                                {
                                    System.out.println("Available screens.."); // print available screens
                                    for(Screens screens:currentTheatre.getScreens())// loop over all the screens in theatre
                                    {
                                        System.out.println("* "+screens.getNameOfScreen());
                                    }
                                    System.out.println("Enter the screen where you want to add the movie : "); // get the screen name where to add the show
                                    String screenToAddMovie = s.nextLine();
                                    screensLogic:for(Screens screens:currentTheatre.getScreens()) // loop over screens to check availability and add show
                                        if(screens.getNameOfScreen().equals(screenToAddMovie))// if that screen available in theatre
                                        {
                                            System.out.println("Enter the start time of the show (HH:mm): "); // get the start time
                                            LocalTime startTime = LocalTime.parse(s.nextLine(),BookMyShow.getTimeFormatter());
                                            LocalTime endTime = startTime.plusMinutes(duration+30); // calculate end time with start time
                                            if(screens.getShowsInScreen().isEmpty()) // if shows inside screen is empty
                                            {
                                                screens.getShowsInScreen().add(new Shows(movieName,dateChoice,startTime,endTime)); // add new show directly
                                                System.out.println("Movie added successfully..in theatre "+currentTheatre.getName()+" for screen "+screens.getNameOfScreen()+" for show "+startTime);
                                                break;
                                            }
                                            for(Shows shows:screens.getShowsInScreen())// loop over shows to check existing shows
                                            {
                                                if(shows.getDateOfShow().isEqual(dateChoice))// if show object's date and entered date equals
                                                {
//                                                    ----------------------------------
//                                                    problem in this logic have to solve
//                                                    ----------------------------------
                                                    if((startTime.isBefore(shows.getStartTime()) && endTime.isBefore(shows.getStartTime())) || (startTime.isAfter(shows.getEndTime()) && endTime.isAfter(shows.getEndTime()))) // check existing show and add show without any repetition
                                                    {
                                                        screens.getShowsInScreen().add(new Shows(movieName,dateChoice,startTime,endTime));
                                                        System.out.println("Movie added successfully..in theatre "+currentTheatre.getName()+" for screen "+screens.getNameOfScreen()+" for show "+startTime);
                                                        break screensLogic;
                                                    }

                                                }
                                            }
                                            System.out.println("Show already exists in this time..."); // or show already exists
                                        }
                                    System.out.println("Do you want to set show for another/same date..?? (y/n) : "); // ask admin wheteher to set another show
                                    String choice = s.nextLine();
                                    if(choice.equals("y"))//if yes then while continues
                                    {
                                        continue;
                                    }
//                                    or breaks
                                    break;
                                }
//                                If date does not match
                                else
                                {
                                    System.out.println("Enter correct date...");
                                }
                    }
    }


//    Function to view all the movies available
    private static void viewMovies()
    {
        System.out.println("---------------------\n");
        for(Movies movies:BookMyShow.getMovies()) // loop over all the movie object from BookMyShow
        {
            System.out.println("Movie Name : "+movies.getName()); // print movie name
            System.out.println("Location : "+movies.getLocation());// print movie location
            System.out.println("Start Date : "+movies.getStartDate());// print start date of movie
            System.out.println("End Date : "+movies.getEndDate());// print end date of movie
            System.out.println("Duration : "+movies.getDuration());// print duration of movie
            System.out.println("---------------------------");
        }
        System.out.println("\n---------------------");

    }

//    Function to view all the theatres available
    private static void viewTheatres()
    {
        System.out.println("Available Theatres...");// print available theatres
        System.out.println("----------------------------");
        for(var theatresAndObjects:BookMyShow.getTheatreNameAndTheatres())// loop over all the available theatres
        {
            for(var theatres:BookMyShow.getTheatreNameAndTheatres().get(theatresAndObjects))
            System.out.println("Name : "+theatres.getName()); // print theatre name
            System.out.println("Location : "+theatres.getLocation()); // print theatre location
            System.out.println("Available Screens.."); // print all the available screens
            for (Screens screens:theatres.getScreens()) // loop over screens of theatre
            {
                System.out.println("* "+screens.getNameOfScreen());
            }
            System.out.println("Movies Showing.."); // print all the movies showing in that theatre
            for (Movies movies:theatres.getMovies()) // Loop over all the movies in the theatre
            {
                System.out.println("* "+movies.getName());
            }
            System.out.println("\n----------------------------");
        }
        System.out.println("----------------------------");
    }
}



