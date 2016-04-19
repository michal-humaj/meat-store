package dto.input;

import com.google.gson.annotations.SerializedName;
import model.MeatType;

/**
 * Created by vlado on 19.04.2016.
 */
public class MeatOrder {
    private Enum<MeatType> type;
    private int count;
    @SerializedName("cooling-type")
    private String coolingType;
    @SerializedName("days-durabilit")
    private int daysDurabilit;

    public Enum<MeatType> getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public String getCoolingType() {
        return coolingType;
    }

    public int getDaysDurabilit() {
        return daysDurabilit;
    }
}
