package impl;

import dto.MeatGroup;
import dto.output.ItemPlace;
import model.*;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static ItemPlace.ItemPlaceList putItemInStock(Meat addingMeat, Integer excludedBoxNumber) {
        Warehouse warehouse = CompanyProvider.getInstance().getAppData().getWarehouse();
        List<ItemPlace> itemPlaces = new ArrayList<>();
        boolean addingFinished = false;

        for (CoolingBox cb : warehouse.getCoolingBoxes()) {
            if(excludedBoxNumber != null && excludedBoxNumber == cb.getNumber()) {
                continue;
            }
            BoxType meatCooling = addingMeat.isFrozen() ? BoxType.FREEZING : BoxType.COOLING;

            if (!cb.getType().equals(meatCooling)) continue;
            for (Shelf shelf : cb.getShelves()) {

                if (shelf.getFreeCapacity() == 0){
                    continue;
                } else if (addingMeat.getCount() <= shelf.getFreeCapacity()){
                    addingMeat.setShelf(shelf);
                    shelf.getMeat().add(addingMeat);
                    ItemPlace itemPlace = new ItemPlace();
                    itemPlace.setBoxNumber(cb.getNumber());
                    itemPlace.setShelfNumber(shelf.getNumber());
                    itemPlace.setCount(addingMeat.getCount());
                    itemPlaces.add(itemPlace);
                    addingFinished = true;
                    break;
                    // dokoncil som pridavanie masa
                }else{
                    Meat divideMeat = SerializationUtils.clone(addingMeat);
                    addingMeat.setCount(addingMeat.getCount() - shelf.getFreeCapacity());
                    divideMeat.setCount(shelf.getFreeCapacity());
                    divideMeat.setShelf(shelf);
                    shelf.getMeat().add(divideMeat);
                    ItemPlace itemPlace = new ItemPlace();
                    itemPlace.setBoxNumber(cb.getNumber());
                    itemPlace.setShelfNumber(shelf.getNumber());
                    itemPlace.setCount(divideMeat.getCount());
                    itemPlaces.add(itemPlace);
                }

            }
            if (addingFinished) break;
        }
        ItemPlace.ItemPlaceList itemPlaceList = new ItemPlace.ItemPlaceList();
        itemPlaceList.setItemPlaceList(itemPlaces);
        if (!addingFinished){
            itemPlaceList.setMessage("Only part of the shipment was stored, could not fit more into warehouse");
        }
        return itemPlaceList;
    }
}
