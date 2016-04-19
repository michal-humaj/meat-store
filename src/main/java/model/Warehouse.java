package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mihu on 19.4.16.
 */
public class Warehouse {

    @SerializedName("cooling-boxes")
    private List<CoolingBox> coolingBoxes;
    @SerializedName("serial-number")
    private String serialNumber;

    public List<CoolingBox> getCoolingBoxes() {
        return coolingBoxes;
    }

    public void setCoolingBoxes(List<CoolingBox> coolingBoxes) {
        this.coolingBoxes = coolingBoxes;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
