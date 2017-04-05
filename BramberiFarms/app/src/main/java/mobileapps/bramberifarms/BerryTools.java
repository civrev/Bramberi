package mobileapps.bramberifarms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        long id = db.insert(BerryTable.NAME, null, values);
        Log.i("BERRY TOOLS", "inputBerry _id: " + id);
    }

    public static void inputStats(YieldStat stat) {
        ContentValues values = new ContentValues();
        values.put(StatsTable.Cols.BID, stat.getBid());
        values.put(StatsTable.Cols.YEAR, stat.getYear());
        values.put(StatsTable.Cols.NUMPLANTS, stat.getNumPlants());
        values.put(StatsTable.Cols.YIELDPP, stat.getYieldPP());
        values.put(StatsTable.Cols.MARKETYIELD, stat.getMarketYield());
        values.put(StatsTable.Cols.PRICEPP, stat.getPricePP());
        long id = db.insert(StatsTable.NAME, null, values);
        Log.i("BERRY TOOLS", "inputStats _id: " + id);
    }

    public static Berry pullBerry(String bName){
        //pulls berry data from db using its name

        //set up for cursor
        String table = "berry"; //BerryTable.NAME;
        String[] columns = null;
        String selection = "name =?";//BerryTable.Cols.NAME + " =?";
        String[] selectionArgs = {bName};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = "1";
        Cursor c = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        int cSize = c.getCount();
        Log.i("BERRY TOOLS", "pullBerry cursor size: " + Integer.toString(cSize));
        Log.i("BERRY TOOLS", "pullBerry column name: " + c.getColumnName(1));
        if(cSize>0) {
            c.moveToNext();
            String newName = c.getString(c.getColumnIndex("name"));
            String season = c.getString(c.getColumnIndex("season"));
            String bid = c.getString(c.getColumnIndex("bid"));
            Berry berry = new Berry(newName, season, bid);
            return berry;
        }
        return null;
    }

    public static void pullStats(String bName){
        Berry berry = pullBerry(bName);
        String bid = berry.getBid();


        //YieldStat stats = new YieldStat();
        //return stats;
    }
}
