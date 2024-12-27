package BookMyShow;

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




    }
}