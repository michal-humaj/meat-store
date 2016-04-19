package util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Rex on 19.4.2016.
 */
public class DateConverter {
    public static String toStringDots(DateTime date){
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");
        return fmt.print(date);
    }

    public static DateTime toDateTimeDots(String date){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        return formatter.parseDateTime(date);
    }

    public static String toStringSlashes(DateTime date){
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        return fmt.print(date);
    }

    public static  DateTime toDateTimeSlashes(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        return formatter.parseDateTime(date);
    }
}
