package impl;

import api.WarehouseManageService;
import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;
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
        return GSON.toJson(getPickingItemFromWarehouseByMeatTypeImpl(inputJson));
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
                } else if (addingMeat.getCount() <= shelf.getFreeCapacity()){
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

        if (!addingFinished){

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
        return Reports.generateCsvReport(CompanyProvider.getInstance().getAppData().getWarehouse()).getBytes();
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

    private ItemPlace.ItemPlaceList getPickingItemFromWarehouseByMeatTypeImpl(String inputJson) {
        ItemPlaceInput input = GSON.fromJson(inputJson, ItemPlaceInput.class);
        Warehouse warehouse = CompanyProvider.getInstance().getAppData().getWarehouse();
        ItemPlace.ItemPlaceList output = new ItemPlace.ItemPlaceList();
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

        int meatMissing = input.getCount();
        Shelf meatShelf;
        int meatTaken;
        for(Meat meat : meats) {
            meatShelf = meat.getShelf();
            meatTaken = meatMissing;

            if((meatMissing - meat.getCount()) == 0) {
                meatShelf.getMeat().remove(meat);
                meatTaken = meat.getCount();
                meatMissing = 0;
            }
            else if((meatMissing - meat.getCount()) > 0) {
                meatShelf.getMeat().remove(meat);
                meatTaken = meat.getCount();
                meatMissing -= meat.getCount();
            }
            else {
                meat.setCount(meat.getCount() - meatTaken);
                meatMissing = 0;
            }

            meatShelf.setCapacity(meatShelf.getCapacity() + meatTaken);

            ItemPlace itemPlace = new ItemPlace();
            itemPlace.setBoxNumber(meatShelf.getCoolingBox().getNumber());
            itemPlace.setCount(meatTaken);
            itemPlace.setDateOfExpiration(meat.getExpiryDate());
            itemPlace.setShelfNumber(meatShelf.getNumber());
            output.getItemPlaceList().add(itemPlace);

            if(meatMissing == 0) break;
        }

        return output;
    }
}
