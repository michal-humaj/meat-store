package dto.input;

import com.google.gson.annotations.SerializedName;
import model.Meat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihu on 19.4.16.
 */
public class MeatList {

    @SerializedName("meat-item")
    private List<Meat> meatList = new ArrayList<>();

    public List<Meat> getMeatList() {
        return meatList;
    }

    public void setMeatList(List<Meat> meatList) {
        this.meatList = meatList;
    }
}
