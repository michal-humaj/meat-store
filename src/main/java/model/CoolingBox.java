package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihu on 19.4.16.
 */
public class CoolingBox {

    private int number;
    private List<Shelf> shelfs = new ArrayList<Shelf>();
    private BoxType type;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Shelf> getShelves() {
        return shelfs;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelfs = shelves;
    }

    public BoxType getType() {
        return type;
    }

    public void setType(BoxType type) {
        this.type = type;
    }
}
