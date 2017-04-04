package mobileapps.bramberifarms;

/**
 * Created by Christian on 4/3/2017.
 */

public class DBSchema {
    public static final class BerryTable {
        //name this table
        public static final String NAME = "berry";

        //now create another class inside here to declare the columns
        public static final class Cols{
            //follow this format for the names of the columns
            public static final String NAME = "name";
            public static final String SEASON = "season";
            public static final String BID = "bid";
        }
    }
    public static final class StatsTable {
        //name this table
        public static final String NAME = "stats";

        //now create another class inside here to declare the columns
        public static final class Cols{
            //follow this format for the names of the columns
            public static final String BID = "bid";
            public static final String YEAR = "year";
            public static final String NUMPLANTS = "numplants";
            public static final String YIELDPP = "yieldpp";
            public static final String MARKETYIELD = "marketyield";
            public static final String PRICEPP = "pricepp";
        }
    }

}
