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
                return adminToCheck;
            }
            else if(adminToCheck.getUserName().equals(userName) && !adminToCheck.getPassword().equals(password))// if password alone wrong then return username null
            {
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

                        System.out.print("Enter the Name of Screen "+ i +": ");//get the name of screen i
                        String screenName = s.nextLine();
                        var checkScreenAlreadyAvailable = BookMyShow.getTheatreNameAndTheatre().get(theatreName); // getting hashmap with key theatre name
                        System.out.print("Enter the number of seats :");// get the number of seats in that screen
                        long numberOfSeats = Long.parseLong(s.nextLine());
                        System.out.print("Enter the Grid (How the seats have to be eg:2*8*2) : "); // get the grid how the seats have to be displayed
                        String grid = s.nextLine();
                        var seatsAndGrids = Utilities.generateGrids(numberOfSeats,grid); // storing seats and grids as HASHMAP by calling a helper function from utilities
//                        System.out.println("The seats are as follows..");// display that seats
//                        for(var seats:seatsAndGrids.entrySet())
//                        {
//                            System.out.println(seats.getKey()+" "+seats.getValue());
//                        }
                        Screens screenObj = new Screens(screenName,numberOfSeats,grid,seatsAndGrids); // creating new screen object with all details

                        screensHashMap.put(screenName,screenObj); // adding screen object with nameofscreen,screenobj in hashmap
                        if(checkScreenAlreadyAvailable!=null) // if theatre object not null
                        {
                                var screenKeys = checkScreenAlreadyAvailable.getScreenNameAndObject().keySet(); // get existing screen objects with keys
                                if(screenKeys.contains(screenName)) // if the screen name already exist in theatre then print already exists
                                {
                                    System.out.println("Screen already exists...");
                                    return;
                                }
                        }
                }
                Theatres theatres = new Theatres(theatreName,screensHashMap,locationOfTheatre); // create theatre object
                BookMyShow.getTheatreNameAndTheatre().put(theatreName,theatres); // add theatre into hashmap with theatre name and theatre object
//                after all the for loop iteration
                System.out.println("Theatre Added Successfully");
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
                System.out.print("Enter the Date of the movie (dd-mm-yyyy): "); // get date of movie when it has to be displayed
                LocalDate dateOfMovie = LocalDate.parse(s.nextLine(),BookMyShow.getFormatter()); // change date as per needed format dd-mm-yyyy
                System.out.print("Enter the Duration of the movie : "); // get the duration of movie
                long duration = Long.parseLong(s.nextLine());
                System.out.print("Enter the price of the Movie : ");
                int priceOfMovie = Integer.parseInt(s.nextLine());
                System.out.println("Available Theatres :"); // print all the available theatres
                for(var theatres:BookMyShow.getTheatreNameAndTheatre().keySet())
                {
                    if(BookMyShow.getTheatreNameAndTheatre().get(theatres).getLocation().equals(locationOfTheatre))
                    {
                        System.out.println("* "+ theatres);
                    }
                }
                String theatreOfMovie = null;
                while (true)
                {
                    System.out.print("Enter the theatre where to add the movie : "); // get theatre name
                    theatreOfMovie = s.nextLine();
                    var theatre = BookMyShow.getTheatreNameAndTheatre().keySet(); // get theatre name as string as key from hashmap
                    if(!theatre.contains(theatreOfMovie)) { // if no theatre then print no theatres in list
                        System.out.println("No theatres found..Enter correct theatre");
                        continue;
                    }
                    break;
                }
                Theatres theatres = BookMyShow.getTheatreNameAndTheatre().get(theatreOfMovie); // get theatre object with theatre name
                System.out.println("Available Screens...!!"); // print all the screens
                for(var screens:theatres.getScreenNameAndObject().keySet())
                {
                    System.out.println("* "+screens);
                }
                String screenNameForMovie = null;
                Screens screen = null; // assign null screen object to get and assign inside for loop
                while (true)
                {
                    System.out.print("Enter the screen name to add : "); // get the screen name
                    screenNameForMovie = s.nextLine();
                    var screens = theatres.getScreenNameAndObject().keySet(); // get screen hashmap as keyset (only keys)
                    if(screens.contains(screenNameForMovie)) // check if screenname key is inside the screen list
                    {
                        screen = theatres.getScreenNameAndObject().get(screenNameForMovie); // set the screen obj to the available screen
                    }
                    if(screen==null)
                    {
                        System.out.println("No screens found..!");
                        continue;
                    }
                    break;
                }
                LocalTime startTime = null;
                LocalTime endTime = null;
                HashMap<Character,ArrayList<String>> seatsAndGrids = null;
                while (true)
                {
                    System.out.print("Enter the start time of the Show (hh:mm) : "); // get the start time
                    startTime = LocalTime.parse(s.nextLine(),BookMyShow.getTimeFormatter());
                    endTime = startTime.plusMinutes(duration + 30); // calculate end time
//                Making seats and grids for shows
                   seatsAndGrids = Utilities.generateGrids(screen.getNumberOfSeats(),screen.getGrid());
                    for(var show : screen.getShowsInScreen()) // loop over show objects to check it same show available already
                    {
//                    Condition to check if the show is before old show timings or after the old show timings
                        if((startTime.isBefore(show.getStartTime()) || endTime.isBefore(show.getStartTime())) &&
                                (startTime.isAfter(show.getEndTime()) || endTime.isAfter(show.getEndTime())) && dateOfMovie.isEqual(show.getDateOfShow()))
                        {
                            System.out.println("Show already exists.."); // if yes print this
                            return;
                        }
                    }
                    break;
                }
                Shows currentShow = new Shows(dateOfMovie,startTime,endTime,screen,seatsAndGrids,priceOfMovie);  // if no show already exist then create a show object
                if(screen.getShowsInScreen().contains(currentShow)) // if the show with same start time and same end time and same date exist then it will go inside (equals method overridden in shows)
                {
                    System.out.println("Show already exists..");
                    return;
                }
                screen.getShowsInScreen().add(currentShow); // add the show object in shows hashset -- calls hashCode and equals method which is overridden
                Movies currentMovie = new Movies(movieName,locationOfTheatre,dateOfMovie,duration,theatres,screen,currentShow); // create a movie object with all the required details
                var movieList = BookMyShow.getMovieNameAndMovies().get(movieName); // get the arraylist of movies from hashmap by passsing key
                if(movieList==null) // if it is null
                {
                    movieList = new ArrayList<>(); // create a new arraylist
                }
                movieList.add(currentMovie); // add the movie object into movie list arraylist
                BookMyShow.getMovieNameAndMovies().put(movieName,movieList); // put the movie name and movie object inside hashmap
                System.out.println("Movie added successfully in "+theatreOfMovie+" on "+dateOfMovie.format(BookMyShow.getFormatter())+" at "+startTime.format(BookMyShow.getTimeFormatter()));


    }


