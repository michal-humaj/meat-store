package dto.output;

import com.google.gson.annotations.SerializedName;
import model.MeatType;
import org.joda.time.DateTime;


/**
 * Created by vlado on 19.04.2016.
 */
public class MeatOrderPlace {

    @SerializedName("box-number")
    private int boxNumber;
    @SerializedName("shelf-number")
    private String shelfNumber;
    private Enum<MeatType> type;
    private int count;
    @SerializedName("date-of-expiration")
    private DateTime dateOfExpiration;

    public int getBoxNumber() {
        return boxNumber;
    }

    public String getShelfNumber() {
        return shelfNumber;
    }

    public Enum<MeatType> getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public DateTime getDateOfExpiration() {
        return dateOfExpiration;
    }
}
