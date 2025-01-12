package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Scanner;

public class Shows {

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate dateOfShow;
    private Screens screen;
    private int price;
    //private String movieName;

    public Shows(LocalDate dateOfShow,LocalTime startTime,LocalTime endTime, Screens screen,int price)
    {
       // this.movieName = movieName;
        this.dateOfShow = dateOfShow;
        this.startTime = startTime;
        this.endTime = endTime;
        this.screen = screen;
        this.price = price;
    }

    public LocalDate getDateOfShow() {
        return dateOfShow;
    }
    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Screens getScreens() {
        return screen;
    }

    public int getPrice() {
        return price;
    }

    //    public String getMovieName() {
//        return movieName;
//    }


    @Override
    public String toString() {
        return " * " + startTime.toString() ;
    }

    /* Overriding equals method from object class
  * to do check show already exist in same time,date
  *
  * This method is called everytime when the add() or contains() is called with the Hashset<Shows>*/
    @Override
//    If true then doesnot creates an object if false then creates and object (when called with add)
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false; // if the passed object is null or if the passed object is not type of class Shows
        Shows shows = (Shows) o; // typecast Object object to Shows object
        return Objects.equals(this.startTime, shows.startTime) && Objects.equals(this.endTime, shows.endTime) && Objects.equals(dateOfShow,shows.getDateOfShow()); // check and return
    }

    /* Overriding hashcode method from object class
     * to create a unique hash with starttime,endtime and date
     *
     * This method is called everytime when the add() or contains() is called with the Hashset<Shows>*/
    @Override
    public int hashCode() {
        return Objects.hash(startTime,endTime,dateOfShow); // hashing the key and check into kay-value table
    }

}
