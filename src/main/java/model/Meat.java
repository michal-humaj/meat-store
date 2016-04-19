package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rex on 19.4.2016.
 */
public class Meat {

    private MeatType meatType;

    private int count;

    @SerializedName("date-of-slaughter")
    private String date;

    @SerializedName("is-frozen")
    private boolean frozen;

    public MeatType getMeatType() {
        return meatType;
    }

    public void setMeatType(MeatType meatType) {
        this.meatType = meatType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
