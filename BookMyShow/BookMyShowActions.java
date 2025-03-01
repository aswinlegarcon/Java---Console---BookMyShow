package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BookMyShowActions {


    public static void start() // starting the book my show
    {
        Scanner s = new Scanner(System.in);
        // adding user for now for easy login
        BookMyShow.getUsers().add(new User("aswin","123","Aswin","cbe")); // adding a temporary user for login
        BookMyShow.getAdmins().add(new Admin("admin","123")); // adding a temporary admin for login
        System.out.println("--------------------------");
        System.out.println("| Welcome to Book My Show |");
        System.out.println("--------------------------");
        // create a location for now
        while (true) // infinite loop until user clicks exit
        {

            System.out.println("Enter Your Role: \n 1. Admin \n 2. User \n 3. Exit"); // ask what to do in the app
            int operationChoice = Integer.parseInt(s.nextLine());
            switch (operationChoice)
            {
//                If Admin came into Book My Show
                case 1:
                    AdminActions adminActions = new AdminActions();
                    Account currentAdmin = adminActions.login(); // getting admin object and storing (returns null if no admins - returns admin if vaild credentials - return username as null if password wrong)
                    if(currentAdmin == null)// if not null
                    {
                        System.out.println("Username or password is wrond..");
                        break;
                    }
                    else
                    {
                        System.out.println("Login Success...!!");
                        if(currentAdmin instanceof Admin)
                        {
                            adminActions.showOperations((Admin)currentAdmin);
                        }
                    }
                    break;
//                    If User came into Book My Show
                case 2:
                    UserActions userActions = new UserActions();
                    Account currentUser = userActions.login();// getting user object and storing (returns null if no users - returns user if vaild credentials - return username as null if password wrong)

                    if(currentUser==null) // check if username is null (if null then ask user want to register or not)
                    {
                        System.out.println("No Account Found..Register and Try again");
                        System.out.print("Do you want to register ( y/n ) :");//asking user to enter the choice
                        String option = s.nextLine();
                        if(option.equals("y")) // if yess then register the user
                        {
                            userActions.register();
                        }
                        else if (option.equals("n"))// if noo the exits
                        {
                            System.out.println("Exitting..");
                            break;
                        }
                        else// if wrong input
                        {
                            System.out.println("Enter either 'y' or 'n' ...");
                        }
                        break;
                    }
                    else if(currentUser.getUserName() == null) // if wrong password then break
                    {
                        break;
                    }
                    else if(currentUser!=null) // if vaild credentials then call show movies
                    {
                        if(currentUser instanceof User)
                        {
                            userActions.showMovies((User)currentUser); // show the movie
                        }
                        main:while (true)
                        {
                        System.out.println("Enter the option what to do \n 1.Continue Booking Tickets \n 2.View All Tickets \n 3.Logout");
                        int choiceToDo = Integer.parseInt(s.nextLine());
                                switch (choiceToDo)
                                {
//                                    If need to continue ticket booking
                                    case 1:
                                        if(currentUser instanceof User)
                                        {
                                            userActions.showMovies((User)currentUser);
                                        }
                                        break;
//                                        If need to view Tickets owned by user
                                    case 2:
                                        if(currentUser instanceof User)
                                        {
                                            userActions.viewTickets((User)currentUser);
                                        }
                                        break;
//                                        If need to logout
                                    case 3:
                                        System.out.println("Logging out..");
                                        break main;
                                }
                        }
                    }

                    break;
//                    If want to exit
                case 3:
                    System.out.println("Exittingg...");
                    System.exit(0);
            }
        }

    }
}