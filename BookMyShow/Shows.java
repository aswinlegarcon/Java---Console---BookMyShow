package BookMyShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Shows {

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate dateOfShow;
//    private String movieName;

    public Shows(LocalDate dateOfShow,LocalTime startTime,LocalTime endTime)
    {
//        this.movieName = movieName;
        this.dateOfShow = dateOfShow;
        this.startTime = startTime;
        this.endTime = endTime;
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

//    public String getMovieName() {
//        return movieName;
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Shows shows = (Shows) o;
        return Objects.equals(this.startTime, shows.startTime) && Objects.equals(this.endTime, shows.endTime) && Objects.equals(dateOfShow,shows.getDateOfShow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime,endTime,dateOfShow);
    }

}
