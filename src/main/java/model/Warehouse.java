package model;

import java.util.List;

/**
 * Created by mihu on 19.4.16.
 */
public class Warehouse {

    private List<CoolingBox> coolingBoxes;
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
