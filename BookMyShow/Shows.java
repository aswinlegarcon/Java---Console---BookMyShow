package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;

public class Shows {

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate dateOfShow;
    private String movieName;

    public Shows(String movieName,LocalDate dateOfShow,LocalTime startTime,LocalTime endTime)
    {
        this.movieName = movieName;
        this.dateOfShow = dateOfShow;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getDateOfShow() {
        return dateOfShow;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public String getMovieName() {
        return movieName;
    }
}
