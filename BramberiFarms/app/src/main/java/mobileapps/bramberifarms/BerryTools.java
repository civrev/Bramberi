package mobileapps.bramberifarms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static mobileapps.bramberifarms.DBSchema.*;

/**
 * Created by Christian on 4/3/2017.
 */

public class BerryTools {

    private static Context c = MainActivity.getmContext();
    private static SQLiteDatabase db = new DBHelper(c).getWritableDatabase();;

    public static void inputBerry(Berry berryX) {
        final Berry berry = berryX;
        if(!berry.equals(null)) {
            new Thread() {
                public void run() {
                    try {
                        ContentValues values = new ContentValues();
                        values.put(BerryTable.Cols.NAME, berry.getName());
                        values.put(BerryTable.Cols.SEASON, berry.getSeason());
                        values.put(BerryTable.Cols.BID, berry.getBid());
                        long id = db.insert(BerryTable.NAME, null, values);
                        Log.i("BERRY TOOLS", "inputBerry _id: " + id);
                    } catch (Exception e) {

                    }
                }
            }.start();
        }
    }

    public static void inputStats(YieldStat statX) {
        final YieldStat stat = statX;
        if(!stat.equals(null)) {
            new Thread() {
                public void run() {
                    try {
                        ContentValues values = new ContentValues();
                        values.put(StatsTable.Cols.BID, stat.getBid());
                        values.put(StatsTable.Cols.YEAR, stat.getYear());
                        values.put(StatsTable.Cols.NUMPLANTS, stat.getNumPlants());
                        values.put(StatsTable.Cols.YIELDPP, stat.getYieldPP());
                        values.put(StatsTable.Cols.MARKETYIELD, stat.getMarketYield());
                        values.put(StatsTable.Cols.PRICEPP, stat.getPricePP());
                        long id = db.insert(StatsTable.NAME, null, values);
                        Log.i("BERRY TOOLS", "inputStats _id: " + id);
                    } catch (Exception e) {
                        //handle failure here
                    }
                }
            }.start();
        }
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

    public static YieldStat pullStats(String bName){
        Berry berry = pullBerry(bName);
        String bid = null;
        if (bName.length()!=0) {
            bid = berry.getBid();

            String table = "stats";
            String[] columns = null;
            String selection = "bid =?";
            String[] selectionArgs = {bid};
            String groupBy = null;
            String having = null;
            String orderBy = null;
            String limit = "1";
            Cursor c = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            int cSize = c.getCount();
            Log.i("BERRY TOOLS", "pullStats cursor size: " + Integer.toString(cSize));
            if (cSize > 0) {
                c.moveToNext();
                String brryId = c.getString(c.getColumnIndex("bid"));
                int year = c.getInt(c.getColumnIndex("year"));
                int numplants = c.getInt(c.getColumnIndex("numplants"));
                double yieldpp = c.getDouble(c.getColumnIndex("yieldpp"));
                double marketyield = c.getDouble(c.getColumnIndex("marketyield"));
                double pricepp = c.getDouble(c.getColumnIndex("pricepp"));
                YieldStat ystat = new YieldStat(brryId, year, numplants, yieldpp, marketyield, pricepp);
                return ystat;
            }
        }
        return null;
    }

    public static YieldStat pullStatsByYear(String bName, String bYear){
        Log.i("BERRY TOOLS", "pullStatsByYear bName: " + bName);
        Berry berry = pullBerry(bName);
        String bid = null;
        YieldStat ystat = null;
        if (bName.length()!=0) {
            bid = berry.getBid();
            Cursor c = db.rawQuery("SELECT * FROM stats WHERE bid =? AND year=" + bYear, new String[] {bid});
            int cSize = c.getCount();
            Log.i("BERRY TOOLS", "pullStatsByYear cursor size: " + Integer.toString(cSize));
            if (cSize > 0) {
                c.moveToNext();
                String brryId = c.getString(c.getColumnIndex("bid"));
                int year = c.getInt(c.getColumnIndex("year"));
                Log.i("BERRY TOOLS", "pullStatsByYear year: " + Integer.toString(year));
                int numplants = c.getInt(c.getColumnIndex("numplants"));
                double yieldpp = c.getDouble(c.getColumnIndex("yieldpp"));
                double marketyield = c.getDouble(c.getColumnIndex("marketyield"));
                double pricepp = c.getDouble(c.getColumnIndex("pricepp"));
                ystat = new YieldStat(brryId, year, numplants, yieldpp, marketyield, pricepp);
                c.close();
                return ystat;
            }
            return null;
        }
        return null;
    }

    public static ArrayList<Berry> allBerry(){
        ArrayList<Berry> listBerry = new ArrayList<Berry>();
        String table = "berry";
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
        Cursor c = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        int cSize = c.getCount();
        Log.i("BERRY TOOLS", "allBerry cursor size: " + Integer.toString(cSize));
        if(cSize>0) {
            for(int i = 0; i<cSize; i++) {
                c.moveToNext();
                String newName = c.getString(c.getColumnIndex("name"));
                String season = c.getString(c.getColumnIndex("season"));
                String bid = c.getString(c.getColumnIndex("bid"));
                Berry berry = new Berry(newName, season, bid);
                listBerry.add(berry);
            }
            return listBerry;
        }
        return null;
    }

    public static ArrayList<String> allBerryNames(){
        ArrayList<String> listName = new ArrayList<String>();
        String table = "berry";
        String[] columns = {"name"};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
        Cursor c = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        int cSize = c.getCount();
        Log.i("BERRY TOOLS", "allBerry cursor size: " + Integer.toString(cSize));
        if(cSize>0) {
            for(int i = 0; i<cSize; i++) {
                c.moveToNext();
                String newName = c.getString(c.getColumnIndex("name"));
                listName.add(newName);
            }
            return listName;
        }
        return null;
    }

    public static ArrayList<YieldStat> allStats(){
        ArrayList<YieldStat> listStat = new ArrayList<YieldStat>();
        String table = "stats";
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
        Cursor c = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        int cSize = c.getCount();
        Log.i("BERRY TOOLS", "allStats cursor size: " + Integer.toString(cSize));
        if(cSize>0) {
            for(int i = 0; i<cSize; i++) {
                c.moveToNext();
                String brryId = c.getString(c.getColumnIndex("bid"));
                int year = c.getInt(c.getColumnIndex("year"));
                int numplants = c.getInt(c.getColumnIndex("numplants"));
                double yieldpp = c.getDouble(c.getColumnIndex("yieldpp"));
                double marketyield = c.getDouble(c.getColumnIndex("marketyield"));
                double pricepp = c.getDouble(c.getColumnIndex("pricepp"));
                YieldStat ystat = new YieldStat(brryId, year, numplants, yieldpp, marketyield, pricepp);
                listStat.add(ystat);
            }
            return listStat;
        }
        return null;
    }
}
