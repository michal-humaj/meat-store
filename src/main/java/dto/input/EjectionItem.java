package dto.input;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rex on 19.4.2016.
 */
public class EjectionItem {
    @SerializedName("box-number")
    private int boxNumber;
    @SerializedName("shelf-number")
    private String shelfNumber;
    private int count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static class EjectionItemList {
        @SerializedName("item-place")
        private List<EjectionItem> ejectionItems = new ArrayList<>();

        public List<EjectionItem> getEjectionItems() {
            return ejectionItems;
        }

        public void setEjectionItems(List<EjectionItem> ejectionItems) {
            this.ejectionItems = ejectionItems;
        }
    }
}
