package BookMyShow;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminActions {

    public static Admin login(Scanner s)
    {
         ArrayList<Admin> admins= BookMyShow.getAdmins();
        System.out.print("Enter the Username : ");
        String userName = s.nextLine();
        System.out.print("Enter the Password : ");
        String password = s.nextLine();
        for(Admin adminToCheck:admins)
        {
            if(adminToCheck.getUserName().equals(userName) && adminToCheck.getPassword().equals(password))
            {
                System.out.println("Login Success...!!");
                return adminToCheck;
            }
            else
            {
                System.out.println("Invalid Username or Password....Try Again");
                return new Admin(null,null);
            }

        }
        return null;
    }

    public static void showOperations(Scanner s,Admin currentAdmin)
    {
        System.out.println("Choose your Operations..!! \n 1. Add Location\n 2. Add Theatre");
        System.out.print("Enter your Choice :");
        int adminChoice = Integer.parseInt(s.nextLine());
        switch (adminChoice)
        {
            case 1:
                System.out.print("Enter the Location name to add :");
                String locationToAdd = s.nextLine();
                BookMyShow.getLocation().add(locationToAdd);
                break;
            case 2:
                ArrayList<String> moviesInTheatre = new ArrayList<>();
                System.out.print("Enter the Theater Name to add :");
                String theatreNameToAdd = s.nextLine();
                System.out.println("Enter the location of Theatre : ");
                String locationOfTheatre = s.nextLine();
                System.out.print("Enter the Movies to add (stop) to quit : ");
                String moviesToAdd = "";
                while (!moviesToAdd.equals("stop"))
                {
                    moviesToAdd = s.nextLine();
                    if(moviesToAdd.equals("stop"))
                    {
                        break;
                    }
                    moviesInTheatre.add(moviesToAdd);
                }
                BookMyShow.getTheatres().add(new Theatres(theatreNameToAdd,locationOfTheatre,moviesInTheatre));
                System.out.println("Info about theatre");
                for(Theatres theatres:BookMyShow.getTheatres())
                {
                    System.out.println("Location" + theatres.getLocation());
                    System.out.println("Name "+ theatres.getName());
                    System.out.println("The movies available are :");
                    for(String movies:theatres.getMovies())
                    {
                        System.out.println(movies);
                    }
                }
        }
    }
}
