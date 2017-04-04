package mobileapps.bramberifarms;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static mobileapps.bramberifarms.DBSchema.*;

/**
 * Created by Christian on 4/3/2017.
 */

public class BerryTools {
    private static Context c = MainActivity.getmContext();
    private static SQLiteDatabase db = new DBHelper(c).getWritableDatabase();;

    public static void inputBerry(Berry berry) {
        ContentValues values = new ContentValues();
        values.put(BerryTable.Cols.NAME, berry.getName());
        values.put(BerryTable.Cols.SEASON, berry.getSeason());
        values.put(BerryTable.Cols.BID, berry.getBid());
        db.insert("berry", null, values);
    }

    public static void inputStats(YieldStat stat) {
        ContentValues values = new ContentValues();
        values.put(StatsTable.Cols.BID, stat.getBid());
        values.put(StatsTable.Cols.YEAR, stat.getYear());
        values.put(StatsTable.Cols.NUMPLANTS, stat.getNumPlants());
        values.put(StatsTable.Cols.YIELDPP, stat.getYieldPP());
        values.put(StatsTable.Cols.MARKETYIELD, stat.getMarketYield());
        values.put(StatsTable.Cols.PRICEPP, stat.getPricePP());
        db.insert("stats", null, values);
    }
}
