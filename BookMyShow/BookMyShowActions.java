package BookMyShow;

import java.util.Scanner;

public class BookMyShowActions {


    public static void start()
    {
        Scanner s = new Scanner(System.in);
        // adding user for now for easy login
        BookMyShow.getUsers().add(new User("aswin","123","Aswin","cbe"));
        BookMyShow.getAdmins().add(new Admin("admin","123")); // create admins
        System.out.println("--------------------------");
        System.out.println("| Welcome to Book My Show |");
        System.out.println("--------------------------");
        // create a location for now
        while (true)
        {

            System.out.println("Enter Your Role: \n 1. Admin \n 2. User \n 3. Exit");
            int operationChoice = Integer.parseInt(s.nextLine());
            switch (operationChoice)
            {
                case 1:
                    Admin currentAdmin = AdminActions.login(s);
                    if(currentAdmin.getUserName() == null)
                    {
                        break;
                    }
                    else if(currentAdmin!=null)
                    {
                        AdminActions.showOperations(s,currentAdmin);
                    }
                    break;
                case 2:
                    User currentUser = UserActions.login(s);
                    if(currentUser==null)
                    {
                        System.out.println("No Account Found..Register and Try again");
                        System.out.print("Do you want to register ( y/n ) :");
                        String option = s.nextLine();
                        if(option.equals("y"))
                        {
                            UserActions.register(s);
                        }
                        else if (option.equals("n"))
                        {
                            System.out.println("Exitting..");
                            break;
                        }
                        else
                        {
                            System.out.println("Enter either 'y' or 'n' ...");
                        }
                        break;
                    }
                    else if(currentUser.getUserName() == null)
                    {
                        break;
                    }
                    else if(currentUser!=null)
                    {
                        String userMovie = UserActions.showMovies(currentUser);
                        break;
                    }

                    break;
                case 3:
                    System.out.println("Exittingg...");
                    System.exit(0);
            }
        }

    }
}