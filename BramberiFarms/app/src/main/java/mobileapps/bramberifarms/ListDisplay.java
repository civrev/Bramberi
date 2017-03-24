package mobileapps.bramberifarms;

import android.content.Intent;

/**
 * Created by Christian on 3/1/2017.
 */

public class ListDisplay {

    private String name;
    private int rid;
    private Intent intent;

    public ListDisplay (String pname, int prid, Intent pintent){
        this.name = pname;
        this.rid = prid;
        this.intent = pintent;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String pname) {
        this.name = pname;
    }
    public int getRID() {
        return this.rid;
    }
    public void setRID(int prid) {
        this.rid = prid;
    }
    public Intent getIntentPlus() {
        return this.intent;
    }
    public void setIntentPlus(Intent pintent) {
        this.intent = pintent;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
