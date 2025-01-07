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
                for(var theatres:BookMyShow.getTheatreNameAndTheatre().keySet())// loop over available theatres
                {
                    if(theatres.equals(theatreName))//If the name already exists then return
                    {
                        System.out.println("Theatre already exists");
                        return;
                    }

                }
//                Or get other needed details
                System.out.print("Enter No.of.Screens to add: ");// get the no of screens in theatre
                int noOfScreens = Integer.parseInt(s.nextLine());
                HashMap<String,Screens> screensHashMap = new HashMap<>();
                for(int i = 1;i<=noOfScreens;i++) // loop as many screens need to add (EG: if 2 screens then 2 times)
                {
                    enterNameOfScreen:while (true)
                    {
                        System.out.print("Enter the Name of Screen "+ i +": ");//get the name of screen i
                        String screenName = s.nextLine();
                        var checkScreenAlreadyAvailable = BookMyShow.getTheatreNameAndTheatre().get(theatreName);
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

                        screensHashMap.put(screenName,screenObj);
                        if(checkScreenAlreadyAvailable!=null)
                        {
                                var screenKeys = checkScreenAlreadyAvailable.getScreenNameAndObject().keySet();
                                for(var screenKeyName:screenKeys)
                                {
                                    if(screenKeyName.equals(screenName))
                                    {
                                        System.out.println("Screen already exists...");
                                        continue enterNameOfScreen;
                                    }
                                }
                        }
                        break;
                    }

                }
                Theatres theatres = new Theatres(theatreName,screensHashMap,locationOfTheatre);
                BookMyShow.getTheatreNameAndTheatre().put(theatreName,theatres);
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

        System.out.println("Available locations to add movies... ");// show available locations
        for(String locations:BookMyShow.getLocations())
        {
            System.out.println("* "+locations);
        }
        String locationOfTheatre = null;
        while (true)// infinite loop to ask user to enter correct location
        {
            System.out.print("Enter the Location where to add the movies :");// ask location where to add the theatre
            locationOfTheatre = s.nextLine();
            if (!BookMyShow.getLocations().contains(locationOfTheatre)) // if that location not found in location arraylist
            {
                System.out.println("No Locations Found");
                continue;
            }
            break;
        }
             // if that location is there then ask the movie name
                System.out.print("Enter the Movie name to add : ");// get movie name
                String movieName = s.nextLine();
                System.out.print("Enter the Date of the movie (dd-mm-yyyy): ");
                LocalDate dateOfMovie = LocalDate.parse(s.nextLine(),BookMyShow.getFormatter());
                System.out.print("Enter the Duration of the movie : ");
                long duration = Long.parseLong(s.nextLine());
                System.out.println("Available Theatres :");
                for(var theatres:BookMyShow.getTheatreNameAndTheatre().keySet())
                {
                    if(BookMyShow.getTheatreNameAndTheatre().get(theatres).getLocation().equals(locationOfTheatre))
                    {
                        System.out.println("* "+ theatres);
                    }
                }
                System.out.print("Enter the theatre where to add the movie : ");
                String theatreOfMovie = s.nextLine();
                for(var theatres:BookMyShow.getTheatreNameAndTheatre().keySet())
                {
                    if(!theatres.equals(theatreOfMovie))
                    {
                        System.out.println("No theatres found..");
                        return;
                    }
                }
                Theatres theatres = BookMyShow.getTheatreNameAndTheatre().get(theatreOfMovie);
                System.out.println("Available Screens...!!");
                for(var screens:theatres.getScreenNameAndObject().keySet())
                {
                    System.out.println("* "+screens);
                }
                System.out.print("Enter the screen name to add : ");
                String screenNameForMovie = s.nextLine();
                Screens screen = null;
                for(var screens:theatres.getScreenNameAndObject().keySet())
                {
                    if(screens.equals(screenNameForMovie))
                    {
                        screen = theatres.getScreenNameAndObject().get(screens);
                    }
                }
                if(screen==null)
                {
                    System.out.println("No screens found..!");
                    return;
                }
                System.out.print("Enter the start time of the Show (hh:mm) : ");
                LocalTime startTime = LocalTime.parse(s.nextLine(),BookMyShow.getTimeFormatter());
                LocalTime endTime = startTime.plusMinutes(duration + 30);
                Shows currentShow = new Shows(movieName,dateOfMovie,startTime,endTime);
                if(screen.getShowsInScreen().contains(currentShow))
                {
                    System.out.println("Show already exists..");
                    return;
                }
                screen.getShowsInScreen().add(currentShow); // calls hashCode and equals method which is overridden
                Movies currentMovie = new Movies(movieName,locationOfTheatre,dateOfMovie,duration,theatres,screen,currentShow);
                var movieList = BookMyShow.getMovieNameAndMovies().get(movieName);
                if(movieList==null)
                {
                    movieList = new ArrayList<>();
                }
                for(Movies movieObj:movieList)
                {
                    if(movieObj.getDate().isEqual(dateOfMovie) &&
                            movieObj.getDuration()==duration &&
                            movieObj.getLocation().equals(locationOfTheatre) &&
                            movieObj.getTheatre().getName().equals(theatreOfMovie) &&
                            movieObj.getScreen().getNameOfScreen().equals(screenNameForMovie))
                    {
                        System.out.println("Movie already exists..");
                        return;
                    }
                }
                movieList.add(currentMovie);
                BookMyShow.getMovieNameAndMovies().put(movieName,movieList);
                System.out.println("Movie added successfully in "+theatreOfMovie+" on "+dateOfMovie.format(BookMyShow.getFormatter())+" at "+startTime.format(BookMyShow.getTimeFormatter()));


    }


//    Function to view all the movies available
    private static void viewMovies()
    {
        System.out.println("---------------------\n");
        var keySet = BookMyShow.getMovieNameAndMovies().keySet();
        if(keySet==null)
        {
            System.out.println("No movies found...Add movie and try again");
            return;
        }
        for(var movies:keySet)
        {
            System.out.println("Movie Name : "+movies);
            var movieList = BookMyShow.getMovieNameAndMovies().get(movies);
            for(Movies movieObj:movieList)
            {
                System.out.println("\n---------------------");
                System.out.println("Location : "+ movieObj.getLocation());
                System.out.println("Theatre : "+ movieObj.getTheatre().getName());
                System.out.println("Date : "+ movieObj.getDate().format(BookMyShow.getFormatter()));
                System.out.println("Show Start Time : "+movieObj.getShow().getStartTime().format(BookMyShow.getTimeFormatter()));
                System.out.println("Show End Time : "+movieObj.getShow().getEndTime().format(BookMyShow.getTimeFormatter()));
                System.out.println("\n---------------------");
            }
        }
        System.out.println("\n---------------------");

    }

//    Function to view all the theatres available
    private static void viewTheatres()
    {
        System.out.println("Available Theatres...");// print available theatres
        System.out.println("----------------------------");

        for(var theatreName : BookMyShow.getTheatreNameAndTheatre().keySet())
        {
            System.out.println("Name : "+theatreName);
            Theatres theatres = BookMyShow.getTheatreNameAndTheatre().get(theatreName);
            System.out.println("Location : "+ theatres.getLocation());
            for(var screens:theatres.getScreenNameAndObject().keySet())
            {
                System.out.println("Screen Name : "+ screens);
                System.out.println("Number of seats : "+theatres.getScreenNameAndObject().get(screens).getNumberOfSeats());
                System.out.println("* "+theatres.getScreenNameAndObject().get(screens).getSeatsAndGrid());
            }
        }
            System.out.println("\n----------------------------");
        }

    }




