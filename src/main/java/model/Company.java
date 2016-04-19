package model;

import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jakub on 19.04.2016.
 */
public class Company {

    private String name;

    @SerializedName("founding-date")
    private String foundingDate;

    private DateTime foundingDateFormatted;

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


    public DateTime getFoundingDateFormatted() {
        return foundingDateFormatted;
    }

    public void setFoundingDateFormatted(DateTime foundingDateFormatted) {
        this.foundingDateFormatted = foundingDateFormatted;
    }

    public void setEmployees( HashMap<String, List<Employee>>  employees) {
        this.employees = employees;
    }

    public void setConvertedDate(){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        this.setFoundingDateFormatted(formatter.parseDateTime(this.getFoundingDate()));
    }

}
