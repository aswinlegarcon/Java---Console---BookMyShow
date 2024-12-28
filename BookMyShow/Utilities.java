package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;

public class Utilities {

    public static HashMap<Character, ArrayList<String>> generateGrids(long noOfSeats, String grid )
    {
        var gridAsString = grid.split("\\*");
        int sumOfGrids = 0;
        for(String grids:gridAsString)
        {
            sumOfGrids += Integer.parseInt(grids);
        }
        if(noOfSeats%sumOfGrids == 0)
        {
            HashMap<Character,ArrayList<String>> seatsAndGrids = new HashMap<>();
            char containsAlphabet = 'A';

            while (noOfSeats>0)
            {
                ArrayList<String> seats = new ArrayList<>();
                for(int col = 0;col<gridAsString.length;col++)
                {
                    int noOfelement = Integer.parseInt(gridAsString[col]);
                    for(int element = 0; element<noOfelement; element++)
                    {
                        seats.add("__");
                    }
                    if(col<gridAsString.length-1)
                    {
                        seats.add(" <SPACE> ");
                    }
                }
                seatsAndGrids.put(containsAlphabet,seats);
                containsAlphabet++;
                noOfSeats = noOfSeats - sumOfGrids;
            }
            return seatsAndGrids;
        }

        System.out.println("No of Seats does not match");
        return null;
    }
}