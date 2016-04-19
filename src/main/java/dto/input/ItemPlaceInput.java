package dto.input;

import com.google.gson.annotations.SerializedName;
import model.BoxType;
import model.MeatType;

/**
 * Created by Rex on 19.4.2016.
 */
public class ItemPlaceInput {
    @SerializedName("type")
    private MeatType meatType;

    @SerializedName("cooling-type")
    private BoxType coolingType;

    public MeatType getMeatType() {
        return meatType;
    }

    public void setMeatType(MeatType meatType) {
        this.meatType = meatType;
    }

    public BoxType getCoolingType() {
        return coolingType;
    }

    public void setCoolingType(BoxType coolingType) {
        this.coolingType = coolingType;
    }
}
