package model;

import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;

import java.util.HashMap;

/**
 * Created by Jakub on 19.04.2016.
 */
public class Company {

    private Warehouse warehouse;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    private String name;

    @SerializedName("founding-date")
    private DateTime foundingDate;

    private E

    private HashMap employees;
}
