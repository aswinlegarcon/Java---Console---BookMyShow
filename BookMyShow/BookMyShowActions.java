package BookMyShow;

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
                    Admin currentAdmin = AdminActions.login(s); // getting admin object and storing (returns null if no admins - returns admin if vaild credentials - return username as null if password wrong)
                    if(currentAdmin.getUserName() == null)// check if username is null
                    {
                        break;
                    }
                    else if(currentAdmin!=null)// if not null
                    {
                        AdminActions.showOperations(s,currentAdmin);
                    }
                    break;
//                    If User came into Book My Show
                case 2:
                    User currentUser = UserActions.login(s);// getting user object and storing (returns null if no users - returns user if vaild credentials - return username as null if password wrong)
                    if(currentUser==null) // check if username is null (if null then ask user want to register or not)
                    {
                        System.out.println("No Account Found..Register and Try again");
                        System.out.print("Do you want to register ( y/n ) :");//asking user to enter the choice
                        String option = s.nextLine();
                        if(option.equals("y")) // if yess then register the user
                        {
                            UserActions.register(s);
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
                        String userMovie = UserActions.showMovies(currentUser);
                        break;
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