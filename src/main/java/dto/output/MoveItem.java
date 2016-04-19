package dto.output;

import com.google.gson.annotations.SerializedName;
import model.MeatType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rex on 19.4.2016.
 */
public class MoveItem {

    @SerializedName("type")
    private MeatType meatType;
    private int count;
    @SerializedName("date-of-expiration")
    private String dateOfExpiration;
    @SerializedName("current-item-place")
    private MoveItemPlace currentItemPlace;
    @SerializedName("new-item-place")
    private MoveItemPlace newItemPlace;

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

    public String getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(String dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public MoveItemPlace getCurrentItemPlace() {
        return currentItemPlace;
    }

    public void setCurrentItemPlace(MoveItemPlace currentItemPlace) {
        this.currentItemPlace = currentItemPlace;
    }

    public MoveItemPlace getNewItemPlace() {
        return newItemPlace;
    }

    public void setNewItemPlace(MoveItemPlace newItemPlace) {
        this.newItemPlace = newItemPlace;
    }

    public static class MoveItemList {
        @SerializedName("move-item")
        private List<MoveItem> moveItemList = new ArrayList<>();

        public List<MoveItem> getMoveItemList() {
            return moveItemList;
        }

        public void setMoveItemList(List<MoveItem> moveItemList) {
            this.moveItemList = moveItemList;
        }
    }

    public static class MoveItemPlace {
        @SerializedName("box-number")
        private int boxNumber;
        @SerializedName("shelf-number")
        private String shelfNumber;

        public int getBoxNumber() {
            return boxNumber;
        }

        public void setBoxNumber(int boxNumber) {
            this.boxNumber = boxNumber;
        }

        public String getShelfNumber() {
            return shelfNumber;
        }

        public void setShelfNumber(String shelfNumber) {
            this.shelfNumber = shelfNumber;
        }
    }
}
