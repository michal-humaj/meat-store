package impl;

import api.WarehouseManageService;
import com.google.gson.Gson;
import dto.MeatGroup;
import dto.input.ItemPlaceInput;
import dto.output.ItemPlace;
import model.CoolingBox;
import model.Meat;
import model.Shelf;
import model.Warehouse;

import java.util.HashMap;


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
        return null;
    }

    @Override
    public String preparationShipmentOfMeat(String inputJson) {
        return null;
    }

    @Override
    public String putItemInStock(String inputJson) {
        return null;
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
