package dto.output;

import com.google.gson.annotations.SerializedName;
import model.MeatType;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by vlado on 19.04.2016.
 */
public class MeatOrderPlace {

    @SerializedName("box-number")
    private int boxNumber;
    @SerializedName("shelf-number")
    private String shelfNumber;
    private Enum<MeatType> type;
    private int count;
    @SerializedName("date-of-expiration")
    private String dateOfExpiration;

    public int getBoxNumber() {
        return boxNumber;
    }

    public String getShelfNumber() {
        return shelfNumber;
    }

    public Enum<MeatType> getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public String getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }

    public void setShelfNumber(String shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public void setType(Enum<MeatType> type) {
        this.type = type;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDateOfExpiration(String dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public static class MeatOrderPlaceList {
        @SerializedName("meat-order-place")
        private List<MeatOrderPlace> meatOrderPlaceList = new ArrayList<>();

        public List<MeatOrderPlace> getMeatOrderPlaceList() {
            return meatOrderPlaceList;
        }

        public void setMeatOrderPlaceList(List<MeatOrderPlace> meatOrderPlaceList) {
            this.meatOrderPlaceList = meatOrderPlaceList;
        }
    }
}
