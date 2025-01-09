package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;

public class Utilities {

//    Method to generate seat pattern for screens
    public static HashMap<Character, ArrayList<String>> generateGrids(long noOfSeats, String grid )
    {
        var gridAsString = grid.split("\\*"); // input will be 2*6*2 so we have to spilt * and store as string array{"2","6"."8"}
        int sumOfGrids = 0; // to store the sum of input eg: 2*6*2 --- 2+6+2
        for(String grids:gridAsString) // loop to add and store it as integers
        {
            sumOfGrids += Integer.parseInt(grids);
        }
        if(noOfSeats%sumOfGrids == 0) // if given pattern matches seat numbers eg: 100 % 10 (2+6+2) == 0
        {
            HashMap<Character,ArrayList<String>> seatsAndGrids = new HashMap<>(); // create a new hashmap to store and return it finally
            char containsAlphabet = 'A'; // char A to move seat number from A - Z

            while (noOfSeats>0) // condition to check (noofSeats will be reduced every iteration)
            {
                ArrayList<String> seats = new ArrayList<>(); // string arraylist to store seats pattern
                for(int col = 0;col<gridAsString.length;col++) // col to loop no of input give eg: if 2*8 then 2 times if 2*6*2 then 3 times
                {
                    int noOfelement = Integer.parseInt(gridAsString[col]); // total no of element in a single column
                    for(int element = 0; element<noOfelement; element++) // loop that element
                    {
                        seats.add("__");// add the seats as string
                    }
                    if(col<gridAsString.length-1) // to give space after each column but not for the last column
                    {
                        seats.add(" <SPACE> "); // add the space to arraylist
                    }
                }
                seatsAndGrids.put(containsAlphabet,seats); // put the alphabet and seats arraylist into hashmap
                containsAlphabet++; // increase the alphabet everytime to get unique set of seats
                noOfSeats = noOfSeats - sumOfGrids; // decrease the seats that has been added already everytime
            }
            return seatsAndGrids; // return hashmap
        }
//      if the numberof seats and given grid doesnot matches
        System.out.println("No of Seats does not match");
        return null;
    }
}