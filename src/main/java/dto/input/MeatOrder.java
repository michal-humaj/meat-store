package dto.input;

import com.google.gson.annotations.SerializedName;
import model.MeatType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlado on 19.04.2016.
 */
public class MeatOrder {
    @SerializedName("type")
    private MeatType meatType;
    private int count;
    @SerializedName("cooling-type")
    private String coolingType;
    @SerializedName("days-durabilit")
    private int daysDurabilit;


    public MeatType getMeatType() {
        return meatType;
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

    public static class MeatOrderList {
        @SerializedName("meat-order")
        private List<MeatOrder> meatOrderList = new ArrayList<>();

        public List<MeatOrder> getMeatOrderList() {
            return meatOrderList;
        }

        public void setMeatOrderList(List<MeatOrder> meatOrderPlaceList) {
            this.meatOrderList = meatOrderPlaceList;
        }
    }
}