//    Function to view all the movies available
    private static void viewMovies()
    {
        System.out.println("---------------------\n");
        var keySet = BookMyShow.getMovieNameAndMovies().keySet(); // get the keys from the hashmap
        if(keySet.isEmpty()) // if it is null
        {
            System.out.println("No movies found...Add movie and try again");
            return;
        }
        for(var movies:keySet) // loop the keys and pass keys into .get() method to get values
        {
            System.out.println("Movie Name : "+movies); // print movie name
            var movieList = BookMyShow.getMovieNameAndMovies().get(movies); // get the movie object
            for(Movies movieObj:movieList) // loop over movie object to get all details
            {
                System.out.println("\n---------------------");
                System.out.println("Location : "+ movieObj.getLocation()); // print the location
                System.out.println("Theatre : "+ movieObj.getTheatre().getName()); // print the theatre name where the movie is
                System.out.println("Date : "+ movieObj.getDate().format(BookMyShow.getFormatter())); // print the date of the movie
                System.out.println("Screen : "+ movieObj.getScreen().getNameOfScreen()); // print the screen where the movie is showing
                System.out.println("Show Start Time : "+movieObj.getShow().getStartTime().format(BookMyShow.getTimeFormatter())); // print the start time of the show
                System.out.println("Show End Time : "+movieObj.getShow().getEndTime().format(BookMyShow.getTimeFormatter())); // print the end time of the show
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

        for(var theatreName : BookMyShow.getTheatreNameAndTheatre().keySet()) // get the theatre hashmap as keyset to extract keys
        {
            System.out.println("Name : "+theatreName); // print theatre name
            Theatres theatres = BookMyShow.getTheatreNameAndTheatre().get(theatreName); // get the theatre object by passing the key (theatrename)
            System.out.println("Location : "+ theatres.getLocation()); // print the location of theatre
            for(var screens:theatres.getScreenNameAndObject().keySet()) // get the screen hashmap as keys
            {
                System.out.println("Screen Name : "+ screens); // print the screen name
                System.out.println("Number of seats : "+theatres.getScreenNameAndObject().get(screens).getNumberOfSeats()); // print the number of seats in the screen
                System.out.println("* "+theatres.getScreenNameAndObject().get(screens).getSeatsAndGrid()); // print the pattern how the screen is constructed
            }
        }
            System.out.println("\n----------------------------");
        }

    }




