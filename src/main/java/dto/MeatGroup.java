package dto;

import model.BoxType;
import model.MeatType;

/**
 * Created by Rex on 19.4.2016.
 */
public class MeatGroup {
    private MeatType meatType;
    private String expiryDate;
    private BoxType boxType;

    public MeatType getMeatType() {
        return meatType;
    }

    public void setMeatType(MeatType meatType) {
        this.meatType = meatType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeatGroup meatGroup = (MeatGroup) o;

        if (meatType != meatGroup.meatType) return false;
        if (expiryDate != null ? !expiryDate.equals(meatGroup.expiryDate) : meatGroup.expiryDate != null) return false;
        return boxType == meatGroup.boxType;

    }

    @Override
    public int hashCode() {
        int result = meatType != null ? meatType.hashCode() : 0;
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (boxType != null ? boxType.hashCode() : 0);
        return result;
    }
}
