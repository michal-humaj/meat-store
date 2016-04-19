package model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jakub on 19.04.2016.
 */
public class Company {

    private String name;

    @SerializedName("founding-date")
    private String foundingDate;

    private Employee director;

    private HashMap<String, List<Employee>> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(String foundingDate) {
        this.foundingDate = foundingDate;
    }

    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public  HashMap<String, List<Employee>>  getEmployees() {
        return employees;
    }

    public void setEmployees( HashMap<String, List<Employee>>  employees) {
        this.employees = employees;
    }



}
