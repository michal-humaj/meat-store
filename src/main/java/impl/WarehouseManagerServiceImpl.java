package impl;

import api.WarehouseManageService;
import com.google.gson.Gson;
import dto.input.ItemPlaceInput;
import dto.output.ItemPlace;
import model.CoolingBox;
import model.Meat;
import model.Shelf;
import model.Warehouse;
import org.joda.time.DateTime;
import util.DateConverter;


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
                    if(meat.getMeatType().equals(itemPlaceInput.getMeatType())) {
                        ItemPlace itemPlace = new ItemPlace();
                        itemPlace.setBoxNumber(coolingBox.getNumber());
                        itemPlace.setCount(meat.getCount());

                        DateTime slaughterDate = DateConverter.toDateTimeDots(meat.getDate());
                        DateTime expiryDate = slaughterDate.plusDays(meat.getMeatType().getFreshDays());

                        itemPlace.setDateOfExpiration(DateConverter.toStringDots(expiryDate));
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
        return new byte[0];
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
}
