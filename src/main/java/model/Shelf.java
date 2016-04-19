package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihu on 19.4.16.
 */
public class Shelf {

    private String number;
    private int capacity;
    private List<Meat> meat = new ArrayList<Meat>();

    public int getFreeCapacity(){
        int freeCapacity = capacity;
        for (Meat meat : this.meat){
            capacity -= meat.getCount();
        }
        return freeCapacity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Meat> getMeat() {
        return meat;
    }

    public void setMeat(List<Meat> meat) {
        this.meat = meat;
    }
}
