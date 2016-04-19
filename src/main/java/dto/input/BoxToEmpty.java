package dto.input;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rex on 19.4.2016.
 */
public class BoxToEmpty {
    @SerializedName("box-number")
    private int boxNumber;

    public int getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }
}
