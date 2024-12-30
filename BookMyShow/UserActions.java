package BookMyShow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserActions {

    public static User login(Scanner s)
    {
        ArrayList<User> users= BookMyShow.getUsers();
        System.out.print("Enter the Username : ");
        String userName = s.nextLine();
        System.out.print("Enter the Password : ");
        String password = s.nextLine();
        for(User userToCheck:users)
        {
            if(userToCheck.getUserName().equals(userName) && userToCheck.getPassword().equals(password))
            {
                System.out.println("Login Success...!!");
                return userToCheck;
            }
            else if(userToCheck.getUserName().equals(userName) && !userToCheck.getPassword().equals(password))
            {
                System.out.println("Invalid Username or Password....Try Again");
                return new User(null,null,null,null);
            }

        }
        return null;
    }

    public static void register(Scanner s)
    {
        System.out.println("Give Your Details to Register..");
        System.out.print("Enter your Name :");
        String name = s.nextLine();
        System.out.print("Enter a username :");
        String userName = s.nextLine();
        System.out.print("Enter a password :");
        String password = s.nextLine();
        System.out.print("Enter your Location :");
        String location = s.nextLine();
        BookMyShow.getUsers().add(new User(userName,password,name,location));
        System.out.println("User Registered Successfully..Now Try Login");

    }

    public static void showOperations(Scanner s,User currentUser)
    {
        System.out.print("Choose your Operations..!!");
//        System.out.print("Enter your Choice :");
//        int userChoice = Integer.parseInt(s.nextLine());
//        switch (userChoice)
//        {
//            case 1:
//                AdminActions.addLocations(s);
//                break;
//            case 2:
//                AdminActions.addTheatres(s);
//                break;
//            case 3:
//                AdminActions.addMovies(s);
//                break;
    }


    private static boolean displayMovies(User currentUser, LocalDate dateOfUser)
    {
        for(Movies movies : BookMyShow.getMovies())
        {
            if(movies.getLocation().equals(currentUser.getLocation()))
            {
                if(dateOfUser.isAfter(movies.getStartDate()) && dateOfUser.isBefore(movies.getEndDate()))
                {
                    System.out.println("*"+movies.getName());
                }
            }
        }

        System.out.println("No movies available in this location..");
        return false;
    }

    public static String showMovies(User currentUser)
    {
        Scanner s = new Scanner(System.in);
            System.out.print("Enter the date when to look for movies : ");
            LocalDate dateOfUser = LocalDate.parse(s.nextLine(), BookMyShow.getFormatter());
            displayMovies(currentUser, dateOfUser);
            return "";
//            if(displayMovies(currentUser,dateOfUser))
//            {
//                System.out.print("Enter the Movie's Name to book Tickets (type 'exit' to logout | type 'change-location' to change location | type 'change-date' to change date): ");
//                String movieToBook = s.nextLine();
//                if(movieToBook.equals("exit"))
//                {
//                    return "Exit";
//                }
//                else if(movieToBook.equals(""))
//                {
//
//                }
//                for(Movies movies : BookMyShow.getMovies())
//                {
//                    if(movies.getLocation().equals(currentUser.getLocation()))
//                    {
//                        if(movies.getStartDate().isAfter(dateOfUser) && movies.getEndDate().isBefore(dateOfUser))
//                        {
//                            if(movies.getName().equals(movieToBook))
//                            {
//
//                            }
//                        }
//                    }
//            }
    }

    private static String changeLocation(Scanner s,User currentUser)
    {
        System.out.println("Available Locations...");
        for(String location :BookMyShow.getLocations())
        {
            System.out.println("*"+location);
        }
        while (true)
        {
            System.out.print("Enter the location where to look for movies :");
            String locationToChange = s.nextLine();
            if(BookMyShow.getLocations().contains(locationToChange))
            {
                currentUser.setLocation(locationToChange);
                return "Done";
            }
            System.out.println("The location not exist..Enter correct location..");
        }
    }


}