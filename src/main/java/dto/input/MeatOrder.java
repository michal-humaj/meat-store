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

    public void setMeatType(MeatType meatType) {
        this.meatType = meatType;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCoolingType(String coolingType) {
        this.coolingType = coolingType;
    }

    public void setDaysDurabilit(int daysDurabilit) {
        this.daysDurabilit = daysDurabilit;
    }

    public static class MeatOrderList {
        @SerializedName("meat-order")
        private List<MeatOrder> meatOrderList = new ArrayList<>();

        public List<MeatOrder> getMeatOrderList() {
            return meatOrderList;
        }

        public void setMeatOrderList(List<MeatOrder> meatOrderList) {
            this.meatOrderList = meatOrderList;
        }
    }
}
