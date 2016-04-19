package dto.output;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlado on 19.04.2016.
 */
public class ItemPlace {

    @SerializedName("box-number")
    private int boxNumber;
    @SerializedName("shelf-number")
    private String shelfNumber;
    private int count;
    @SerializedName("date-of-expiration")
    private String dateOfExpiration;

    public int getBoxNumber() {
        return boxNumber;
    }

    public String getShelfNumber() {
        return shelfNumber;
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

    public void setCount(int count) {
        this.count = count;
    }

    public void setDateOfExpiration(String dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public static class ItemPlaceList {
        @SerializedName("item-place")
        private List<ItemPlace> itemPlaceList = new ArrayList<>();

        @SerializedName("error-message")
        private String message;

        public List<ItemPlace> getItemPlaceList() {
            return itemPlaceList;
        }

        public void setItemPlaceList(List<ItemPlace> itemPlaceList) {
            this.itemPlaceList = itemPlaceList;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
