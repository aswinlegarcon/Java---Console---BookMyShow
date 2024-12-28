package BookMyShow;

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
            else
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


    public static String showMovies(Scanner s,User currentUser)
    {
        changeLocation:while (true)
        {
            var moviesList = BookMyShow.getLocationAndMovies().get(currentUser.getLocation());
            if (moviesList!=null)
            {
                while (true)
                {

                    System.out.println("The Movies available in your areas are as follows..");
                    int no = 1;
                    for(var movies:moviesList)
                    {
                        System.out.println(no+" "+movies.getName());
                        no++;
                    }
                    System.out.print("Enter the Movie's Name to book Tickets (type 'exit' to logout | type 'change' to change location): ");
                    String userSelectedMovie = s.nextLine();
                    if(userSelectedMovie.equals("exit"))
                    {
                        return null;
                    }
                    if(userSelectedMovie.equals("change"))
                    {
                        String toCheck = UserActions.changeLocation(s,currentUser);
                        if (toCheck.equals("Done"))
                        {
                            continue changeLocation;
                        }
                    }
                    for(var movies:moviesList)
                    {
                        if(movies.getName().equals(userSelectedMovie))
                        {
                            System.out.println("You selected "+ userSelectedMovie);
                            System.out.println("The Info about movie is :");
                            System.out.println("Release date : "+movies.getDate().format(BookMyShow.getFormatter()));
                            System.out.println("Duration : "+movies.getDuration()+" mins");
                            return userSelectedMovie;
                        }
                    }
                    System.out.println("The entered movie is not available..Try again");

                }
            }
            else
            {
                System.out.println("There are no movies available in the area..");
            }
            return null;
        }
        }


    private static String changeLocation(Scanner s,User currentUser)
    {
        var locations = BookMyShow.getLocationAndMovies().entrySet();
        System.out.println("Available Locations...");
        for(var key:locations)
        {
            System.out.println(key.getKey());
        }
        while (true)
        {
            System.out.print("Enter the location where to look for movies :");
            String locationToChange = s.nextLine();
            if(BookMyShow.getLocationAndTheatres().containsKey(locationToChange))
            {
                currentUser.setLocation(locationToChange);
                return "Done";
            }
            System.out.println("The location not exist..Enter correct location..");
        }
    }


}