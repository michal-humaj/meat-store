package util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by vlado on 19.04.2016.
 */
public class ConvertDate {

    public String toStringDots(DateTime date){
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
        return fmt.print(date);
    }

    public DateTime toDateTimeDots(String date){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        return formatter.parseDateTime(date);
    }

    public String toStringSlashes(DateTime date){
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        return fmt.print(date);
    }

    public DateTime toDateTimeSlashes(String date){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        return formatter.parseDateTime(date);
    }

}
