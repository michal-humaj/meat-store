package impl;

import dto.MeatGroup;
import model.CoolingBox;
import model.Meat;
import model.Shelf;
import model.Warehouse;

import java.util.HashMap;

/**
 * Created by Rex on 19.4.2016.
 */
public class WarehouseTools {
    public static HashMap<MeatGroup, Integer> getMeatCountByType(Warehouse warehouse) {
        HashMap<MeatGroup, Integer> meatCounts = new HashMap<>();

        for (CoolingBox coolingBox : warehouse.getCoolingBoxes()) {
            for (Shelf shelf : coolingBox.getShelves()) {
                for (Meat meat : shelf.getMeat()) {
                    MeatGroup meatGroup = new MeatGroup();
                    meatGroup.setBoxType(coolingBox.getType());
                    meatGroup.setExpiryDate(meat.getExpiryDate());
                    meatGroup.setMeatType(meat.getMeatType());

                    int totalCount = 0;
                    if(meatCounts.containsKey(meatGroup)) {
                        totalCount = meatCounts.get(meatGroup);
                    }

                    totalCount += meat.getCount();
                    meatCounts.put(meatGroup, totalCount);
                }
            }
        }

        return meatCounts;
    }
}
