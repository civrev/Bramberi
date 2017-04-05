package mobileapps.bramberifarms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static mobileapps.bramberifarms.DBSchema.*;

/**
 * Created by Christian on 4/3/2017.
 */

//so I am creating this helper to move things along
public class DBHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "bramberi.db";

    public DBHelper (Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    //so this is how to create the db
    @Override
    public void onCreate(SQLiteDatabase db){
        //so I am using this to run a raw SQL statement as a string
        db.execSQL("create table " + BerryTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                BerryTable.Cols.NAME + ", " + BerryTable.Cols.SEASON + "," +
                BerryTable.Cols.BID + ")");

        //I am seperating these into 2 SQL executions to make it easier on the eyes
        db.execSQL("create table " + StatsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                StatsTable.Cols.BID + ", " + StatsTable.Cols.YEAR +
                ", " + StatsTable.Cols.NUMPLANTS + ", " +
                StatsTable.Cols.YIELDPP + ", " + StatsTable.Cols.MARKETYIELD +
                ", " + StatsTable.Cols.PRICEPP + ")");
    }

    //and this one is how to upgrade from an old version to new version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


}
