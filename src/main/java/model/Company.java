package model;

import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;

import java.util.HashMap;

/**
 * Created by Jakub on 19.04.2016.
 */
public class Company {

    private String name;

    @SerializedName("founding-date")
    private DateTime foundingDate;

    private Employee director;

    private HashMap employees;

    private Warehouse warehouse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(DateTime foundingDate) {
        this.foundingDate = foundingDate;
    }

    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public HashMap getEmployees() {
        return employees;
    }

    public void setEmployees(HashMap employees) {
        this.employees = employees;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
