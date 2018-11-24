package hsport.com.example.king.yahooweatherv2;

/**
 * Created by King on 23/11/2018.
 */

public class Forecast {

    private String date, day, description;
    private int high, low;

    public Forecast(String date, String day, String description, int high, int low) {
        this.date = date;
        this.day = day;
        this.description = description;
        this.high = high;
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getDescription() {
        return description;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }
    /* public Forecast(String date, String day, String description, int high, int low) {
        this.date = date;
        this.day = day;
        this.description = description;
        this.high = high;
        this.low = low;


    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getDescription() {
        return description;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

}*/

        }
