package mobileapps.bramberifarms;

/**
 * Created by Christian on 4/3/2017.
 */

public class YieldStat {
    private String bid; // berry id foriegn key
    private int year; // year these stats are for
    private int numPlants; // number of plants
    private double yieldPP; // yield per plant (in lbs)
    private double marketYield; // yield for market (in lbs)
    private double pricePP; // price per pound (in $)

    public YieldStat(String bid, int year, int numPlants, double yieldPP, double marketYield, double pricePP) {
        this.bid = bid;
        this.year = year;
        this.numPlants = numPlants;
        this.yieldPP = yieldPP;
        this.marketYield = marketYield;
        this.pricePP = pricePP;
    }
    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumPlants() {
        return numPlants;
    }

    public void setNumPlants(int numPlants) {
        this.numPlants = numPlants;
    }

    public double getYieldPP() {
        return yieldPP;
    }

    public void setYieldPP(double yieldPP) {
        this.yieldPP = yieldPP;
    }

    public double getMarketYield() {
        return marketYield;
    }

    public void setMarketYield(double marketYield) {
        this.marketYield = marketYield;
    }

    public double getPricePP() {
        return pricePP;
    }

    public void setPricePP(double pricePP) {
        this.pricePP = pricePP;
    }

    public String toString(){
        return ("BID: " + bid+" year: "+year+" plants: "+numPlants+" yieldpp: "+yieldPP+
                " market yield: "+marketYield+" priceplb: "+pricePP);
    }
}
