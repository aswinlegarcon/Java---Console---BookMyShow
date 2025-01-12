package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

public class Ticket {

   private String theatreName;
   private String movieName;
   private String screenName;
   private LocalDate dateOfShow;
   private LocalTime timeOfShow;
   private int noOfTickets;
   private HashSet<String> seatNumbers;
   private int price;

   public Ticket(String theatreName,String movieName,String screenName,LocalDate dateOfShow,LocalTime timeOfShow, int noOfTickets, HashSet<String> seatNumbers,int price)
   {
       this.theatreName = theatreName;
       this.movieName = movieName;
       this.screenName = screenName;
       this.dateOfShow = dateOfShow;
       this.timeOfShow = timeOfShow;
       this.noOfTickets = noOfTickets;
       this.seatNumbers = seatNumbers;
       this.price = price;
   }

    public LocalDate getDateOfShow() {
        return dateOfShow;
    }

    public HashSet<String> getSeatNumbers() {
        return seatNumbers;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public String getScreenName() {
        return screenName;
    }

    public int getPrice() {
        return price;
    }

    public LocalTime getTimeOfShow() {
        return timeOfShow;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }
}
