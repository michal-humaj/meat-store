import api.WarehouseConfigurationService;
import api.WarehouseManageService;
import impl.CompanyProvider;
import impl.WarehouseConfigurationServiceImpl;
import impl.WarehouseManagerServiceImpl;
import model.*;
import model.Warehouse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rex on 19.4.2016.
 */
public class Main {

    public static void main(String[] args) {
        WarehouseManageService warehouseManageService = new WarehouseManagerServiceImpl();
        WarehouseConfigurationService warehouseConfigurationService = new WarehouseConfigurationServiceImpl();

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("init1.json")));
            warehouseConfigurationService.initializateWarehouse(jsonContent);

            addMeats();
            String jsonPickItem = new String(Files.readAllBytes(Paths.get("pickItemFromWarehouse.json")));
//            String out = warehouseManageService.getPickingItemFromWarehouseByMeatType(jsonPickItem);
            String out = warehouseManageService
                    .putItemInStock("{ \"type\" : \"PORK\", \"count\" : 14, \"date-of-slaughter\" : \"25.02.2016\", \"is-frozen\" : false }");

            Warehouse w = CompanyProvider.getInstance().getAppData().getWarehouse();
            System.out.printf(out);



        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load input json");
        }


    }

    public static void addMeats() {
        Warehouse warehouse = CompanyProvider.getInstance().getAppData().getWarehouse();
        Meat meat1 = new Meat();
        Meat meat2 = new Meat();
        Meat meat3 = new Meat();

        meat1.setCount(10);
        meat1.setDate("08.08.2016");
        meat1.setFrozen(false);
        meat1.setMeatType(MeatType.CHICKEN);

        meat2.setCount(10);
        meat2.setDate("07.08.2016");
        meat2.setFrozen(false);
        meat2.setMeatType(MeatType.SALMON);

        meat3.setCount(10);
        meat3.setDate("08.06.2016");
        meat3.setFrozen(false);
        meat3.setMeatType(MeatType.CHICKEN);

        List<Meat> meats = new ArrayList<>();
        meats.add(meat1);
        meats.add(meat2);
        meats.add(meat3);
        warehouse.getCoolingBoxes().get(0).getShelves().get(0).setMeat(meats);

        for (CoolingBox coolingBox : warehouse.getCoolingBoxes()) {
            for(Shelf shelf : coolingBox.getShelves()) {
                for(Meat meat : shelf.getMeat()) {
                    meat.setShelf(shelf);
                }
            }
        }
    }
}
