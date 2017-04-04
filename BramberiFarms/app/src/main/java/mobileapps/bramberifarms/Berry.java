package mobileapps.bramberifarms;

/**
 * Created by Christian on 4/3/2017.
 */

public class Berry {
    private String name; // name of berry
    private String season; // season of the berry
    private String bid; //berry id (example RF1)

    public Berry(String bid, String name, String season) {
        this.bid = bid;
        this.name = name;
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String toString(){
        return ("ID: " + bid + " Name: " + name + " Season: " + season);
    }
}
