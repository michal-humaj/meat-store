package impl;

import api.WarehouseManageService;

/**
 * Created by Rex on 19.4.2016.
 */
public class WarehouseManagerServiceImpl implements WarehouseManageService {

    @Override
    public String getLocationOfItemInWarehouse(String inputJson) {
        return null;
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
