package BookMyShow;

import java.util.Scanner;

public class BookMyShowActions {


    public static void start()
    {
        Scanner s = new Scanner(System.in);
        // adding user for now for easy login
        BookMyShow.getUsers().add(new User("aswin","123","Aswin","CBE"));
        BookMyShow.getAdmins().add(new Admin("admin","123")); // create admins
        BookMyShow.getLocation().add("CBE"); // create a location for now
        while (true)
        {
            System.out.println("Enter Your Role: \n 1. Admin \n 2. User \n 3. Exit");
            int operationChoice = Integer.parseInt(s.nextLine());
            switch (operationChoice)
            {
                case 1:
                    Admin currentAdmin = AdminActions.login(s);
                    if(currentAdmin!=null)
                    {
                        AdminActions.showOperations(s,currentAdmin);
                    }
                    break;
                case 2:
                    User currentUser = UserActions.login(s);
                    if(currentUser!=null)
                    {
                        UserActions.showOperations(s,currentUser);
                    }
                    else if(currentUser==null)
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
                case 3:
                    System.out.println("Exittingg...");
                    System.exit(0);
            }
        }

    }
}
