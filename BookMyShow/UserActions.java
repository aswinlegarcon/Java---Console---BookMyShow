package BookMyShow;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserActions {
    //   Method to login the user
    public static User login(Scanner s) {
        ArrayList<User> users = BookMyShow.getUsers(); // get the users list
        System.out.print("Enter the Username : "); // get the username
        String userName = s.nextLine();
        System.out.print("Enter the Password : "); // get the password
        String password = s.nextLine();
        for (User userToCheck : users) // loop over all the user objects
        {
            if (userToCheck.getUserName().equals(userName) && userToCheck.getPassword().equals(password)) // check both username and password are correct
            {
                System.out.println("Login Success...!!");
                return userToCheck;
            } else if (userToCheck.getUserName().equals(userName) && !userToCheck.getPassword().equals(password)) // check username exists but password is wrong
            {
                System.out.println("Invalid Username or Password....Try Again");
                return new User(null, null, null, null);
            }

        }
        return null; // return null if no users found
    }


    //    Function to register the user if no user available
    public static void register(Scanner s) {
        System.out.println("Give Your Details to Register..");
        System.out.print("Enter your Name :"); // get the name
        String name = s.nextLine();
        System.out.print("Enter a username :"); // get the username
        String userName = s.nextLine();
        System.out.print("Enter a password :"); // get the password
        String password = s.nextLine();
        System.out.print("Enter your Location :"); // get the location
        String location = s.nextLine();
        BookMyShow.getUsers().add(new User(userName, password, name, location)); // add new Object into users list
        System.out.println("User Registered Successfully..Now Try Login");

    }

    //    Function to view all the tickets owned by user
    public static void viewTickets(User currentUser) {
        var ticketsOfUser = currentUser.getTickets(); // get the tickets in user
        System.out.println("Tickets of User : "); // print the tickets
        for (Ticket ticket : ticketsOfUser) // loop over all the object and print every ticket
        {

//            Basic own format to print a ticket
            System.out.println();
            System.out.println("------------------------------------------------|");
            System.out.println("---" + ticket.getTheatreName() + "---               |");
            System.out.println("---" + ticket.getScreenName() + "---                |");
            System.out.println("---" + ticket.getMovieName() + "---                |");
            System.out.println("Date of Movie : " + ticket.getDateOfShow().format(BookMyShow.getFormatter()) + "  |");
            System.out.println("Show Time : " + ticket.getTimeOfShow().format(BookMyShow.getTimeFormatter()) + "  |");
            System.out.println("Total no of Tickets : " + ticket.getNoOfTickets() + "  |");
            System.out.println("Tickets : " + ticket.getSeatNumbers() + "  |");
            System.out.println("Total amount paid : " + ticket.getPrice() + "  |");
            System.out.println("-----------------------");
            System.out.println("-|||||||||||||||||||||-");
            System.out.println("-|||||||-----|||||||||-");
            System.out.println("-|||||||||||||||||||||-");
            System.out.println("-||||||-------||||||||-");
            System.out.println("------------------------------------------------|");
        }
    }

    // Method to show all the movies
    public static void showMovies(User currentUser) {
        Scanner s = new Scanner(System.in);
        ArrayList<Movies> movies = new ArrayList<>(); // movie arraylist to store the current movie objects
        HashSet<String> moviesInUserArea = new HashSet<>(); // To print the movies in the user area
        LocalDate currentDate = LocalDate.now(); // get the current date
        LocalDate userDate = currentDate; // user selected date is current date by default
        while (true) // infinite loop to run until user clicks exit
        {
            System.out.println("Current date : " + currentDate.format(BookMyShow.getFormatter())); // print the current date
            System.out.println("Selected date : " + userDate.format(BookMyShow.getFormatter())); // print the selected date
            System.out.println("Current location : "+ currentUser.getLocation()); // print the current selected location

            boolean status = displayMovies(currentUser, userDate , moviesInUserArea); // call displaymovies method and store it in boolean
            if (!status) // if it is false then no movies available there
            {
                System.out.println("No movies available in this location..in this date..");
                LocalDate tempDate = UserActions.changePreferences(currentUser,currentDate); // call change preference method and store in a Date variable (Returns date if user need to change date or if user need to change location then return null object)
                if(tempDate!=null) // if the date is not null
                {
                    userDate = tempDate; // then change the date
                }
            }
            else if(status) // if the status is true
            {
                System.out.print("Enter the movie name to book (enter 'change' to change your preferences): "); // ask movie name to book or enter change to change the preferences
                String movieToBook = s.nextLine();
                if(movieToBook.equals("change")) // if user entered change
                {
                    LocalDate tempDate = UserActions.changePreferences(currentUser,currentDate); // call the change preferences method
                    if(tempDate!=null) // if the date not null
                    {
                        userDate = tempDate; // then set date to user selected date and continue the loop
                    }
                }
                else if(moviesInUserArea.contains(movieToBook)) // if the movie name in the list
                {
                    for(var movieObj : BookMyShow.getMovieNameAndMovies().get(movieToBook)) // get the movie objects with movieName
                    {
                        if(movieObj.getLocation().equals(currentUser.getLocation()) && movieObj.getDate().isEqual(userDate)) // if the object matches this condition then
                        {
                            movies.add(movieObj); // add that object in the arraylist of movies
                        }
                    }
                    break;
                }
            }

        }
        if(movies.isEmpty()) // if the movie list empty after all condition then
        {
            System.out.println("No Movies found in that date/location..."); // print needed details
            return;
        }
        UserActions.doOperations(currentUser,movies,userDate); // if all condition false and comes to this line then call the doOperation method to make the user book tickets
    }

    //    Operations that can be done by users
    public static void doOperations(User currentUser, ArrayList<Movies> movies, LocalDate currentDate) {
        Scanner s = new Scanner(System.in);
        System.out.println("The movie availability is as below : ");
        System.out.println("Current date : " + currentDate.format(BookMyShow.getFormatter())); // print the current date
        HashMap<String, HashSet<Shows>> theatreAgainstShows = new HashMap<>(); // A hashmap to store theatre name - Shows in that theatre
        String movieName = null;
        for (Movies movie : movies) // loop over all the movie objects of movie user selected
        {
            if (theatreAgainstShows.containsKey(movie.getTheatre().getName())) // if the hashmap contains theatre name already
            {
                theatreAgainstShows.get(movie.getTheatre().getName()).add(movie.getShow()); // Then add the show object of current movie that is running in that theatre
            } else {
                HashSet<Shows> shows = new HashSet<>(); // create new hashset to put into hashmap
                shows.add(movie.getShow()); // add the show into hashset
                theatreAgainstShows.put(movie.getTheatre().getName(), shows); // put the theatre name and shows inside the hashmap
            }
            movieName = movie.getName();
        }
        for (String theatreName : theatreAgainstShows.keySet()) // loop over hashmap to take keys
        {
            System.out.println("Theatre : " + theatreName); // print theatre name
            System.out.println("Shows : " + theatreAgainstShows.get(theatreName).toString()); // print all the show start time in the theatre by overriding toString
        }
        HashSet<Shows> shows = new HashSet<>();
        LocalTime time = null;
        String theatreName = null;
        while (true) {
            System.out.print("\nEnter the theatre name to book movie : "); // get the theatre name
            theatreName = s.nextLine();
            System.out.print("Enter the show time : "); // get the showtime
            time = LocalTime.parse(s.nextLine(), BookMyShow.getTimeFormatter());
            shows = theatreAgainstShows.get(theatreName);// get the Hashset Shows of theatre
            if (shows == null) {
                System.out.println("Enter correct theatre...");
                continue;
            }
            break;
        }
        Shows currentShow = null; // initially assign show object to null for future usage
        for (Shows show : shows) // loop over all the show object
        {
            if (show.getDateOfShow().equals(currentDate) && show.getStartTime().equals(time)) // get the current show object that user entered by checking the start time
            {
                currentShow = show; // reassign the loop object to currentshow
            }
        }
        if (currentShow == null) // if null after all these then
        {
            System.out.println("Enter the correct time of show...");
        } else // or do this
        {
            System.out.println("-------------- " + currentShow.getScreens().getNameOfScreen() + " ------------------"); // print the screen name
            System.out.println("------- Total Seats : " + currentShow.getScreens().getNumberOfSeats() + " ------------------"); // print the total seats
            var seatsAndGrids = currentShow.getSeatsAndGrid(); // get the seats and grids hashmap and store it
            HashMap<Character, ArrayList<String>> duplicateSeatsAndGrids = new HashMap<>();
            System.out.println("The seats are as follows..");// display that seats
            for (var seats : seatsAndGrids.entrySet()) // loop over hashmap as key-value
            {
                System.out.println("Row : " + seats.getKey() + " " + seats.getValue());  // print the key and the value
            }
            System.out.print("Enter how many seats to book : "); // get number of seats to book
            int noOfSeats = Integer.parseInt(s.nextLine());
            int noOfTickets = noOfSeats; // storing into another variable for passing into ticket object
            int totalAmount = currentShow.getPrice() * noOfSeats; // total amount of tickets
            int seatCount = 1; // Integer to print as Seat 1/Seat 2 etc
            HashSet<String> totalSeatNumbers = new HashSet<>(); // To store Total seats booked as A1 A2
            totalSeatNumbers = UserActions.bookShows(currentShow,seatCount,noOfSeats); // function to book the show
            if(totalSeatNumbers==null)
            {
                System.out.println("Continuing..");
                return;
            }
            System.out.println("Payment of Rs : " + totalAmount + " has been made"); // print the payment made
//                        adding ticket object
            currentUser.getTickets().add(new Ticket(theatreName, movieName, currentShow.getScreens().getNameOfScreen(), currentDate, time, noOfTickets, totalSeatNumbers, currentShow.getPrice() * noOfTickets));
//
//                  Ticket printing
                System.out.println("Your ticket is as below..");
                System.out.println();
                System.out.println("----------------------------");
                System.out.println("----------------------------");
                System.out.println("---" + theatreName + "---");
                System.out.println("---" + currentShow.getScreens().getNameOfScreen() + "---");
                System.out.println("---" + movieName + "---");
                System.out.println("Date of Movie : " + currentDate.format(BookMyShow.getFormatter()));
                System.out.println("Show Time : " + time.format(BookMyShow.getTimeFormatter()));
                System.out.println("Total no of Tickets : " + noOfTickets);
                System.out.println("Tickets : " + totalSeatNumbers);
                System.out.println("Total amount paid : " + totalAmount);
                System.out.println("----------------------------");
                System.out.println("----------------------------");
            }
        }

//      helper method to do operation to book shows
    private static HashSet<String> bookShows(Shows currentShow,int seatCount,int noOfSeats)
    {
        Scanner s = new Scanner(System.in);
        HashSet<String> seatNumbers = new HashSet<>(); // to store the seat numbers temporarily
        //        Make duplicate hashmap
        HashMap<Character, ArrayList<String>> duplicateSeatsAndGrids = new HashMap<>(); // creating a duplicate hashmap
        for(var keysAndValues : currentShow.getSeatsAndGrid().entrySet()) // getting seats and grids as keys and values
        {
            duplicateSeatsAndGrids.put(keysAndValues.getKey(), new ArrayList<>()); // add the keys into the duplicate seats and grids
            duplicateSeatsAndGrids.get(keysAndValues.getKey()).addAll(keysAndValues.getValue()); // get the key and add the value
        }
        while (noOfSeats > 0) // loops until no of seats greater than 0
        {
            System.out.print("Enter the seatNo where to book the seat " + seatCount + " (A1,B4,C2): "); // get seat numbers
            String rowToBook = s.nextLine();
            char row = rowToBook.charAt(0); // get the Row as A,B etc
            int seatNo = Integer.parseInt(rowToBook.substring(1)); // get the seat no in that row 1,2,10,6 etc

            if (!duplicateSeatsAndGrids.containsKey(row)) // if that row not in hashmap then print this
            {
                System.out.println("Row not available enter correct row..");
                continue;
            }
            var gridAsString = currentShow.getScreens().getGrid().split("\\*"); // get the grid pattern from the screen and store it as String array
            int sumOfString = 0; // variable to calculate sum of grids
            for (String grid : gridAsString)
            {
                sumOfString = sumOfString + Integer.parseInt(grid); // calculating sum of grids
            }
            if(seatNo>sumOfString) // if entered seat.no greater than seats
            {
                System.out.println("Enter correct ticket..");
                continue;
            }
            String seatToBook = null; // getting that seat to check already booked or available

            if (seatNo <= Integer.parseInt(gridAsString[0])) // if the seat.no less than first set of grid (before first space) eg: seat number <= 2
            {
                seatToBook = duplicateSeatsAndGrids.get(row).get(seatNo - 1); // get the seat number as per index
                if (seatToBook.equals("X")) // if the selected seat is X that is already booked
                {
                    System.out.println("Seat already booked..");
                    continue;
                }
                duplicateSeatsAndGrids.get(row).set(seatNo - 1, "X");// set the seat number as per index
                System.out.println(duplicateSeatsAndGrids.get(row));
                seatNumbers.add(rowToBook); // add the seat number
                noOfSeats--; // reduce the no of seats to be booked to control the while loop
                seatCount++; // increase the seat count for printing purpose
            } else if (seatNo >= (sumOfString + 1) - Integer.parseInt(gridAsString[2]))// if the seat.no greater than last space eg: seatNo >= 9
            {
                seatToBook = duplicateSeatsAndGrids.get(row).get(seatNo + 1); // get the seat number plus 1 index
                if (seatToBook.equals("X")) // if the selected seat is X that is already booked
                {
                    System.out.println("Seat already booked..");
                    continue;
                }
                duplicateSeatsAndGrids.get(row).set(seatNo + 1, "X");// get the seat number plus 1 index
                System.out.println(duplicateSeatsAndGrids.get(row));
                seatNumbers.add(rowToBook); // add the seat number
                noOfSeats--; // reduce the no of seats to be booked to control the while loop
                seatCount++; // increase the seat count for printing purpose
            } else if (seatNo > Integer.parseInt(gridAsString[0])) // if the seat is greater than 1st space eg : seatNo > 2
            {
                seatToBook = duplicateSeatsAndGrids.get(row).get(seatNo); // get the number as it is
                if (seatToBook.equals("X")) // if the selected seat is X that is already booked
                {
                    System.out.println("Seat already booked..");
                    continue;
                }
                duplicateSeatsAndGrids.get(row).set(seatNo, "X");// get the number as it is
                System.out.println(duplicateSeatsAndGrids.get(row));
                seatNumbers.add(rowToBook); // add the seat number
                noOfSeats--; // reduce the no of seats to be booked to control the while loop
                seatCount++; // increase the seat count for printing purpose
            }
        }

        while (true)
        {
            System.out.print("Are you sure do you want to book these tickets : (y/n) : "); // Ask confirmation to book that ticket
            String ticketConfirmChoice = s.nextLine();
            if(ticketConfirmChoice.equalsIgnoreCase("y")) // if yes
            {
                currentShow.setSeatsAndGrid(duplicateSeatsAndGrids); // set the seats booked after confirmation
                break;
            }
            else if(ticketConfirmChoice.equalsIgnoreCase("n")) // if no then cancel the booking
            {
                System.out.println("Canceled Booking..");
                return null; // if any problem then returning 0
            }
            else // if wrong input
            {
                System.out.println("Enter correct input..");
            }
        }
        return seatNumbers;
    }




//Helper function for showMovies to list all the movies
    private static boolean displayMovies(User currentUser, LocalDate dateOfUser , HashSet<String> moviesInUserArea)
    {
        boolean mainCheck = false;// to check whether any one movie is available or not
        for(var movies : BookMyShow.getMovieNameAndMovies().keySet()) // get movie hashmap and get keys to loop over keys
        {
            boolean check = false; // everytime movie comes the boolean turns false
            for(var movieObj : BookMyShow.getMovieNameAndMovies().get(movies))  // loop over the key and get objects
            {

                if(movieObj.getLocation().equals(currentUser.getLocation()) && movieObj.getDate().isEqual(dateOfUser)) // if the movie object matches location and date
                {
                    check = true; // if that movie is in current location and date then it turns true
                }
            }
            if(check) // if it is true (means a movie is available)
            {
                mainCheck = true;
                System.out.println("*"+movies); // print the movies there
                moviesInUserArea.add(movies); // add movies in Hashset
            }

        }
        return mainCheck; // return the boolean value
    }

//    Helper method to showMovies method to change the location or date of the user
    private static LocalDate changePreferences(User currentUser, LocalDate currentDate)
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Do you want to change your date/location (y/n) type 'exit' to logout : "); // ask the user what to do
        String wishOfUser = s.nextLine();
        switch (wishOfUser) {
            case "y": // if yes
                mainLoop:while (true) {
                    System.out.print("Enter 'date' to change date | Enter 'location' to change location | Enter 'exit' to quit : "); // ask user what to change
                    String changeOption = s.nextLine();
                    switch (changeOption) {
                        case "date": // if date
                            System.out.print("Enter the date when to look for movies : "); // get the date from the user
                            LocalDate dateOfUser = LocalDate.parse(s.nextLine(), BookMyShow.getFormatter());
                            if (dateOfUser.isAfter(currentDate) || dateOfUser.isEqual(currentDate)) { // check the date is not in past
                                return dateOfUser;
                            }
                            System.out.println("Enter upcoming dates not the past days.."); // print if past date
                            break;

                        case "location": // if user chose to change location
                            for (String availableLocations : BookMyShow.getLocations()) {
                                System.out.println("* " + availableLocations); // print all the available location
                            }
                            System.out.print("Enter the location where to look for movies : "); // get the location where to look for movies
                            String locationToLook = s.nextLine();
                            if (BookMyShow.getLocations().contains(locationToLook)) { // if that location available
                                currentUser.setLocation(locationToLook); // set the location
                                return null; // return the date as null
                            }
                            System.out.println("That location is not available.."); // or print no location found
                            break;
                        case "exit": // if exit then
                            break mainLoop; // break the main loop

                        default: // if wrong input
                            System.out.println("Enter correct input ");
                            break;

                    }
                }
            case "n": // if user chose no
                System.out.println("Refreshing...!!");
                return null;
            case "exit": // if user chose exit
                System.out.println("Logging out");
                return null;
            default: // if no correct input
                System.out.println("Enter correct input..!!");

        }
        return null;
    }


}