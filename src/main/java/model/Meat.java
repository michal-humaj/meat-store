package model;

import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;
import util.DateConverter;

import java.io.Serializable;

/**
 * Created by Rex on 19.4.2016.
 */
public class Meat implements Serializable {

    @SerializedName("type")
    private MeatType meatType;

    private int count;

    @SerializedName("date-of-slaughter")
    private String date;

    @SerializedName("is-frozen")
    private boolean frozen;

    private Shelf shelf;

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

    public String getExpiryDate() {
        DateTime slaughterDate = DateConverter.toDateTimeDots(getDate());
        return DateConverter.toStringDots(slaughterDate.plusDays(getMeatType().getFreshDays()));
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

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public boolean isExpired() {
        return DateConverter.toDateTimeDots(getExpiryDate()).isBeforeNow();
    }

    public boolean isInDurability(int days) {
        return false;
    }
}
