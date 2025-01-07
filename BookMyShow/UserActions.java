//package BookMyShow;
//
//import java.awt.print.Book;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class UserActions {
//
//    public static User login(Scanner s)
//    {
//        ArrayList<User> users= BookMyShow.getUsers();
//        System.out.print("Enter the Username : ");
//        String userName = s.nextLine();
//        System.out.print("Enter the Password : ");
//        String password = s.nextLine();
//        for(User userToCheck:users)
//        {
//            if(userToCheck.getUserName().equals(userName) && userToCheck.getPassword().equals(password))
//            {
//                System.out.println("Login Success...!!");
//                return userToCheck;
//            }
//            else if(userToCheck.getUserName().equals(userName) && !userToCheck.getPassword().equals(password))
//            {
//                System.out.println("Invalid Username or Password....Try Again");
//                return new User(null,null,null,null);
//            }
//
//        }
//        return null;
//    }
//
//    public static void register(Scanner s)
//    {
//        System.out.println("Give Your Details to Register..");
//        System.out.print("Enter your Name :");
//        String name = s.nextLine();
//        System.out.print("Enter a username :");
//        String userName = s.nextLine();
//        System.out.print("Enter a password :");
//        String password = s.nextLine();
//        System.out.print("Enter your Location :");
//        String location = s.nextLine();
//        BookMyShow.getUsers().add(new User(userName,password,name,location));
//        System.out.println("User Registered Successfully..Now Try Login");
//
//    }
//
//    public static void showOperations(Scanner s,User currentUser)
//    {
//        System.out.print("Choose your Operations..!!");
////        System.out.print("Enter your Choice :");
////        int userChoice = Integer.parseInt(s.nextLine());
////        switch (userChoice)
////        {
////            case 1:
////                AdminActions.addLocations(s);
////                break;
////            case 2:
////                AdminActions.addTheatres(s);
////                break;
////            case 3:
////                AdminActions.addMovies(s);
////                break;
//    }
//
//
//    private static boolean displayMovies(User currentUser, LocalDate dateOfUser)
//    {
//        boolean check = false;
//        for(Movies movies : BookMyShow.getMovies())
//        {
//            if(movies.getLocation().equals(currentUser.getLocation()))
//            {
//                if((dateOfUser.isAfter(movies.getStartDate()) || dateOfUser.isEqual(movies.getStartDate())) && (dateOfUser.isBefore(movies.getEndDate()) || dateOfUser.isEqual(movies.getEndDate() )))
//                {
//                    System.out.println("*"+movies.getName());
//                    check = true;
//                }
//            }
//        }
//        return check;
//    }
//
//    public static String showMovies(User currentUser) {
//        Scanner s = new Scanner(System.in);
//        LocalDate currentDate = LocalDate.now();
//        LocalDate userDate = currentDate;
//        doChange:while (true) {
//            System.out.println("Current date : " + currentDate.format(BookMyShow.getFormatter()));
//            System.out.println("Selected date : " + userDate.format(BookMyShow.getFormatter()));
//            System.out.println("Current location : "+ currentUser.getLocation());
//
//            boolean status = displayMovies(currentUser, userDate);
//            if (!status) {
//                System.out.println("No movies available in this location..in this date..");
//            }
//            System.out.println("Enter the movie name to book : ");
//            String movieToBook = s.nextLine();
//            for(Movies movies : BookMyShow.getMovies())
//            {
//                if(movies.getLocation().equals(currentUser.getLocation()))
//                {
//                    if((userDate.isAfter(movies.getStartDate()) || userDate.isEqual(movies.getStartDate())) && (userDate.isBefore(movies.getEndDate()) || userDate.isEqual(movies.getEndDate() )))
//                    {
//                        if(movies.getName().equals(movieToBook))
//                        {
//                            for(Theatres theatres:BookMyShow.getTheatres())
//                            {
//                                for(Movies movieInTheatre : theatres.getMovies())
//                                {
//                                    if(movieInTheatre.getName().equals(movieToBook))
//                                    {
//                                        System.out.println("---------"+theatres.getName()+"----------");
//                                    }
//                                }
//                                for(Screens screens:theatres.getScreens())
//                                {
//                                    for (Shows shows:screens.getShowsInScreen())
//                                    {
//                                        if(shows.getMovieName().equals(movieToBook) && shows.getDateOfShow().isEqual(userDate))
//                                        {
//                                            System.out.print(" "+shows.getStartTime());
//                                            System.out.println();
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            System.out.print("Do you want to change your date/location (y/n) type 'exit' to logout : ");
//            String wishOfUser = s.nextLine();
//            switch (wishOfUser) {
//                case "y":
//                    while (true) {
//                        System.out.print("Enter 'date' to change date | Enter 'location' to change location | Enter 'exit' to quit : ");
//                        String changeOption = s.nextLine();
//                        switch (changeOption) {
//                            case "date":
//                                System.out.print("Enter the date when to look for movies : ");
//                                LocalDate dateOfUser = LocalDate.parse(s.nextLine(), BookMyShow.getFormatter());
//                                if (dateOfUser.isAfter(currentDate) || dateOfUser.isEqual(currentDate)) {
//                                    userDate = dateOfUser;
//                                    continue doChange;
//                                }
//                                System.out.println("Enter upcoming dates not the past days..");
//                                break;
//
//                            case "location":
//                                for (String availableLocations : BookMyShow.getLocations()) {
//                                    System.out.println("* " + availableLocations);
//                                }
//                                System.out.print("Enter the location where to look for movies : ");
//                                String locationToLook = s.nextLine();
//                                if (BookMyShow.getLocations().contains(locationToLook)) {
//                                    currentUser.setLocation(locationToLook);
//                                    continue doChange;
//                                }
//                                System.out.println("That location is not available..");
//                                break;
//                            case "exit":
//                                break doChange;
//
//                            default:
//                                System.out.println("Enter correct input ");
//                                break;
//
//                        }
//                    }
//                        case "n":
//                            System.out.println("Refreshing...!!");
//                            break;
//                        case "exit":
//                            System.out.println("Logging out");
//                            break;
//                default:
//                    System.out.println("Enter correct input..!!");
//
//            }
//        }
//        return "";
//    }
//
//
//
////            if(displayMovies(currentUser,dateOfUser))
////            {
////                System.out.print("Enter the Movie's Name to book Tickets (type 'exit' to logout | type 'change-location' to change location | type 'change-date' to change date): ");
////                String movieToBook = s.nextLine();
////                if(movieToBook.equals("exit"))
////                {
////                    return "Exit";
////                }
////                else if(movieToBook.equals(""))
////                {
////
////                }
////                for(Movies movies : BookMyShow.getMovies())
////                {
////                    if(movies.getLocation().equals(currentUser.getLocation()))
////                    {
////                        if(movies.getStartDate().isAfter(dateOfUser) && movies.getEndDate().isBefore(dateOfUser))
////                        {
////                            if(movies.getName().equals(movieToBook))
////                            {
////
////                            }
////                        }
////                    }
////            }
//
//
//    private static String changeLocation(Scanner s,User currentUser)
//    {
//        System.out.println("Available Locations...");
//        for(String location :BookMyShow.getLocations())
//        {
//            System.out.println("*"+location);
//        }
//        while (true)
//        {
//            System.out.print("Enter the location where to look for movies :");
//            String locationToChange = s.nextLine();
//            if(BookMyShow.getLocations().contains(locationToChange))
//            {
//                currentUser.setLocation(locationToChange);
//                return "Done";
//            }
//            System.out.println("The location not exist..Enter correct location..");
//        }
//    }
//
//
//}