package impl;

import api.WarehouseManageService;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.axes.IteratorPool;
import dto.MeatGroup;
import dto.input.ItemPlaceInput;
import dto.output.ItemPlace;
import model.*;
import org.joda.time.DateTime;
import util.DateConverter;
import util.MeatDateComparator;
import model.CoolingBox;
import model.Meat;
import model.Shelf;
import model.Warehouse;
import model.*;
import org.apache.commons.lang3.SerializationUtils;
import org.joda.time.DateTime;
import util.DateConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rex on 19.4.2016.
 */
public class WarehouseManagerServiceImpl implements WarehouseManageService {

    public static final Gson GSON = new Gson();

    @Override
    public String getLocationOfItemInWarehouse(String inputJson) {
        ItemPlaceInput itemPlaceInput = GSON.fromJson(inputJson, ItemPlaceInput.class);

        Warehouse warehouse = CompanyProvider.getInstance().getAppData().getWarehouse();

        ItemPlace.ItemPlaceList result = new ItemPlace.ItemPlaceList();

        for (CoolingBox coolingBox : warehouse.getCoolingBoxes()) {
            for (Shelf shelf : coolingBox.getShelves()) {
                for (Meat meat : shelf.getMeat()) {
                    if(meat.getMeatType().equals(itemPlaceInput.getMeatType()) &&
                            coolingBox.getType().equals(itemPlaceInput.getCoolingType())) {
                        ItemPlace itemPlace = new ItemPlace();
                        itemPlace.setBoxNumber(coolingBox.getNumber());
                        itemPlace.setCount(meat.getCount());
                        itemPlace.setDateOfExpiration(meat.getExpiryDate());
                        itemPlace.setShelfNumber(shelf.getNumber());
                        result.getItemPlaceList().add(itemPlace);
                    }
                }
            }
        }

        return GSON.toJson(result);
    }

    @Override
    public String getPickingItemFromWarehouseByMeatType(String inputJson) {
        Gson gson = new Gson();
        ItemPlaceInput input = gson.fromJson(inputJson, ItemPlaceInput.class);
        Warehouse warehouse = CompanyProvider.getInstance().getAppData().getWarehouse();
        ItemPlace output = new ItemPlace();
        List<Meat> meats = new ArrayList<>();

        for (CoolingBox coolingBox : warehouse.getCoolingBoxes()) {
            if(coolingBox.getType().equals(input.getCoolingType())) {
                for(Shelf shelf : coolingBox.getShelves()) {
                    for(Meat meat : shelf.getMeat()) {
                        if(meat.getMeatType().equals(input.getMeatType())) {
                            meats.add(meat);
                        }
                    }
                }
            }
        }

        Collections.sort(meats, new MeatDateComparator());



        return null;
    }

    @Override
    public String preparationShipmentOfMeat(String inputJson) {
        return null;
    }

    @Override
    public String putItemInStock(String inputJson) {
        Meat addingMeat = (new Gson()).fromJson(inputJson, Meat.class);

        Warehouse warehouse = CompanyProvider.getInstance().getAppData().getWarehouse();
        List<ItemPlace> itemPlaces = new ArrayList<>();
        boolean addingFinished = false;

        for (CoolingBox cb : warehouse.getCoolingBoxes()) {
            BoxType meatCooling = addingMeat.isFrozen() ? BoxType.FREEZING : BoxType.COOLING;

            if (!cb.getType().equals(meatCooling)) continue;
            for (Shelf shelf : cb.getShelves()) {

                if (shelf.getFreeCapacity() == 0){
                    continue;
                } else if (addingMeat.getCount() < shelf.getFreeCapacity()){
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
        return GSON.toJson(itemPlaceList);

    }

    @Override
    public String receivingShipments(String inputJson) {
        return null;
    }

    @Override
    public byte[] generateReportOnCurrentState() {
        StringBuilder csv = new StringBuilder();

        csv.append("meat-type,count,expiry-date,freezed" + System.lineSeparator());

        return csv.toString().getBytes();
    }

    @Override
    public String ejectionItems() {
        return null;
    }

    @Override
    public void moveItem(String inputJson) {

    }

    @Override
    public void emptyCoolingBoxForCleaning(String inputJson) {

    }

    private HashMap<MeatGroup, Integer> getMeatCountByType() {
        HashMap<MeatGroup, Integer> meatCounts = new HashMap<>();

        for (CoolingBox coolingBox : CompanyProvider.getInstance().getAppData().getWarehouse().getCoolingBoxes()) {
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
